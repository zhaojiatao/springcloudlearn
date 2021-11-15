package zjt.learn.infrastructure.cqrs.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zjt.learn.infrastructure.cqrs.CqrsRegister;
import zjt.learn.infrastructure.cqrs.IQueryBus;
import zjt.learn.infrastructure.cqrs.MethodBean;
import zjt.learn.model.base.Query;
import java.util.Map;


/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 22:12
 * @ClassName: QueryBusImpl
 * @Version: 1.0.0
 */
@Component
public class QueryBusImpl implements IQueryBus {
    @Autowired
    private CqrsRegister cqrsRegister;


    @Override
    public <T> T send(Query query) {
        Map<Class<? extends Query>, MethodBean> queryMaps = cqrsRegister.getQueryMaps();
        MethodBean methodBean = queryMaps.get(query.getClass());
        try {
            return (T) methodBean.getMethod().invoke(methodBean.getObject(), query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
