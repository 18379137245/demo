package ding;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@SpringBootApplication
@MapperScan("ding.mapper")
@EnableCaching
@ControllerAdvice
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class,args);
        log.info("项目启动成功...");
    }
}
