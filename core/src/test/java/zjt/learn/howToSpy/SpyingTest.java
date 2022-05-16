package zjt.learn.howToSpy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * 功能：
 * 被spy的对象,除了被stubbing的逻辑之外,都默认走真实方法
 * @Author: zhaojiatao
 * @Date: 2022/5/16 14:05
 * @ClassName: SpyingTest
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    @Test
    public void testSpy(){
        List<String> realList=new ArrayList<>();
        List<String> list = spy(realList);

        list.add("Mockito");
        list.add("PowerMock");

        assertThat(list.get(0),equalTo("Mockito"));
        assertThat(list.get(1),equalTo("PowerMock"));
        assertThat(list.isEmpty(),equalTo(false));

        when(list.isEmpty()).thenReturn(true);//仅此方法被mock，其余走真实逻辑
        when(list.size()).thenReturn(0);//仅此方法被mock，其余走真实逻辑
        assertThat(list.get(0),equalTo("Mockito"));
        assertThat(list.get(1),equalTo("PowerMock"));
        assertThat(list.isEmpty(),equalTo(true));
        assertThat(list.size(),equalTo(0));
    }
}
