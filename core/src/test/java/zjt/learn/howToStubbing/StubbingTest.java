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
