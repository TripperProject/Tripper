package com.tripper.config;

import com.tripper.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

//    private static final String[] CLASSPATH_RESOURCE_LOCATIONS= {
//            "classpath:/static/", "classpath:/public/", "classpath:/",
//            "classpath:/resources/", "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/"
//    };

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        // /에 해당하는 url mapping을 /common/test로 forward한다.
//        registry.addViewController( "/" ).setViewName( "forward:/index" );
//        // 우선순위를 가장 높게 잡는다.
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//    }

}
