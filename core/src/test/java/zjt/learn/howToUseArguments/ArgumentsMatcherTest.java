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
