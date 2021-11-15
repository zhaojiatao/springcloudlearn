package zjt.learn.infrastructure.cqrs;


import zjt.learn.model.base.Command;

/**
 * 功能：
 * 命令bus
 * @Author: zhaojiatao
 * @Date: 2021/10/24 22:08
 * @ClassName: ICommandBus
 * @Version: 1.0.0
 */
public interface ICommandBus {
    <T> T send(Command command);
}
