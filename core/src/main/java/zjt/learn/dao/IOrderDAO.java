package zjt.learn.dao;

import zjt.learn.dto.MakeOrderDTO;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/12 11:15
 * @ClassName: IOrderDAO
 */
public interface IOrderDAO {


    void save(MakeOrderDTO makeOrderDTO);


    MakeOrderDTO loadFromDB(Long id);

}
