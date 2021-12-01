package zjt.learn.infrastructure.cqrs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zjt.learn.infrastructure.cqrs.CqrsRegister;
import zjt.learn.infrastructure.cqrs.ICommandBus;
import zjt.learn.infrastructure.cqrs.MethodBean;
import zjt.learn.model.base.Command;

import java.util.Map;


/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 22:12
 * @ClassName: CommandBusImpl
 * @Version: 1.0.0
 */
@Component
public class CommandBusImpl implements ICommandBus {
    @Autowired
    private CqrsRegister cqrsRegister;
    @Override
    public <T> T send(Command command) {
        Map<Class<? extends Command>, MethodBean> commandMaps = cqrsRegister.getCommandMaps();
        MethodBean methodBean = commandMaps.get(command.getClass());
        try {
            return (T) methodBean.getMethod().invoke(methodBean.getObject(), command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
