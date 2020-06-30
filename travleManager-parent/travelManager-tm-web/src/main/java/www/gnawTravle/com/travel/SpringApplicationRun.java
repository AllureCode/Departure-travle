package www.gnawTravle.com.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: travleManager-parent
 * @description: 启动类
 * @author: wang_sir
 * @create: 2020-06-09 16:08
 **/
@SpringBootApplication(scanBasePackages = "www.gnawTravle.com.travel.*")
@MapperScan(basePackages = "www.gnawTravle.com.travel.mapper")
public class SpringApplicationRun {

    public static void main(String[] args) {

        SpringApplication.run(SpringApplicationRun.class,args);
    }
}
