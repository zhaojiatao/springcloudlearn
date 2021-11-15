package zjt.learn.model.command;

import lombok.Data;
import zjt.learn.model.base.Command;

/**
 * 功能：
 * 收货
 * @Author: zhaojiatao
 * @Date: 2021/11/7 22:19
 * @ClassName: ReceivedOrderCommand
 * @Version: 1.0.0
 */
@Data
public class ReceivedOrderCommand extends Command {
    private Long id;
}
