package com.huadi.itmp.config;

import com.huadi.itmp.config.properties.ITMPProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 胡学良
 * @date 2021-08-26 15:35
 **/
@Configuration
@PropertySource("classpath:application.yml")
@EnableConfigurationProperties({ITMPProperties.class})
public class ITMPConfig {
}
