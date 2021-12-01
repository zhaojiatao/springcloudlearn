package zjt.learn.application.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zjt.learn.cqrs.ICqrsCommand;
import zjt.learn.infrastructure.cqrs.ICommandBus;
import zjt.learn.model.base.Command;
import zjt.learn.model.base.Response;

/**
 * 功能：
 * command接收
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 21:09
 * @ClassName: CqrsCommandGateWay
 * @Version: 1.0.0
 */
@RestController
@Slf4j
public class CqrsCommandGateWay implements ICqrsCommand {
    @Autowired
    private ICommandBus commandBus;


    /**
     * 使用bus发送出去
     * @param command
     * @return
     */
    @Override
//  @Transactional(rollbackFor = Exception.class)
    public <T> Response<T> execute(@RequestBody Command command) {
        try{
            return Response.ok(commandBus.send(command));
        }catch (Exception e){
            //当异常被捕获时手动回滚事务
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("执行命令发生异常",e);
            return Response.fail(e.getMessage());
        }

    }
}
