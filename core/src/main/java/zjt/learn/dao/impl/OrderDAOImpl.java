package zjt.learn.dao.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import zjt.learn.dao.IOrderDAO;
import zjt.learn.dto.MakeOrderDTO;

import java.util.UUID;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/12 11:16
 * @ClassName: OrderDAOImpl
 */
@Slf4j
@Repository
public class OrderDAOImpl implements IOrderDAO {
    @Override
    public Long save(MakeOrderDTO makeOrderDTO) {
        log.info("makeOrderDTO 存入数据库", JSON.toJSONString(makeOrderDTO));
        return UUID.randomUUID().getMostSignificantBits();
    }
}
