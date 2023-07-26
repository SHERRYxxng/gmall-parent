package sherry.taobao.gmall.product;


import org.mybatis.spring.annotation.MapperScan;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import sherry.taobao.gmall.common.constant.RedisConst;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"sherry.taobao.gmall"})
@MapperScan({"sherry.taobao.gmall.product.mapper"})
public class ServiceProductApplication implements CommandLineRunner {

    @Autowired
    private RedissonClient redissonClient;

    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class,args);
    }

    /**
     * 初始化布隆过滤器
     * 执行时机，在应用程序初始化完成后执行
     * @param args incoming main method arguments
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        //获取布隆过滤器
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(RedisConst.SKU_BLOOM_FILTER);
        //设置预计存储数据量和设置误差率
        bloomFilter.tryInit(10000l,0.01);


    }
}
