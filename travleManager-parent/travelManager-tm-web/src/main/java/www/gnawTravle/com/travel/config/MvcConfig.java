package www.gnawTravle.com.travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: travleManager-parent
 * @description:
 * @author: wang_sir
 * @create: 2020-06-11 20:19
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 切换语言
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
}
