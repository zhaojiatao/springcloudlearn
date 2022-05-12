package zjt.learn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import zjt.learn.dto.MakeOrderDTO;

/**
 * 功能：
 *
 */
@FeignClient("core-service")
@RequestMapping("/testService")
public interface ITestService {

    @GetMapping(value = "/queryById")
    String query(@RequestParam(value = "id") String id);

    @PostMapping(value = "makeOrder")
    Long makeOrder(MakeOrderDTO makeOrderDTO);
}
