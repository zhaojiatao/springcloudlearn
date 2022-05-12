package zjt.learn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zjt.learn.ITestService;
import zjt.learn.dao.IOrderDAO;
import zjt.learn.dto.MakeOrderDTO;
import zjt.learn.service.IdGenerateAdapter;

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
public class TestServiceImpl implements ITestService {

    @Autowired
    private IdGenerateAdapter idGenerateAdapter;

    @Resource
    private IOrderDAO orderDAO;

    public static String buildName(String str){
        return str+"CC";
    }


    @Override
    public String query(@RequestParam(value = "id") String id) {
        log.info("服务端接受到请求：id={}",id);
        return "服务端返回："+id;
    }

    @Override
    public Long makeOrder(MakeOrderDTO makeOrderDTO) {
        if(Objects.isNull(makeOrderDTO)){
            throw new RuntimeException("不可为空");
        }
        Long id = idGenerateAdapter.generate();

        makeOrderDTO.setId(id);

        Long result = orderDAO.save(makeOrderDTO);

        return result;
    }
}
