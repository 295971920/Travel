package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-24 17:54
 * @Description : 类别查询实现类
 **/
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 查询所有分类
     *
     * @return
     */
    @Override
    public List<Category> findALl() {
        String sql = "select * from tab_category";
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
