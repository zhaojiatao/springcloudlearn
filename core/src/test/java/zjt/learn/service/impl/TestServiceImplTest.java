package zjt.learn.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import zjt.learn.dao.IOrderDAO;
import zjt.learn.dto.MakeOrderDTO;
import zjt.learn.service.IdGenerateAdapter;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/12 13:19
 * @ClassName: TestServiceImplTest
 */
class TestServiceImplTest {

    @InjectMocks
    @Spy
    private TestServiceImpl testService;

    @Mock
    private IdGenerateAdapter idGenerateAdapter;

    @Mock
    private IOrderDAO orderDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeOrder() {
        try{
            testService.makeOrder(null);
            Assertions.fail("这里会挂掉");
        }catch (Exception e){
            Assertions.assertTrue(e instanceof RuntimeException);
        }

        MakeOrderDTO makeOrderDTO=new MakeOrderDTO();

        Mockito.when(idGenerateAdapter.generate()).thenReturn(123456789L);
        Mockito.when(orderDAO.save(Mockito.any())).thenReturn(123456789L);

        Long aLong = testService.makeOrder(makeOrderDTO);
        Assertions.assertEquals(aLong,123456789L);


        try(MockedStatic<TestServiceImpl> staticService = Mockito.mockStatic(TestServiceImpl.class)){
            staticService.when(() -> TestServiceImpl.buildName(Mockito.any())).thenReturn("aabb");
            staticService.when(() -> TestServiceImpl.buildName("aa1")).thenCallRealMethod();
            Assertions.assertEquals("aabb", TestServiceImpl.buildName("aa"));
            Assertions.assertEquals("aa1CC", TestServiceImpl.buildName("aa1"));
        }



    }
}