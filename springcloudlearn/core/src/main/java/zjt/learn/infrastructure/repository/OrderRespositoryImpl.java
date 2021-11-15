package zjt.learn.infrastructure.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import zjt.learn.domain.model.OrderEntity;
import zjt.learn.domain.model.OrderInfo;
import zjt.learn.statemashine.depositorder.DepositOrderStatus;
import zjt.learn.statemashine.simpleorder.SimpleOrderStatus;

/**
 * 功能：
 * 订单实体仓库
 * @Author: zhaojiatao
 * @Date: 2021/10/26 11:03
 * @ClassName: OrderRespositoryImpl
 */
@Repository
public class OrderRespositoryImpl implements IOrderRepository {
    @Override
    public Long saveOrderEntity(OrderEntity orderEntity) {
        //落地逻辑省略

        return 1L;
    }

    @Override
    public OrderEntity loadOrderEntity(Long id) {
        //FIXME 临时mock的id为1和2的数据，用来测试状态机
        if(id==1){
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderType(1L);
            orderInfo.setStatus(SimpleOrderStatus.WAIT_PAYMENT.getCode());
            //查询逻辑省略
            return OrderEntity.builder().orderInfo(orderInfo).build();
        }
        if(id==2){
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderType(2L);
            orderInfo.setStatus(DepositOrderStatus.WAIT_PAYMENT_DEPOSIT.getCode());
            //查询逻辑省略
            return OrderEntity.builder().orderInfo(orderInfo).build();
        }
        //查询逻辑省略
        return OrderEntity.builder().orderInfo(new OrderInfo()).build();
    }
}
