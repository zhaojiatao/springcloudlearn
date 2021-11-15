package zjt.learn.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zjt.learn.domain.model.valueobject.Address;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能：
 * 子实体
 * @Author: zhaojiatao
 * @Date: 2021/10/26 10:10
 * @ClassName: OrderInfo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单类型
     */
    private Long orderType;

    /**
     * 订单编码
     */
    @ApiModelProperty(name = "orderSerial", value = "订单编码")
    private String orderSerial;

    /**
     * 租户 ID
     */
    @ApiModelProperty(name = "tenantId", value = "租户ID")
    private Long tenantId;

    /**
     * 采购商 ID
     */
    @ApiModelProperty(name = "purchaserId", value = "采购商ID")
    private Long purchaserId;

    /**
     * 实付价
     */
    @ApiModelProperty(name = "fee", value = "实付价")
    private BigDecimal fee;
    /**
     * 状态
     */
    @ApiModelProperty(name = "status", value = "状态")
    private Integer status;

    /**
     * 收货地址
     * 典型的值对象
     * 值对象表示用于起描述性作用的，没有唯一标识的对象
     */
    private Address receiveAddress;
}
