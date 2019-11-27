package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        //从Redis中查询
        //获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //可使用sortedset排序查询
        // Set<String> categorys = jedis.zrange("category", 0, -1);
        //查询sortedset中的分数(cid)和值(cname)
        final Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        //判断查询的集合是否为空
        if (categorys == null || categorys.size() == 0) {
            System.out.println("从数据库中查询");
            //如果为空，需要从数据库中查询，再将数据存入redis
            //从数据库中查询
            cs = categoryDao.findALl();
            //将集合数据存储到redis中的category的key
            for (int i = 0; i <cs.size() ; i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            System.out.println("从redis中查询.....");
            //如果不为空，将set的数据存入List中
            cs = new ArrayList<Category>();
            //sortedset排序查询
            /*for (String name : categorys ) {
             Category category = new Category();
                 category.setCname(name);
                 cs.add(category);
            }*/
            //sortedset中的分数(cid)和值(cname)
            for (Tuple tuple: categorys ) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}



















