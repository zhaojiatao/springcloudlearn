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
 * @Date: 2021/11/8 16:59
 * @ClassName: DepositCommonAction01
 */
@Slf4j
@Component
public class DepositCommonAction01 extends AbstractOrderAction {
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        log.info("DepositCommonAction01 do something，orderId={}",orderId);
        //TODO do something...
    }
}