package zjt.learn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zjt.learn.service.IdGenerateService;

import java.util.UUID;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/12 11:07
 * @ClassName: IdGenerateAdapterImple
 */
@Service
@Slf4j
public class IdGenerateServiceImpl implements IdGenerateService {
    @Override
    public Long generate() {
        log.info("真正调用generate");
        return UUID.randomUUID().getMostSignificantBits();
    }
}
