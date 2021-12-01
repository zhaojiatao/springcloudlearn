package zjt.learn.infrastructure.statemachine.base.persist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultExtendedState;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;
import zjt.learn.domain.model.OrderEntity;
import zjt.learn.infrastructure.repository.IOrderRepository;
import zjt.learn.statemashine.BaseEvent;
import zjt.learn.statemashine.BaseState;
import zjt.learn.statemashine.depositorder.DepositOrderStatus;
import zjt.learn.statemashine.simpleorder.SimpleOrderStatus;

import java.util.ArrayList;

@Slf4j
@Component("commonStateMachinePersist")
public class CommonStateMachinePersist implements StateMachinePersist<BaseState, BaseEvent, String> {

    @Autowired
    private IOrderRepository orderRepository;

    /**
     * 落地状态
     * @param context
     * @param contextObj
     */
    @Override
    public void write(StateMachineContext<BaseState, BaseEvent> context, String contextObj) {
        Long orderId = Long.valueOf(contextObj);
        OrderEntity orderEntity = orderRepository.loadOrderEntity(orderId);
        log.info("修改前订单状态={}",orderEntity.getOrderInfo().getStatus());
        BaseState statusEnum = context.getState();
        orderEntity.getOrderInfo().setStatus(statusEnum.code());
        orderRepository.saveOrderEntity(orderEntity);
        log.info("修改后订单状态={}",orderEntity.getOrderInfo().getStatus());

    }


    /**
     * 根据订单号构造状态机上下文
     * @param contextObj
     * @return
     */
    @Override
    public StateMachineContext<BaseState, BaseEvent> read(String contextObj) {
        Long orderId = Long.valueOf(contextObj);
        OrderEntity orderEntity = orderRepository.loadOrderEntity(orderId);

        Long orderType = orderEntity.getOrderInfo().getOrderType();

        //TODO 使用订单类型枚举，switch
        BaseState state;
        if(orderType==1){
            //TODO 这个是临时得
            state = SimpleOrderStatus.getOrderStatusFromCode(orderEntity.getOrderInfo().getStatus());
        }else if(orderType==2){
            //TODO 这个是临时得
            state = DepositOrderStatus.getOrderStatusFromCode(orderEntity.getOrderInfo().getStatus());
        }else{
            throw new RuntimeException();
        }

        return new DefaultStateMachineContext<>(
                new ArrayList<>(),
                state,
                null,
                null,
                new DefaultExtendedState(),
                null,
                contextObj);
    }
}
