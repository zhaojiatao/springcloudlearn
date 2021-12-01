package zjt.learn.infrastructure.cqrs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/24 21:19
 * @ClassName: QueryHandler
 * @Version: 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryHandler {
}
