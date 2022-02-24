package com.huadi.itmp.config;

import com.huadi.itmp.common.helper.IRedisHelper;
import com.huadi.itmp.core.interceptor.AccessInterceptor;
import com.huadi.itmp.core.interceptor.IpAccessInfoCollectInterceptor;
import com.huadi.itmp.core.interceptor.ThreadLocalMapInterceptor;
import com.huadi.itmp.core.interceptor.TokenInterceptor;
import com.huadi.itmp.core.redis.TokenManager;
import com.huadi.itmp.core.resolver.CollectAccessInfoResolver;
import com.huadi.itmp.core.resolver.CurrentSubjectResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author meteor
 */

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private IRedisHelper redisHelper;

    @Autowired
    private TokenManager tokenManager;

    @Bean
    TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor(tokenManager);
    }

    @Bean
    AccessInterceptor accessInterceptor() {
        return new AccessInterceptor();
    }

    @Bean
    IpAccessInfoCollectInterceptor ipFlowControlInterceptor() {
        return new IpAccessInfoCollectInterceptor(redisHelper);
    }

    @Bean
    ThreadLocalMapInterceptor threadLocalMapInterceptor() {
        return new ThreadLocalMapInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(threadLocalMapInterceptor())
                .addPathPatterns("/**");

        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**");


        registry.addInterceptor(accessInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/static/**");

        registry.addInterceptor(ipFlowControlInterceptor())
                .addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("static/**").addResourceLocations("classpath:/static/layuimini/");
        registry.addResourceHandler("static/page/**").addResourceLocations("classpath:/static/layuimini/page/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentSubjectResolver());
        resolvers.add(new CollectAccessInfoResolver());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
