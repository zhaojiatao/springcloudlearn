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
