package com.huadi.itmp.modules.menu.mapper;

import com.huadi.itmp.modules.menu.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 胡学良
 * @date 2021-08-27 15:50
 **/
public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {

    //这里我只查询页面转态为启用,可自行定义和写
    @Query(value = "select * from  system_menu where STATUS = 1 AND id IN (SELECT menu_id FROM role_menu WHERE role_id = ?1)",nativeQuery = true)
    List<SysMenu> getSystemMenuByStatusAndSort(Integer roleId);
}
