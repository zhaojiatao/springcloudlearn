package zjt.learn.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zjt.learn.common.utils.RespHelper;
import zjt.learn.cqrs.ICqrsCommand;
import zjt.learn.cqrs.ICqrsQuery;
import zjt.learn.model.command.CloseOrderCommand;
import zjt.learn.model.command.DeliveryOrderCommand;
import zjt.learn.model.command.MakeOrderCommand;
import zjt.learn.model.command.PayOrderCommand;
import zjt.learn.model.dto.OrderInfoDTO;
import zjt.learn.model.query.OrderDetailQuery;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 22:21
 * @ClassName: TestController
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestCqrsController {
    @Autowired
    public ICqrsQuery queryGateway;
    @Autowired
    public ICqrsCommand cqrsCommand;


    /**
     * 模拟查询
     * query：对订单进行读操作
     * @return
     */
    @RequestMapping(value = "/testQuery", method = RequestMethod.GET)
    public OrderInfoDTO testQuery(){
        OrderDetailQuery query=new OrderDetailQuery();
        query.setId(1L);
        return RespHelper.or500(queryGateway.query(query));
    }


    /**
     * 模拟命令
     * command：对订单实体进行写操作相关
     * @return
     */
    @RequestMapping(value = "/testCommand", method = RequestMethod.GET)
    public Long testCommand(){
        //模拟下单
        MakeOrderCommand command=new MakeOrderCommand();
        command.setCount(2);
        command.setItemId(111L);
        command.setUserId(123L);
        return RespHelper.or500(cqrsCommand.execute(command));
    }

    @RequestMapping(value = "/testCloseCommand", method = RequestMethod.GET)
    public Boolean testCloseCommand(){
        //模拟下单
        CloseOrderCommand command=new CloseOrderCommand();
        command.setId(1L);
        return RespHelper.or500(cqrsCommand.execute(command));
    }


    @RequestMapping(value = "/payOrderCommand", method = RequestMethod.GET)
    public Boolean payOrderCommand(@RequestParam Long id){
        PayOrderCommand command=new PayOrderCommand();
        command.setId(id);
        return RespHelper.or500(cqrsCommand.execute(command));
    }


    @RequestMapping(value = "/deliveryOrderCommand", method = RequestMethod.GET)
    public Boolean deliveryOrderCommand(@RequestParam  Long id){
        DeliveryOrderCommand command=new DeliveryOrderCommand();
        command.setId(id);
        return RespHelper.or500(cqrsCommand.execute(command));
    }







}
