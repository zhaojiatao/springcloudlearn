package zjt.learn.model.command;

import lombok.Data;
import zjt.learn.model.base.Command;

/**
 * 功能：
 * 支付
 * @Author: zhaojiatao
 * @Date: 2021/11/7 22:18
 * @ClassName: PayOrderCommand
 * @Version: 1.0.0
 */
@Data
public class PayOrderCommand extends Command {
    private Long id;
}
