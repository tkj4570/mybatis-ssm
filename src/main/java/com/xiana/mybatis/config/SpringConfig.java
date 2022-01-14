package com.xiana.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


/**
 * @author <a href="mail to: 457066709@qq.com" rel="nofollow">Administrator</a>
 * @version v1.0
 * @package com.xiana.mybatis.config
 * @project mybatis-ssm
 * @description [类型描述]
 * @createTime 2022/1/12 20:19
 */
@Configuration
@ComponentScan(
        value = "com.xiana.mybatis",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes={Service.class})},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes={Controller.class})},
        useDefaultFilters = false
)
@PropertySource("classpath:druid.properties")
public class SpringConfig {

    @Bean("dataSource")
    public DruidDataSource druidDataSource(@Value("${mysql.driverClassName}") String driver, @Value("${mysql.url}") String url, @Value("${mysql.username}") String username, @Value("${mysql.password}") String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    /**
     * 注入mybatis的sqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //注入数据库连接池
        factoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //加入一个映射器
        configuration.addMappers("com.xiana.mybatis.dao");
        //数据库字段转驼峰命名法
        configuration.setMapUnderscoreToCamelCase(true);
        //多个参数的map的key定义方式
        configuration.setUseActualParamName(true);
        //分步查询时是否开启延迟加载 先从单表查询、需要时再从关联表去关联查询
        configuration.setLazyLoadingEnabled(true);
        //懒加载的对象是否任何懒属性全部加载出来
        configuration.setAggressiveLazyLoading(false);
        //开启2级缓存
        configuration.setCacheEnabled(true);

        //查找mapper.xml文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mapper/*.xml");
        factoryBean.setMapperLocations(resources);

        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.xiana.mybatis.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }


}
