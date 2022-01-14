package com.xiana.mybatis.config;

import com.xiana.mybatis.interceptor.FirstInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mail to: 457066709@qq.com" rel="nofollow">Administrator</a>
 * @version v1.0
 * @package com.xiana.mybatis.config
 * @project mybatis-ssm
 * @description [类型描述]
 * @createTime 2022/1/12 20:19
 */

/**
 * 1.IOC容器自动封装 2.thymeleaf视图解析器 3.view-controller视图控制器 4.default-handler-servlet默认servlet静态资源
 * 5.mvc注解驱动 6.上传解析器 7.interceptor拦截器 8.自定义异常解析器
 */
@Configuration
@ComponentScan(
    value = "com.xiana.mybatis",
    includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes={Controller.class})},
    useDefaultFilters = false
)
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {
    @Bean // 配置生成模板解析器
    public ITemplateResolver iTemplateResolver(){
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean // 生成模板引擎并为模板引擎注入模板解析器
    public SpringTemplateEngine templateEngine(ITemplateResolver iTemplateResolver){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(iTemplateResolver);
        return templateEngine;
    }

    @Bean // 生成视图解析器并未解析器注入模板引擎
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    // 3.view-controller视图解析器
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    // 4.default-handler-servlet静态文件servlet
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 6.文件上传解析器
    @Bean
    public MultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }

    //7.拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        FirstInterceptor firstInterceptor = new FirstInterceptor();
        registry.addInterceptor(firstInterceptor).addPathPatterns("/**").excludePathPatterns("/");
    }

    //8.自定义异常解析器
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException","error");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        simpleMappingExceptionResolver.setExceptionAttribute("ex");
        resolvers.add(simpleMappingExceptionResolver);
    }
}
