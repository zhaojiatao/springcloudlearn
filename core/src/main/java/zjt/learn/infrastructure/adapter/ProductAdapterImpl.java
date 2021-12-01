package zjt.learn.infrastructure.adapter;

import org.springframework.stereotype.Component;
import zjt.learn.domain.adapter.product.IProductAdapter;
import zjt.learn.domain.model.OrderProductInfo;

/**
 * 功能：
 * 防腐层
 * @Author: zhaojiatao
 * @Date: 2021/10/26 10:40
 * @ClassName: ProductAdapterImpl
 */
@Component
public class ProductAdapterImpl implements IProductAdapter {
    @Override
    public OrderProductInfo queryProductByCode(String productCode) {
        //调用商品中心dubbo接口查询产品信息OutProductInfoDTO

        //使用Translators转换为本领域的对象OrderProductInfo

        //OutProductInfoDTO->OrderProductInfo

        return new OrderProductInfo();
    }
}
