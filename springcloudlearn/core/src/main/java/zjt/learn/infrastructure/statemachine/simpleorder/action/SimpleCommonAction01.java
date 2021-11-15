package zjt.learn.infrastructure.statemachine.simpleorder.action;

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
 * @Date: 2021/11/8 17:06
 * @ClassName: SimpleCommonAction01
 */
@Slf4j
@Component
public class SimpleCommonAction01 extends AbstractOrderAction {
    @Override
    protected void doAction(BasicOrderEvent<BaseEvent> event, BaseState source, BaseState target) {
        Long orderId = event.getOrderId();
        log.info("SimpleCommonAction01 do something，orderId={}",orderId);
        //TODO do something...
    }
}
