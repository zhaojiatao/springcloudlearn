package zjt.learn.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import zjt.learn.dao.IOrderDAO;
import zjt.learn.dto.MakeOrderDTO;
import zjt.learn.service.IUserAdapter;
import zjt.learn.service.IdGenerateService;

import static org.mockito.Mockito.*;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/15 21:45
 * @ClassName: DemoTest
 */
public class DemoTest {

    @InjectMocks
    @Spy
    private OrderServiceImpl testService;

    @Mock
    private IdGenerateService idGenerateService;

    @Mock
    private IUserAdapter userAdapter;

    @Mock
    private IOrderDAO orderDAO;

    @BeforeEach
    void setUp() {
        //mock 注解需要搭配 MockitoAnnotations.openMocks(testClass) 方法一起使用。
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 真实调用testService 并传入null参数
     */
    @Test
    void test01(){
        try{
            testService.makeOrder(null);
            Assertions.fail("下单参数不可为null");
        }catch (Exception e){
            Assertions.assertTrue(e instanceof RuntimeException);
        }

    }

    /*
    * 学习基本的stubbing
    * 1、任意参数
    * 2、具体参数
    * 3、抛异常
    */
    @Test
    void test02(){


        {
            when(userAdapter.queryUserRealNameByUserName(Mockito.anyString())).thenReturn("mock真实姓名");
            MakeOrderDTO makeOrderDTO = testService.makeOrder(MakeOrderDTO.builder()
                    .userName("zhangsan").build());
            Assertions.assertTrue("mock真实姓名".equalsIgnoreCase(makeOrderDTO.getUserRealName()));
        }

        {
            when(userAdapter.queryUserRealNameByUserName("zhangsan")).thenReturn("张三");
            MakeOrderDTO makeOrderDTO = testService.makeOrder(MakeOrderDTO.builder()
                    .userName("zhangsan").build());
            Assertions.assertTrue("张三".equalsIgnoreCase(makeOrderDTO.getUserRealName()));
        }


        {
            when(userAdapter.queryUserRealNameByUserName("zhangsan")).thenThrow(new RuntimeException("无法查询到该用户"));
            try{
                MakeOrderDTO makeOrderDTO = testService.makeOrder(MakeOrderDTO.builder()
                        .userName("zhangsan").build());
            }catch (Exception e){
                Assertions.assertTrue(e instanceof RuntimeException);
            }
        }

    }

    /**
     * 学习如何mock没有返回值的方法
     */
    @Test
    public void test03(){
        {
            doNothing().when(orderDAO).save(Mockito.any());
            MakeOrderDTO makeOrderDTO = testService.makeOrder(MakeOrderDTO.builder()
                    .userName("zhangsan").build());
            verify(orderDAO,times(1)).save(Mockito.any());
        }

        {
            doThrow(RuntimeException.class).when(orderDAO).save(Mockito.any());
            try {
                MakeOrderDTO makeOrderDTO = testService.makeOrder(MakeOrderDTO.builder()
                        .userName("zhangsan").build());
            }catch (Exception e){
                Assertions.assertTrue(e instanceof RuntimeException);
            }
        }

    }



}
