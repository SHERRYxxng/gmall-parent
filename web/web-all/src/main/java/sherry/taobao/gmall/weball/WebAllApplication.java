package sherry.taobao.gmall.weball;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/8/2 10:48
 */
@ComponentScan({"sherry.taobao.gmall"})
@SpringBootApplication
@ComponentScan({"sherry.taobao.gmall"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages= {"sherry.taobao.gmall"})
public class WebAllApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAllApplication.class, args);
    }
}
