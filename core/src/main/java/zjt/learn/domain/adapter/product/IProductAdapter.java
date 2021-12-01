package zjt.learn.domain.adapter.product;


import zjt.learn.domain.model.OrderProductInfo;

/**
 * 功能：
 *
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/26 10:37
 * @ClassName: IProductAdapter
 */
public interface IProductAdapter {
    OrderProductInfo queryProductByCode(String productCode);
}
