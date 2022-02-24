package com.huadi.itmp.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 胡学良
 * @date 2021-08-26 15:34
 **/
@Data
@ConfigurationProperties(prefix = "itmp")
public class ITMPProperties {
    /**
     * debug模式开关
     */
    private Boolean debug;

    private Boolean globalPreventDuplicate = false;
}
