package zjt.learn.model.command;

import lombok.Data;
import zjt.learn.model.base.Command;

/**
 * 功能：
 * 发货
 * @Author: zhaojiatao
 * @Date: 2021/11/7 22:18
 * @ClassName: DeliveryOrderCommand
 * @Version: 1.0.0
 */
@Data
public class DeliveryOrderCommand extends Command {
    private Long id;
}
