package zjt.learn.infrastructure.statemachine.depositorder.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zjt.learn.infrastructure.statemachine.base.action.AbstractOrderAction;
import zjt.learn.infrastructure.statemachine.base.event.BasicOrderEvent;
import zjt.learn.statemashine.BaseEvent;
import zjt.learn.statemashine.BaseState;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/8 16:42
 * @ClassName: DepositAction01
 */
@Slf4j
@Component
public class DepositAction01 extends AbstractOrderAction {
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        log.info("DepositAction01 do something，orderId={}",orderId);
        //TODO do something...
    }
}
