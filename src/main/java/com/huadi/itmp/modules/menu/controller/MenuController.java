package com.huadi.itmp.modules.menu.controller;

import com.huadi.itmp.core.annotation.Anonymous;
import com.huadi.itmp.modules.menu.service.SysMenuService;
import com.huadi.itmp.util.UUIDUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 胡学良
 * @date 2021-08-27 15:48
 **/
@RestController
@RequestMapping("/api")
@Api("菜单API")
public class MenuController {
    @Resource
    private SysMenuService sysMenuService;

    @Anonymous
    @GetMapping("/menu/{role}")
    public Map<String, Object> menu(@PathVariable("role") Integer role) {
        return sysMenuService.menu(role);
    }
}
