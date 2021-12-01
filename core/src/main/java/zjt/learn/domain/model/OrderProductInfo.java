package zjt.learn.domain.model;

import java.io.Serializable;

/**
 * 功能：
 * 子实体
 * @Author: zhaojiatao
 * @Date: 2021/10/26 10:16
 * @ClassName: OrderProductInfo
 */
public class OrderProductInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    private Long orderId;

    /**
     * 产品code
     */
    private String productCode;

    /**
     * 数量
     */
    private Integer count;
}
