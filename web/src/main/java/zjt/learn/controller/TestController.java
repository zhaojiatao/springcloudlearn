package zjt.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zjt.learn.IOrderService;
import zjt.learn.dto.MakeOrderDTO;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/4/20 15:32
 * @ClassName: TestController
 */
@RestController
@RequestMapping("/query")
public class TestController {
    @Autowired
    private IOrderService orderService;

    /**
     * 查询订单详情
     * http://localhost:8082/query/orderdetail/sadxvsdf
     * @param id
     * @return
     */
    @GetMapping(value = "/orderdetail/{id}")
    public MakeOrderDTO orderDetail(@PathVariable(value = "id") Long id){
        return orderService.query(id);
    }


}
