package com.huadi.itmp.modules.menu.service;

import java.util.Map;

public interface SysMenuService {

    /**
     * 根据角色获取菜单信息
     * @param role 角色ID
     * @return 菜单信息
     */
    Map<String, Object> menu(Integer role);

}
