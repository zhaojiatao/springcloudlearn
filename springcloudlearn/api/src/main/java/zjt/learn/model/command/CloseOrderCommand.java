package zjt.learn.model.command;

import lombok.Data;
import zjt.learn.model.base.Command;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/26 11:06
 * @ClassName: CloseOrderCommand
 */
@Data
public class CloseOrderCommand extends Command {
    private Long id;
}
