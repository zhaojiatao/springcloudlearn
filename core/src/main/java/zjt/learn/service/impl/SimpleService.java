package zjt.learn.service.impl;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 16:12
 * @ClassName: SimpleService
 */
public class SimpleService {
    public int method1(int i, String s, Collection<?> c, Serializable ser){
        throw new RuntimeException();
    }

    public void method2(int i, String s, Collection<?> c, Serializable ser){
        throw new RuntimeException();
    }

    public final String testFinal(){
        throw new RuntimeException();
    }

    public String getPrivateResult(String params){
        return testPrivate(params);
    }

    private String testPrivate(String params){
        return "I am a private result";
    }

}
