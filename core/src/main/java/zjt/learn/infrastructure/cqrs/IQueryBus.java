package zjt.learn.infrastructure.cqrs;


import zjt.learn.model.base.Query;

/**
 * 功能：
 * querybus
 * @Author: zhaojiatao
 * @Date: 2021/10/24 22:08
 * @ClassName: IQueryBus
 * @Version: 1.0.0
 */
public interface IQueryBus {

     <T> T send(Query query);

}
