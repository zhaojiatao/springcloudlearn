package zjt.learn.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjt.learn.domain.adapter.product.IProductAdapter;
import zjt.learn.domain.model.OrderEntity;
import zjt.learn.domain.model.OrderInfo;
import zjt.learn.domain.model.OrderProductInfo;
import zjt.learn.infrastructure.cqrs.CommandHandler;
import zjt.learn.infrastructure.cqrs.Handler;
import zjt.learn.infrastructure.repository.IOrderRepository;
import zjt.learn.infrastructure.statemachine.base.event.PushOrderEvent;
import zjt.learn.infrastructure.statemachine.base.eventbus.IOrderEventBus;
import zjt.learn.model.command.*;
import zjt.learn.statemashine.depositorder.DepositOrderEvent;
import zjt.learn.statemashine.simpleorder.SimpleOrderEvent;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 21:14
 * @ClassName: OrderAppService
 * @Version: 1.0.0
 */
@Service
@Handler
public class OrderAppService {


    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductAdapter productAdapter;

    @Autowired
    private IOrderEventBus orderEventBus;


    /**
     * 下单
     * @param command
     * @return
     */
    @CommandHandler
    public Long makeOrder(MakeOrderCommand command){
        Integer count = command.getCount();
        Long itemId = command.getItemId();
        Long userId = command.getUserId();
        System.out.println(count);
        System.out.println(itemId);
        System.out.println(userId);

        //1、校验参数
        //ValidationUtil.validate(command);
        //2、业务校验
        //XXX
        //3、执行下单逻辑

        //构造orderInfo
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setId(1L);

        //构造productInfo
        OrderProductInfo productInfo =productAdapter.queryProductByCode(command.getItemCode());

        OrderEntity entity=OrderEntity.builder()
                .orderInfo(orderInfo)
                .orderProductInfo(productInfo)
                .id(orderInfo.getId())
                .build();

        orderRepository.saveOrderEntity(entity);

        return entity.getId();
    }


    @CommandHandler
    public Boolean closeOrder(CloseOrderCommand command){
        Long id = command.getId();
        OrderEntity entity = orderRepository.loadOrderEntity(id);
        entity.closeOrder();
        orderRepository.saveOrderEntity(entity);
        return true;
    }


    @CommandHandler
    public Boolean payOrder(PayOrderCommand command){
        Long id = command.getId();
        OrderEntity entity = orderRepository.loadOrderEntity(id);
        if(entity.getOrderInfo().getOrderType()==1){
            orderEventBus.submitEvent(new PushOrderEvent(id, SimpleOrderEvent.PAYED));
        }else if(entity.getOrderInfo().getOrderType()==2){
            orderEventBus.submitEvent(new PushOrderEvent(id, DepositOrderEvent.PAYED_DEPOSIT));
        }

        return true;
    }

    @CommandHandler
    public Boolean deliveryOrder(DeliveryOrderCommand command){
        Long id = command.getId();
        OrderEntity entity = orderRepository.loadOrderEntity(id);
        if(entity.getOrderInfo().getOrderType()==1){
            orderEventBus.submitEvent(new PushOrderEvent(id, SimpleOrderEvent.DELIVERY));
        }else if(entity.getOrderInfo().getOrderType()==2){
            orderEventBus.submitEvent(new PushOrderEvent(id, DepositOrderEvent.DELIVERY_DEPOSIT));
        }
        return true;
    }

    @CommandHandler
    public Boolean receivedOrder(ReceivedOrderCommand command){
        Long id = command.getId();
        OrderEntity entity = orderRepository.loadOrderEntity(id);
        if(entity.getOrderInfo().getOrderType()==1){
            orderEventBus.submitEvent(new PushOrderEvent(id, SimpleOrderEvent.RECEIVED));
        }else if(entity.getOrderInfo().getOrderType()==2){
            orderEventBus.submitEvent(new PushOrderEvent(id, DepositOrderEvent.RECEIVED_DEPOSIT));
        }
        return true;
    }




}
