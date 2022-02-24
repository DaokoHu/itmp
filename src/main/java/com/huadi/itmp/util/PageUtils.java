package com.huadi.itmp.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;

/**
 * @author meteor
 */
public class PageUtils {
    public static <T>IPage<T> emptyPage() {
        IPage<T> page = new Page<>();
        page.setTotal(0L);
        page.setPages(0L);
        page.setCurrent(0);
        page.setRecords(new ArrayList<>(0));
        return page;
    }


}
