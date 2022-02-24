package com.huadi.itmp.modules.menu.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author 胡学良
 * @date 2021-08-27 15:50
 **/
@Getter
@Setter
@Embeddable
public class MenuKey implements Serializable {
    private Long id;
    private String title;
    private String href;
}

