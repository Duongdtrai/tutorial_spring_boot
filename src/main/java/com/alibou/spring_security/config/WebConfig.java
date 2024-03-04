package com.alibou.spring_security.config;

import com.alibou.spring_security.formatters.DateFormatter;
import com.alibou.spring_security.formatters.IntegerFormatter;
import com.alibou.spring_security.interceptors.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) { // done
        System.out.println("CORS initial");
        registry.addMapping("/**");
//        .allowedOrigins("https://5341-123-16-53-214.ngrok-free.app")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("addFormatters initial");
        registry.addFormatter(new DateFormatter());
        registry.addFormatter(new IntegerFormatter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addInterceptors initial");
        registry.addInterceptor(new MyInterceptor());
    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        System.out.println("addViewControllers initial");
//        registry.addViewController("/").setViewName("home");
//    }
}
