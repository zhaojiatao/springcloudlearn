package zjt.learn.infrastructure.statemachine.simpleorder.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zjt.learn.domain.model.OrderEntity;
import zjt.learn.infrastructure.repository.IOrderRepository;
import zjt.learn.infrastructure.statemachine.base.action.AbstractOrderAction;
import zjt.learn.infrastructure.statemachine.base.event.BasicOrderEvent;
import zjt.learn.statemashine.BaseEvent;
import zjt.learn.statemashine.BaseState;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/7 21:26
 * @ClassName: SendDingDingMsgAction
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class SimpleOrderAction02 extends AbstractOrderAction {
    @Autowired
    private IOrderRepository orderRepository;
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        OrderEntity orderEntity = orderRepository.loadOrderEntity(orderId);
        log.info("SimpleOrderAction02，orderId={}",orderId);

        //TODO 发送钉钉消息
    }
}
