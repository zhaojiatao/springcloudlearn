package zjt.learn.application.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zjt.learn.cqrs.ICqrsQuery;
import zjt.learn.infrastructure.cqrs.IQueryBus;
import zjt.learn.model.base.Query;
import zjt.learn.model.base.Response;

/**
 * 功能：
 * query接收
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 21:12
 * @ClassName: CqrsQueryGateWay
 * @Version: 1.0.0
 */
@RestController
@Slf4j
public class CqrsQueryGateWay implements ICqrsQuery {
    @Autowired
    private IQueryBus queryBus;

    /**
     * 使用bus发送出去
     * @param query
     * @return
     */
    @Override
    public <T> Response<T> query(@RequestBody Query query) {

        try{
            return Response.ok(queryBus.send(query));
        }catch (Exception e){
            log.error("发送query异常",e);
            return Response.fail(e.getMessage());
        }


    }
}
