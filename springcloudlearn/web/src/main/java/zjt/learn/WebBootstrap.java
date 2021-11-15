package zjt.learn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/5 13:26
 * @ClassName: WebBootstrap
 */
@SpringBootApplication
@EnableFeignClients
public class WebBootstrap extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebBootstrap.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(WebBootstrap.class, args);
    }
}
