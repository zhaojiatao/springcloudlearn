package zjt.learn.howToMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zjt.learn.IOrderService;
import zjt.learn.dto.MakeOrderDTO;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 11:16
 * @ClassName: DeepMockTest
 */
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
