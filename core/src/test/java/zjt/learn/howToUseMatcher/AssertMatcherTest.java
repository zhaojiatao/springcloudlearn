package zjt.learn.howToUseMatcher;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/16 16:55
 * @ClassName: AssertMatcherTest
 */
public class AssertMatcherTest {


    @Test
    public void test(){
        int i=10;
        assertThat(i,equalTo(10));
        assertThat(i,not(equalTo(20)));
        assertThat(i,is(10));
        assertThat(i,is(not(20)));

        double price=23.45;
        assertThat(price,either(equalTo(23.45)).or(equalTo(23.54)));
        assertThat(price,both(equalTo(23.45)).and(not(equalTo(23.54))));

    }

    @Test
    public void test2(){
        double price=23.45;
        assertThat(price,either(equalTo(23.45)).or(equalTo(23.54)));
        assertThat(price,both(equalTo(23.45)).and(not(equalTo(23.54))));
        assertThat(price, anyOf(is(23.45),is(23.54)));
        assertThat(price, anyOf(is(23.45),not(is(23.54)),not(35.24)));

        assertThat(Stream.of(1,2,3).anyMatch(i->i>2),equalTo(true));
        assertThat(Stream.of(1,2,3).anyMatch(i->i>0),equalTo(true));

    }


    /*
    @Test
    public void test3(){
        double price=23.45;
        //如果断言失败，打印自定义msg
        assertThat("the double value assertion failed.",price,either(equalTo(23.54)).or(equalTo(23.66)));
    }
    */



}
