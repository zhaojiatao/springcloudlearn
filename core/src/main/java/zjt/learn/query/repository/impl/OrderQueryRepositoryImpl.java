package zjt.learn.query.repository.impl;

import org.springframework.stereotype.Repository;
import zjt.learn.model.dto.OrderInfoDTO;
import zjt.learn.query.repository.IOrderQueryRepository;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/26 13:15
 * @ClassName: OrderQueryRepositoryImpl
 */
@Repository
public class OrderQueryRepositoryImpl implements IOrderQueryRepository {

    @Override
    public OrderInfoDTO queryOrderByOrderId(Long id) {
        //根据id查询订单
        OrderInfoDTO orderInfoDTO=new OrderInfoDTO();
        orderInfoDTO.setId(1L);
        orderInfoDTO.setOrderSerial("SO2021110500001");
        return orderInfoDTO;
    }

    @Override
    public OrderInfoDTO queryOrderByOrderNo(String orderNo) {
        //根据orderNo查询订单
        return null;
    }
}
