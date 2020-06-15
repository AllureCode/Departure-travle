package www.gnawTravle.com.travel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import www.gnawTravle.com.travel.interceptor.ManagerInterceptor;

/**
 * @program: travleManager-parent
 * @description:
 * @author: wang_sir
 * @create: 2020-06-11 20:19
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter  {
    /**
     * 切换语言
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }


    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new ManagerInterceptor()).addPathPatterns("/manager/**");
    }
}
