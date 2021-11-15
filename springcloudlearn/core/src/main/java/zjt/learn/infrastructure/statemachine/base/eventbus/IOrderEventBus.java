package zjt.learn.infrastructure.statemachine.base.eventbus;


import zjt.learn.infrastructure.statemachine.base.event.BasicOrderEvent;
import zjt.learn.statemashine.BaseEvent;

/**
 * 流程推动
 */
public interface IOrderEventBus {
    /**
     * 提交事件
     *
     * @param orderEvent
     * @return
     */
    void submitEvent(BasicOrderEvent<? extends BaseEvent> orderEvent);
}
