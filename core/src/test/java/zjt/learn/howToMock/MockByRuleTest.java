package zjt.learn.howToMock;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import zjt.learn.service.IdGenerateService;

import static org.mockito.Mockito.mock;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 11:11
 * @ClassName: MockByRuleTest
 */
public class MockByRuleTest {

    /**
     * 使用Rule的方式，这种一般比较少见，一般使用注解方式
     */
    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    @Test
    public void testMock(){
        //mock之后如果不stabbling，则返回默认值
        IdGenerateService idGenerateService=mock(IdGenerateService.class);
        Long generate = idGenerateService.generate();
        System.out.println(generate);
    }

}
