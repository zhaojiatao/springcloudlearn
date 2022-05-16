package zjt.learn.howToSpy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * 功能：
 * 在对遗留代码进行重构时往往可以选择使用spy模式
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 14:13
 * @ClassName: SpyingAnnotationTest
 */
public class SpyingAnnotationTest {

    @Spy
    private List<String> list=new ArrayList<>();

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSpy(){

        list.add("Mockito");
        list.add("PowerMock");

        assertThat(list.get(0),equalTo("Mockito"));
        assertThat(list.get(1),equalTo("PowerMock"));
        assertThat(list.isEmpty(),equalTo(false));


        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(0);
        assertThat(list.get(0),equalTo("Mockito"));
        assertThat(list.get(1),equalTo("PowerMock"));
        assertThat(list.isEmpty(),equalTo(true));
        assertThat(list.size(),equalTo(0));


    }


}
