package zjt.learn.model.base;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * 功能：
 */


@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "className"
)
public class Query implements Serializable {
    private static final long serialVersionUID = 1L;
}
