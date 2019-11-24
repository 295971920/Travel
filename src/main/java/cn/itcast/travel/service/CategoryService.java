package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-24 17:45
 * @Description :分类Service接口
 **/
public interface CategoryService {
    /**
     * 查询所有分类
     * @return
     */
    public List<Category> findAll();
}
