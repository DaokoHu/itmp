package com.huadi.itmp.core.flow;

import lombok.Data;
import lombok.ToString;

/**
 * 访问者信息
 * @author meteor
 */
@Data
@ToString
public class AccessInfo {

    /**
     * 访问者IP
     */
    private String ipAddress;

    /**
     * 上一次访问网站的时间
     */
    private Long latestVisitTime;

    /**
     * 该IP连续访问网站的次数
     */
    private Integer visitCount;

    /**
     * 上一次访问目标字段的时间
     */
    private Long fieldLatestVisitTime;

    /**
     * 访问该字段的次数
     */
    private Integer fieldVisitCount;

}
