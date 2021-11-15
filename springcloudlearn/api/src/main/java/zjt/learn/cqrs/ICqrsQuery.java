package zjt.learn.cqrs;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import zjt.learn.model.base.Query;
import zjt.learn.model.base.Response;


/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 20:35
 * @ClassName: ICqrsQuery
 * @Version: 1.0.0
 */
@FeignClient(name = "core")
@RequestMapping("/core/commandGateWay")
public interface ICqrsQuery {

    /**
     * 执行查询命令
     *
     * @param query
     * @return
     */
    @PostMapping(value = "/query")
    <T> Response<T> query(@RequestBody Query query);
}
