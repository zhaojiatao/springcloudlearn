package zjt.learn.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * 功能：
 *
 */
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * springCloud-openFegin底层使用JackSon在序列化布尔类型值时，会导致无法正确反序列化的问题，使用该注解解决
     */
    @JsonProperty("success")
    private boolean success;

    /**
     * 用Jackson序列化， 如果存入对象 包含 泛型，那么 默认情况下，这个泛型对象会被Jackson反序列为 LinkedHashMap . 抛出类型转换异常
     */
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,include = JsonTypeInfo.As.PROPERTY,property = "@class")
    private T result;

    private String error;


    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.success = false;
        this.error = error;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("success", this.success).add("result", this.result).add("error", this.error).omitNullValues().toString();
    }

    public static <T> Response<T> ok(T data) {
        Response<T> resp = new Response();
        resp.setResult(data);
        return resp;
    }

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> fail(String error) {
        Response<T> resp = new Response();
        resp.setError(error);
        return resp;
    }
}