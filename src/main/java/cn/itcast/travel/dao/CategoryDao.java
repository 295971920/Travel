package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-24 17:50
 * @Description : 分类查询接口
 **/
public interface CategoryDao {

    /**
     * 查询所有分类
     * @return
     */
    List<Category> findALl();
}
