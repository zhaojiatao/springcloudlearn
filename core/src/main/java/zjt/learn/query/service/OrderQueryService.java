package zjt.learn.query.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjt.learn.infrastructure.cqrs.Handler;
import zjt.learn.infrastructure.cqrs.QueryHandler;
import zjt.learn.model.dto.OrderInfoDTO;
import zjt.learn.model.query.OrderDetailQuery;
import zjt.learn.query.repository.IOrderQueryRepository;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/26 13:13
 * @ClassName: OrderQueryService
 */
@Service
@Handler
public class OrderQueryService {

    @Autowired
    private IOrderQueryRepository orderQueryRepository;

    @QueryHandler
    public OrderInfoDTO orderDetailQuery(OrderDetailQuery query){
        System.out.println(JSON.toJSONString(query));
        OrderInfoDTO orderInfoDTO = orderQueryRepository.queryOrderByOrderId(query.getId());
        return orderInfoDTO;
    }

}
