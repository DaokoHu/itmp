package com.huadi.itmp.modules.menu.dto;

import com.huadi.itmp.modules.menu.entity.SystemMenu;
import lombok.Data;

import java.util.List;

/**
 * @author 胡学良
 * @date 2021-08-27 15:31
 **/
@Data
public class MenuListDTO {
    private String title;

    private String icon;

    private String href;

    private String target;

    List<MenuListDTO> child;

    public MenuListDTO(SystemMenu systemMenu, List<MenuListDTO> child) {
        this.title = systemMenu.getTitle();
        this.icon = systemMenu.getIcon();
        this.href = systemMenu.getHref();
        this.target = systemMenu.getTarget();

    }
}
