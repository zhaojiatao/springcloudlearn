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
