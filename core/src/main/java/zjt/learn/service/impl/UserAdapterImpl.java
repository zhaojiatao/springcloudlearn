package zjt.learn.service.impl;

import org.springframework.stereotype.Service;
import zjt.learn.service.IUserAdapter;


/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/15 22:21
 * @ClassName: UserAdapterImp
 */
@Service
public class UserAdapterImpl implements IUserAdapter {
    @Override
    public String queryUserRealNameByUserName(String userName) {
        return "real"+userName;
    }
}
