package zjt.learn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zjt.learn.IOrderService;
import zjt.learn.dao.IOrderDAO;
import zjt.learn.dto.MakeOrderDTO;
import zjt.learn.service.IUserAdapter;
import zjt.learn.service.IdGenerateService;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/4/20 15:36
 * @ClassName: QueryServiceImpl
 */
@Slf4j
@Service
@ResponseBody
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IdGenerateService idGenerateService;

    @Resource
    private IOrderDAO orderDAO;

    @Autowired
    private IUserAdapter userAdapter;

    public static String buildName(String str){
        return str+"CC";
    }


    @Override
    public MakeOrderDTO query(@RequestParam(value = "id") Long id) {
        log.info("服务端接受到请求：id={}",id);
        return orderDAO.loadFromDB(id);
    }

    @Override
    public MakeOrderDTO makeOrder(MakeOrderDTO makeOrderDTO) {
        if(Objects.isNull(makeOrderDTO)){
            throw new RuntimeException("不可为空");
        }

        if(StringUtils.isNotBlank(makeOrderDTO.getUserName())){
            //查询外部服务：根据userName查询真实姓名
            String realName = userAdapter.queryUserRealNameByUserName(makeOrderDTO.getUserName());
            makeOrderDTO.setUserRealName(realName);
        }

        //调用外部服务生成主键
        Long id = idGenerateService.generate();
        makeOrderDTO.setId(id);

        //调用数据库落地
        orderDAO.save(makeOrderDTO);

        return makeOrderDTO;
    }
}
