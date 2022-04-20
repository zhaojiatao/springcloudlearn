package zjt.learn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zjt.learn.IQueryService;

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
public class QueryServiceImpl implements IQueryService {
    @Override
    public String query(@RequestParam(value = "id") String id) {
        log.info("服务端接受到请求：id={}",id);
        return "服务端返回："+id;
    }
}
