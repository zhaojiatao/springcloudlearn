package zjt.learn.cqrs;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import zjt.learn.model.base.Command;
import zjt.learn.model.base.Response;


/**
 * 功能：
 */
@FeignClient(name = "core")
@RequestMapping("/core/commandGateWay")
public interface ICqrsCommand {

    /**
     * 命令执行器
     *
     * @param command
     * @return
     */
    @PostMapping(value = "/execute")
    <T> Response<T> execute(@RequestBody Command command);

}
