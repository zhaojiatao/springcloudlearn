package zjt.learn.infrastructure.repository;


import zjt.learn.domain.model.OrderEntity;

/**
 * 功能：
 * 两个方法是Repository中最常见的方法，有的DDD实践者甚至认为一个纯粹的Repository只应该包含这两个方法。
 * @Author: zhaojiatao
 * @Date: 2021/10/26 10:56
 * @ClassName: IOrderRepository
 */
public interface IOrderRepository {
    Long saveOrderEntity(OrderEntity orderEntity);

    OrderEntity loadOrderEntity(Long id);
}
