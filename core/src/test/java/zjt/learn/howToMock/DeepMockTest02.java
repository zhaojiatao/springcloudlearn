package zjt.learn.howToMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import zjt.learn.IOrderService;
import zjt.learn.dto.MakeOrderDTO;

import static org.mockito.Mockito.when;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 13:24
 * @ClassName: DeepMockTest02
 */
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
