# springcloudlearn
springcloud学习项目-使用ddd
# 官方文档

# 零、文档及依赖

[官方api文档](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html) 

https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html



```xml
<dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-inline</artifactId>
            <version>4.3.1</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>4.3.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
```







# 一、如何编写一个mock类

## 1.1 @RunWith(MockitoJUnitRunner.class) 

> 推荐使用此方式。
>
> 当使用此方式声明测试类，则无需再使用MockitoAnnotations.openMocks(this);
>
> 开启此注解，则会对单测做一些列的校验。



```java
@RunWith(MockitoJUnitRunner.class)
public class MockByRunnerTest {    
    @Test    
    public void testMock(){        
        //mock之后如果不stabbling，则返回默认值        
        IdGenerateService idGenerateService=mock(IdGenerateService.class);        
        Long generate = idGenerateService.generate();        
        System.out.println(generate);    
    }
}
```

## 1.2 By Annotiation

> 使用注解的方式更会比较优雅，但需要通过代码显式开启mock。

```java
public class MockByAnnotiationTest {
    @Mock
    private IOrderService orderService;
    @Before
    public void init(){
        //如果使用@RunWith(MockitoJUnitRunner.class)，则此处无需初始化注解扫描
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testMock(){
        MakeOrderDTO result = orderService.query(1L);
        System.out.println(result);
    }
}
```





## 1.3  By Rule

```java
public class MockByRuleTest {

    /**
     * 使用Rule的方式，这种一般比较少见，一般使用注解方式
     */
    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @Test
    public void testMock(){
        //mock之后如果不stabbling，则返回默认值
        IdGenerateService idGenerateService=mock(IdGenerateService.class);
        Long generate = idGenerateService.generate();
        System.out.println(generate);
    }

}
```



## 1.4 深度mock

### 1.4.1 @Mock(answer = Answers.RETURNS_DEEP_STUBS)

> 当使用answer = Answers.RETURNS_DEEP_STUBS时，mockito会自动为我们返回的对象进行深度mock，不过这可能不是我们想要的结果。

```java
public class DeepMockTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private IOrderService orderService;


    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeepMock(){
        MakeOrderDTO result = orderService.query(1L);
        System.out.println(result.getId());

    }
}
```



### 1.4.2 return一个mock对象

```java
public class DeepMockTest02 {

    @Mock
    private IOrderService orderService;

    @Mock
    private MakeOrderDTO makeOrderDTO;


    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeepMock(){
        when( orderService.query(Mockito.anyLong())).thenReturn(makeOrderDTO);
        MakeOrderDTO result = orderService.query(1L);
        when(makeOrderDTO.getId()).thenReturn(1111L);
        System.out.println(result.getId());

    }
}
```





# 二、如何进行stubbing进行打桩

> 我们使用mock框架，最重要的就是对mock对象的行为进行stubbing，译为打桩，或者录制。

```java
package zjt.learn.howToStubbing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import zjt.learn.service.impl.StubbingService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 13:29
 * @ClassName: StubbingTest
 */
@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    private List<String> list;

    @Before
    public void init(){
        this.list=mock(ArrayList.class);
    }

    @Test
    public void howToUseStubbing(){
        when(list.get(0)).thenReturn("first");
        assertThat(list.get(0),equalTo("first"));

        when(list.get(anyInt())).thenThrow(new RuntimeException());

        try{
            list.get(0);
            fail();
        }catch (Exception e){
            assertThat(e,instanceOf(RuntimeException.class));
        }
    }


    /**
     * 对一个void方法进行mock，指定doNothing，并通过verify校验是否执行
     * 对一个void方法进行mock，指定必然抛出异常，并断言是否发生异常
     */
    @Test
    public void howToStubbingVoidMethod(){
        //mock 一个没有返回值的动作
        doNothing().when(list).clear();
        list.clear();
        //校验是否执行了这个动作
        verify(list,times(1)).clear();

        //mock 一个没有返回值的动作抛异常
        doThrow(RuntimeException.class).when(list).clear();
        try{
            list.clear();
            fail();
        }catch (Exception e){
            //校验是否执行了这个动作并抛异常
            assertThat(e,instanceOf(RuntimeException.class));
        }

    }


    /**
     * 如下两种是等价的
     * when(list.get(0)).thenReturn("first");
     * doReturn("second").when(list).get(1);
     *
     */
    @Test
    public void stubblingDoReturn(){
        when(list.get(0)).thenReturn("first");
        doReturn("second").when(list).get(1);
        assertThat(list.get(0),equalTo("first"));
        assertThat(list.get(1),equalTo("second"));

    }

    /**
     * 多次调用，每次返回不一样，依次生效
     */
    @Test
    public void iterateStubbing(){
        when(list.size()).thenReturn(1,2,3,4);
        assertThat(list.size(),equalTo(1));
        assertThat(list.size(),equalTo(2));
        assertThat(list.size(),equalTo(3));
        assertThat(list.size(),equalTo(4));
        assertThat(list.size(),equalTo(4));
    }

    /**
     * 多次调用，每次返回不一样，依次生效
     * 这种等价于when(list.size()).thenReturn(1,2,3,4)
     */
    @Test
    public void iterateStubbing02(){
        when(list.size()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);
        assertThat(list.size(),equalTo(1));
        assertThat(list.size(),equalTo(2));
        assertThat(list.size(),equalTo(3));
        assertThat(list.size(),equalTo(4));
        assertThat(list.size(),equalTo(4));
    }


    /**
     * 重点：根据mock方法的入参，来定制化逻辑，返回不同的answer
     */
    @Test
    public void stubbingWithAnswer(){
        when(list.get(anyInt())).thenAnswer(invocationOnMock -> {
            //获取调用该方法的第0个入参
            Integer argument = invocationOnMock.getArgument(0, Integer.class);
            return String.valueOf(argument*10);//这里是举例子，入参乘以10返回
        });

        assertThat(list.get(0),equalTo("0"));
        assertThat(list.get(999),equalTo("9990"));
    }


    /**
     * mock一个类，mock部分方法，真实调用部分方法
     */
    @Test
    public void stubbingWihtRealCall(){
        StubbingService service = mock(StubbingService.class);
//        System.out.println(service.getClass());
//        service.getS();
        when(service.getS()).thenReturn("Alex");
        assertThat(service.getS(),equalTo("Alex"));

        when(service.getI()).thenCallRealMethod();//仅此方法调用真实逻辑
        assertThat(service.getI(),equalTo(10));

    }

    @After
    public void destroy(){
        //将stubbing的动作解除
        reset(this.list);
    }

}

```





# 三、如何使用spy

> 被spy的对象,除了被stubbing的逻辑之外,都默认走真实方法.

```java
package zjt.learn.howToSpy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * 功能：
 * 被spy的对象,除了被stubbing的逻辑之外,都默认走真实方法
 * @Author: zhaojiatao
 * @Date: 2022/5/16 14:05
 * @ClassName: SpyingTest
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    @Test
    public void testSpy(){
        List<String> realList=new ArrayList<>();
        List<String> list = spy(realList);

        list.add("Mockito");
        list.add("PowerMock");

        assertThat(list.get(0),equalTo("Mockito"));
        assertThat(list.get(1),equalTo("PowerMock"));
        assertThat(list.isEmpty(),equalTo(false));

        when(list.isEmpty()).thenReturn(true);//仅此方法被mock，其余走真实逻辑
        when(list.size()).thenReturn(0);//仅此方法被mock，其余走真实逻辑
        assertThat(list.get(0),equalTo("Mockito"));
        assertThat(list.get(1),equalTo("PowerMock"));
        assertThat(list.isEmpty(),equalTo(true));
        assertThat(list.size(),equalTo(0));
    }
}

```





# 四、如何使用Arguments

>  [Mockito](https://so.csdn.net/so/search?q=Mockito&spm=1001.2101.3001.7020)通过equals()方法，来对方法参数进行验证。但有时我们需要更加灵活的参数需求，比如，匹配任何的String类型的参数等等。参数匹配器就是一个能够满足这些需求的工具。 

### 4.1 关键方法：isA,any

```java
package zjt.learn.howToUseArguments;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;

/**
 * 功能：
 * argument matcher在mock的过程中扮演这一个重要的角色，可以使得mock的返回值根据不同的argument匹配回不同的返回值。
 * 其内部的底层原理是使用了object's equals()方法
 * @Author: zhaojiatao
 * @Date: 2022/5/16 14:20
 * @ClassName: ArgumentsMatcherTest
 */
public class ArgumentsMatcherTest {

    @Test
    public void basicTest(){
        List<Integer> list= mock(ArrayList.class);
        when(list.get(0)).thenReturn(100);
        assertThat(list.get(0),equalTo(100));
        assertThat(list.get(1),nullValue());

    }

    /**
     * isA,any
     */
    @Test
    public void testComplex(){
        Foo foo = mock(Foo.class);
        {
            when(foo.function(Mockito.isA(Parent.class))).thenReturn(100);
            int result = foo.function(new Child1());
            assertThat(result,equalTo(100));
            int result2 = foo.function(new Child2());
            assertThat(result2,equalTo(100));
            reset(foo);
        }

        {
            when(foo.function(Mockito.isA(Child1.class))).thenReturn(100);
            int result = foo.function(new Child1());
            assertThat(result,equalTo(100));
            int result2 = foo.function(new Child2());
            assertThat(result2,equalTo(0));
            reset(foo);
        }


        {
            //any里面默认就是true
            when(foo.function(Mockito.any(Child1.class))).thenReturn(100);
            int result2 = foo.function(new Child2());
            assertThat(result2,equalTo(0));
            reset(foo);
        }




    }

    static class Foo{
        int function(Parent p){
            return p.work();
        }
    }

    interface Parent{
        int work();
    }

    class Child1 implements Parent{

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    class Child2 implements Parent{

        @Override
        public int work() {
            throw new RuntimeException();
        }
    }


}

```

### 4.2 Wildcard Argument Matcher 通配

> Mockito框架中的Matchers类内建了很多参数匹配器，而我们常用的Mockito对象便是继承自Matchers。这些内建的参数匹配器如，anyInt()匹配任何int类型参数，anyString()匹配任何字符串，anySet()匹配任何Set等。下面通过例子来说明如何使用内建的参数匹配器

```java
package zjt.learn.howToUseArguments;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import zjt.learn.service.impl.SimpleService;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * 功能：
 * 有@RunWith(MockitoJUnitRunner.class) 则无需使用 MockitoAnnotations.openMocks(this);
 * @Author: zhaojiatao
 * @Date: 2022/5/16 16:12
 * @ClassName: WildcardArgumentMatcherTest
 *
 * 如果使用了wildcard方式，则如果想特殊指定，必须使用eq()函数
 *
 **/
@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentMatcherTest {


    @Mock
    private SimpleService simpleService;

    @Test
    public void wildcardMethod1(){
        when(simpleService.method1(anyInt(),anyString(),anyCollection(),isA(Serializable.class)))
                .thenReturn(100);
        int result = simpleService.method1(1, "Alex", Collections.emptyList(), "Mockito");
        assertThat(result,equalTo(100));


        result = simpleService.method1(1, "Wang", Collections.emptySet(), "MockitoForJava");
        assertThat(result,equalTo(100));


    }


    @Test
    public void wildcardMethodWhihSpec(){
        //注意：此处的第一次通配如果放在第三行，则会覆盖之前的特殊化定制
        when(simpleService.method1(anyInt(),anyString(),anyCollection(),isA(Serializable.class)))
                .thenReturn(-1);
        when(simpleService.method1(anyInt(),eq("Alex"),anyCollection(),isA(Serializable.class)))
                .thenReturn(100);
        when(simpleService.method1(anyInt(),eq("Wang"),anyCollection(),isA(Serializable.class)))
                .thenReturn(200);

        int result =simpleService.method1(1, "ssss", Collections.emptySet(), "MockitoForJava");
        assertThat(result,equalTo(-1));

        result = simpleService.method1(1, "Alex", Collections.emptyList(), "Mockito");
        assertThat(result,equalTo(100));

        result = simpleService.method1(1, "Wang", Collections.emptySet(), "MockitoForJava");
        assertThat(result,equalTo(200));

    }



    @Test
    public void wildcardMethod2(){
        List<Object> list = Collections.emptyList();
        doNothing().when(simpleService).method2(anyInt(),anyString(),anyCollection(),isA(Serializable.class));
        simpleService.method1(1, "Alex", list, "Mockito");
        verify(simpleService,times(1)).method1(1, "Alex", list, "Mockito");
        //如果使用了wildcard方式，则如果想特殊指定，必须使用eq()函数
        verify(simpleService,times(1)).method1(anyInt(), eq("Alex"), anyCollection(),isA(Serializable.class));

    }

    @After
    public void destroy(){
        //将stubbing的动作解除
        reset(simpleService);
    }


}

```





# 五、对于assertThat函数中的matcher详解

> org.hamcrest.Matcher 包是个很优雅值得学习的开源包
>
> 使用org.hamcrest.MatcherAssert.assertThat比以往的assert更灵活，易于拓展

```java
package zjt.learn.howToUseMatcher;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 16:55
 * @ClassName: AssertMatcherTest
 */
public class AssertMatcherTest {


    @Test
    public void test(){
        int i=10;
        assertThat(i,equalTo(10));
        assertThat(i,not(equalTo(20)));
        assertThat(i,is(10));
        assertThat(i,is(not(20)));

        double price=23.45;
        assertThat(price,either(equalTo(23.45)).or(equalTo(23.54)));
        assertThat(price,both(equalTo(23.45)).and(equalTo(23.54)));

    }

    @Test
    public void test2(){
        double price=23.45;
        assertThat(price,either(equalTo(23.45)).or(equalTo(23.54)));
        assertThat(price,both(equalTo(23.45)).and(not(equalTo(23.54))));
        assertThat(price, anyOf(is(23.45),is(23.54)));
        assertThat(price, anyOf(is(23.45),not(is(23.54)),not(35.24)));

        assertThat(Stream.of(1,2,3).anyMatch(i->i>2),equalTo(true));
        assertThat(Stream.of(1,2,3).anyMatch(i->i>0),equalTo(true));

    }

    /*
    @Test
    public void test3(){
        double price=23.45;
        //如果断言失败，打印自定义msg
        assertThat("the double value assertion failed.",price,either(equalTo(23.54)).or(equalTo(23.66)));
    }
    */

}

```



# 六、如何mock final方法

> mockito自2.1.0之后版本可以无视final，和普通mock方式一样

```java
package zjt.learn.howToMockFinal;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import zjt.learn.service.impl.OrderServiceImpl;
import zjt.learn.service.impl.SimpleService;

import static org.mockito.Mockito.when;


/**
 * 功能：
 * final方法和普通方法没区别，正常mock即可
 * @Author: zhaojiatao
 * @Date: 2022/5/16 17:22
 * @ClassName: MockFinalTest
 */
@RunWith(MockitoJUnitRunner.class)
public class MockFinalTest {

    @Mock
    private SimpleService simpleService;


    @Test
    public void mockFinalTest(){
        when(simpleService.testFinal()).thenReturn("abc");
        String s = simpleService.testFinal();
        System.out.println(s);
    }

}

```





# 七、如何mock 静态方法

 ```java
package zjt.learn.howToMockStatic;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import zjt.learn.service.impl.OrderServiceImpl;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 17:14
 * @ClassName: MockStaticMethodTest
 */

/**
 * mock静态方法必须使用mockito-inline，
 * 对静态方法打桩使用 mockStatic() 方法来 mock静态方法的所属类，此方法返回一个具有作用域的模拟对象。
 *
 * 最好使用java7的try-with-resources，自动关闭资源
 * 原因是因为 mockStatic() 方法是将当前需要 mock 的类注册到本地线程上（ThreadLocal），
 * 而这个注册在一次 mock 使用完之后是不会消失的，需要我们手动的去销毁。
 * 如过没有销毁，再次 mock 这个类的时候 Mockito 将会提示我们
 * ：”当前对象 mock 的对象已经在线程中注册了，请先撤销注册后再试“。
 * 这样做的目的也是为了保证模拟出来的对象之间是相互隔离的，
 * 保证同时和连续的测试不会收到上下文的影响。
 *
 * */
public class MockStaticMethodTest {

    @Test
    public void mockStaticTest(){
        try(MockedStatic<OrderServiceImpl> staticService = Mockito.mockStatic(OrderServiceImpl.class)){
            staticService.when(() -> OrderServiceImpl.buildName(Mockito.any())).thenReturn("aabb");
            staticService.when(() -> OrderServiceImpl.buildName("aa1")).thenCallRealMethod();
            Assertions.assertEquals("aabb", OrderServiceImpl.buildName("aa"));
            Assertions.assertEquals("aa1CC", OrderServiceImpl.buildName("aa1"));
        }
    }
}

 ```







