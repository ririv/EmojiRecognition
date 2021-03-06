package com.riri.memerecognition.config;

import com.riri.memerecognition.component.StaticPagePathFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    private final StaticPagePathFinder staticPagePathFinder;

    @Autowired
    public WebMvcConfig(StaticPagePathFinder staticPagePathFinder) {
        this.staticPagePathFinder = staticPagePathFinder;
    }

    @Value("${path.base.img}")
    private String imgBasePath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        for (StaticPagePathFinder.PagePaths pagePaths:staticPagePathFinder.findPath()) {
            registry.addViewController(pagePaths.getUrlPath()).setViewName(pagePaths.getViewFilePath());
        }
        registry.addViewController("login").setViewName("signin.html");
        registry.addViewController("register").setViewName("signup.html");
    }

    //自定义静态资源映射目录，将网址映射本地目录
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 必须嘚加入"file:"+ 映射外部资源目录路径
        // "classpath" + 映射的是项目中resources目录
        registry.addResourceHandler("api/v1/" + "file/img/**").addResourceLocations("file:"+ imgBasePath);
        // "classpath" + 映射的是项目中resources目录，这里仅作测试
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:"+ "static");
    }

    //Cors跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry
                .addMapping("/**")
//                .addMapping("/api/**")
                .allowedMethods("*")
                //设置允许的域名，*为全部
                .allowedOrigins("*")
                .allowCredentials (true)
                .allowedHeaders("*")
                //准备响应前的缓存持续的最大时间（以秒为单位）
                .maxAge(3600);

    }
}