package com.huadi.itmp.modules.menu.service.impl;

import com.huadi.itmp.modules.menu.dto.MenuInfo;
import com.huadi.itmp.modules.menu.entity.MenuVo;
import com.huadi.itmp.modules.menu.entity.SysMenu;
import com.huadi.itmp.modules.menu.mapper.SysMenuRepository;
import com.huadi.itmp.modules.menu.service.SysMenuService;
import com.huadi.itmp.modules.menu.util.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 胡学良
 * @date 2021-08-27 15:49
 **/
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuRepository sysMenuRepository;
    @Override
    public Map<String, Object> menu(Integer role) {
        Map<String, Object> map = new HashMap<>(16);
//        Map<String,Object> home = new HashMap<>(16);
//        Map<String,Object> logo = new HashMap<>(16);
        List<SysMenu> menuList = sysMenuRepository.getSystemMenuByStatusAndSort(role);
        List<MenuVo> menuInfo = new ArrayList<>();
        for (SysMenu e : menuList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getKey().getId());
            menuVO.setPid(e.getPid());
            menuVO.setHref(e.getKey().getHref());
            menuVO.setTitle(e.getKey().getTitle());
            menuVO.setIcon(e.getIcon());
            menuVO.setTarget(e.getTarget());
            menuInfo.add(menuVO);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0L));
        MenuInfo homeInfo = new MenuInfo();
        homeInfo.setTitle("首页");
        homeInfo.setHref("page/welcome-1.html");
        map.put("homeInfo", homeInfo);
        MenuInfo logoInfo = new MenuInfo();
        logoInfo.setTitle("后台管理");
        logoInfo.setImage("images/logo.png");
        logoInfo.setHref("index.html");
        map.put("logoInfo", logoInfo);
        return map;
    }
}
