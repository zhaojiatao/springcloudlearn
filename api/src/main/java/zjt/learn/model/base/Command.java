package zjt.learn.model.base;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * 功能：
 *
 */

/**
 * 指定使用的类名称（use = JsonTypeInfo.Id.CLASS），并将其作为JSON属性保留（include = JsonTypeInfo.As.PROPERTY）。 属性名称指定为’className’。
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "className"
)
public class Command implements Serializable {
    private static final long serialVersionUID = 1L;

}
