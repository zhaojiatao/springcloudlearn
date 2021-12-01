package zjt.learn.query.repository;


import zjt.learn.model.dto.OrderInfoDTO;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/26 13:15
 * @ClassName: IOrderQueryRepository
 */
public interface IOrderQueryRepository {
    OrderInfoDTO queryOrderByOrderId(Long id);

    OrderInfoDTO queryOrderByOrderNo(String orderNo);
}
