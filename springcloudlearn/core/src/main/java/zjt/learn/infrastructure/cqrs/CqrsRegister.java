package zjt.learn.infrastructure.cqrs;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import zjt.learn.model.base.Command;
import zjt.learn.model.base.Query;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 21:24
 * @ClassName: CqrsRegister
 * @Version: 1.0.0
 */
@Slf4j
@Component
@Order(value=Integer.MAX_VALUE)
public class CqrsRegister implements ApplicationContextAware {
    private Map<Class<? extends Command>,MethodBean> commandMaps = new ConcurrentHashMap<>();
    private Map<Class<? extends Query>,MethodBean> queryMaps = new ConcurrentHashMap<>();

    public  Map<Class<? extends Command>,MethodBean> getCommandMaps(){
        return this.commandMaps;
    }

    public  Map<Class<? extends Query>,MethodBean> getQueryMaps(){
        return this.queryMaps;
    }



    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> handlers = applicationContext.getBeansWithAnnotation(Handler.class);
        Iterator var3 = handlers.entrySet().iterator();

        while(true) {
            Object bean;
            do {
                if (!var3.hasNext()) {
                    return;
                }

                Map.Entry<String, Object> entry = (Map.Entry)var3.next();
                bean = AopTargetUtils.getTarget(entry.getValue());
            } while(bean == null);

            Method[] methods = bean.getClass().getDeclaredMethods();
            Method[] var7 = methods;
            int var8 = methods.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                Method method = var7[var9];

                if (method.getParameterTypes().length == 1 && method.isAnnotationPresent(CommandHandler.class)) {
                    method.setAccessible(true);
                    @SuppressWarnings("unchecked")
                    Class<? extends Command> command = (Class<? extends Command>) method.getParameterTypes()[0];
                    MethodBean methodBean = new MethodBean(bean, method);

                    if (commandMaps.containsKey(command)) {
                        throw new RuntimeException(String.format("存在多出对command=%s的处理", command.getName()));
                    } else {
                        commandMaps.put(command, methodBean);
                    }
                }

                if (method.getParameterTypes().length == 1 && method.isAnnotationPresent(QueryHandler.class)) {
                    method.setAccessible(true);
                    @SuppressWarnings("unchecked")
                    Class<? extends Query> query = (Class<? extends Query>) method.getParameterTypes()[0];
                    MethodBean methodBean = new MethodBean(bean, method);

                    if (queryMaps.containsKey(query)) {
                        throw new RuntimeException(String.format("存在多出对query=%s的处理", query.getName()));
                    } else {
                        queryMaps.put(query, methodBean);
                    }
                }


            }

            log.info("commandMaps={}",JSON.toJSONString(commandMaps));
            log.info("queryMaps={}",JSON.toJSONString(queryMaps));


        }

    }
}
