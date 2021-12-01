package zjt.learn.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 功能：
 * 订单实体(聚合根)
 * 聚合根一定是实体对象，但是并不是所有实体对象都是聚合根，
 * 同时聚合根还可以拥有其他子实体对象。
 * 聚合根的ID在整个软件系统中全局唯一，而其下的子实体对象的ID只需在单个聚合根下唯一即可。
 * @Author: zhaojiatao
 * @Date: 2021/10/26 10:08
 * @ClassName: OrderEntity
 */
@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 聚合根的ID在整个软件系统中全局唯一
     */
    private Long id;

    /**
     * 子实体
     * 实体对象表示的是具有一定生命周期并且拥有全局唯一标识(ID)的对象
     */
    private OrderInfo orderInfo;

    /**
     * 子实体
     * 实体对象表示的是具有一定生命周期并且拥有全局唯一标识(ID)的对象
     */
    private OrderProductInfo orderProductInfo;


    public void closeOrder(){
        this.orderInfo.setStatus(-1);
    }



}
