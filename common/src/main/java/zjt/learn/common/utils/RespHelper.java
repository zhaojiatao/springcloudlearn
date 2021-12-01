package zjt.learn.common.utils;


import zjt.learn.common.exceptions.JsonResponseException;
import zjt.learn.model.base.Response;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/6 21:48
 * @ClassName: RespHelper
 * @Version: 1.0.0
 */
public class RespHelper {
    public static <T> T or(Response<T> resp, T failValue) {
        return resp.isSuccess() ? resp.getResult() : failValue;
    }

    public static Boolean orFalse(Response<Boolean> resp) {
        return or(resp, Boolean.FALSE);
    }

    public static <T> T or500(Response<T> resp) {
        if (resp.isSuccess()) {
            return resp.getResult();
        }
        throw new JsonResponseException(500, resp.getError());
    }
}
