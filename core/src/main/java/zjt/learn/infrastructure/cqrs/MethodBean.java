package zjt.learn.infrastructure.cqrs;

import java.lang.reflect.Method;

public class MethodBean {
    private final Object object;
    private final Method method;

    public MethodBean(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
