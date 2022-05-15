package zjt.learn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import zjt.learn.dto.MakeOrderDTO;

/**
 * 功能：
 *
 */
@FeignClient("core-service")
@RequestMapping("/orderService")
public interface IOrderService {

    @GetMapping(value = "/queryById")
    MakeOrderDTO query(@RequestParam(value = "id") Long id);

    @PostMapping(value = "makeOrder")
    MakeOrderDTO makeOrder(MakeOrderDTO makeOrderDTO);
}
