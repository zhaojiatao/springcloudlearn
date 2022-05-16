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
