package zjt.learn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * <p>
 * 功能：spring security basic认证配置类
 * 用户名和密码项目全局配置一致，方便统一管理，减少因账号不一样引起的服务间调用复杂性
 * </p>
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter   {
    private static Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    /**
     * spring security 认证用户名
     */
    @Value("${spring.security.user.name}")
    private String username;
    /**
     * spring security 认证密码
     */
    @Value("${spring.security.user.password}")
    private String password;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        //关闭springboot2.0 security认证
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll()
//                .and().logout().permitAll();
        http.authorizeRequests()
                .antMatchers("/metrics/prometheus").permitAll()
                .and()
                .csrf()
                .disable();
        // 禁用session存值，防止内存溢出
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        super.configure(http);
    }

    /**
     * 启动时加载用户spring security basic自定义认证信息
     * @return
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // "USER"角色无实际业务意义，项目中命名全局一致即可
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        manager.createUser(User.withUsername(username).password(passwordEncoder.encode(password)).roles("USER").build());
        return manager;
    }

}
