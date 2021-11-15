package zjt.learn.model.command;

import lombok.Data;
import zjt.learn.model.base.Command;

/**
 * 功能：
 * 下单命令
 * @Author: zhaojiatao
 * @Date: 2021/10/24 20:33
 * @ClassName: MakeOrderCommand
 * @Version: 1.0.0
 */

@Data
public class MakeOrderCommand extends Command {
    private Integer count;
    private Long itemId;
    private String itemCode;
    private Long userId;


}
