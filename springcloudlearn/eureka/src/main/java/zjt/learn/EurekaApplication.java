package zjt.learn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 通过@EnableEurekaServer使这个工程变成Eureka注册中心
 * Eureka是一个高可用组件，它没有后端缓存，每一个示例注册之后需要向注册中心发送心跳
 * （因此可以再内存中完成），在默认情况下Eureka server也是一个eureka client,必须要指定
 * 一个server。Eureka server的配置文件application.yml,具体配置到application.yml中查看
 * 访问Eureka可以通过：http://${eureka.instance.hostname}:${server.port}  查看Eureka浏览页面
 *
 * @author zhaojiatao
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}

