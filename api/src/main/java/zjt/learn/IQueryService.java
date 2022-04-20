package zjt.learn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 *
 */
@FeignClient("core-service")
@RequestMapping("/queryService")
public interface IQueryService {

    @GetMapping(value = "/queryById")
    String query(@RequestParam(value = "id") String id);
}
