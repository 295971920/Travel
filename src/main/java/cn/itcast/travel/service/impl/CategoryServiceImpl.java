package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;

import java.util.List;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-24 17:48
 * @Description : 分类实现类
 **/
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();
    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryDao.findALl();
    }
}
