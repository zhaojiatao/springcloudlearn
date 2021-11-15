package zjt.learn.infrastructure.statemachine.base.eventbus;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import zjt.learn.domain.model.OrderEntity;
import zjt.learn.infrastructure.repository.IOrderRepository;
import zjt.learn.infrastructure.statemachine.base.event.BasicOrderEvent;
import zjt.learn.statemashine.BaseEvent;
import zjt.learn.statemashine.BaseState;

import javax.annotation.Resource;

@Slf4j
@Service("orderEventBus")
public class OrderEventBusImpl implements IOrderEventBus {

    @Autowired
    private IOrderRepository orderRepository;


    @Autowired
    @Qualifier("simpleOrderStateMachineService")
    private StateMachineService<BaseState, BaseEvent> simpleOrderStateMachineService;

    @Resource(name = "depositOrderStateMachineService")
    private StateMachineService<BaseState, BaseEvent> depositOrderStateMachineService;



    private final static String EXCEPTION_CODE = "SYSTEM_EXCEPTION";


    private StateMachineService<BaseState, BaseEvent> choseMachineServiceByOrderType(Long orderId ){
        OrderEntity orderEntity = orderRepository.loadOrderEntity(orderId);
        Long orderType = orderEntity.getOrderInfo().getOrderType();
        if(orderType==1){
            return simpleOrderStateMachineService;
        }else if(orderType==2){
            return depositOrderStateMachineService;
        }
        return null;
    }


    @Override
    public void submitEvent(BasicOrderEvent<? extends BaseEvent> orderEvent) {
        Long orderId = orderEvent.getOrderId();
        Assert.notNull(orderId, "orderId不能为空");
        StateMachineService<BaseState, BaseEvent> stateMachineService=choseMachineServiceByOrderType(orderId);
        try {
            //获取状态机
            StateMachine<BaseState, BaseEvent> machine = stateMachineService.acquireStateMachine(orderId.toString(), false);
            Message<?> message = MessageBuilder.withPayload(orderEvent.getEventType()).setHeader("_event", orderEvent).build();
            machine.getStateMachineAccessor().doWithRegion(function -> function.addStateMachineInterceptor(
                    new StateMachineInterceptorAdapter<BaseState, BaseEvent>() {
                        @Override
                        public Exception stateMachineError(StateMachine<BaseState, BaseEvent> stateMachine, Exception exception) {
                            stateMachine.getExtendedState().getVariables().put(EXCEPTION_CODE, exception);
                            return exception;
                        }
                    }));
            machine.start();

            machine.sendEvent((Message<BaseEvent>) message);
            if (machine.hasStateMachineError()) {
                log.error("状态机推动发生错误,machine.getExtendedState().getVariables()==>{}", JSONObject.toJSONString(machine.getExtendedState().getVariables()));
                throw (Exception) machine.getExtendedState().getVariables().get(EXCEPTION_CODE);
            }
        } catch (Exception e) {
            log.error("订单{}状态机处理异常.", orderId, e);
            throw new RuntimeException("LOAN_SECOND_CAR_SYSTEM_EXCEPTION");
        } finally {
            stateMachineService.releaseStateMachine(orderId.toString(), true);
        }
    }
}
