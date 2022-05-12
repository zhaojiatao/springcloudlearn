package zjt.learn.service.impl;

import org.springframework.stereotype.Service;
import zjt.learn.service.IdGenerateAdapter;

import java.util.UUID;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/12 11:07
 * @ClassName: IdGenerateAdapterImple
 */
@Service
public class IdGenerateAdapterImpl implements IdGenerateAdapter {
    @Override
    public Long generate() {
        return UUID.randomUUID().getMostSignificantBits();
    }
}
