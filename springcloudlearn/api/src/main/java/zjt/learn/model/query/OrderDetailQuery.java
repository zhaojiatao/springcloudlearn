package zjt.learn.model.query;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zjt.learn.model.base.Query;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 22:01
 * @ClassName: OrderDetailQuery
 * @Version: 1.0.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDetailQuery extends Query {
    /**
     * 订单id
     */
    private Long id;
}
