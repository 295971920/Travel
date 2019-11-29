package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-27 23:39
 * @Description :
 **/
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据cid查询总记录数
     * @param cid
     * @return
     */
    @Override
    public int findTotalCount(int cid) {
        //定义SQL
        String sql = "select count(*) from tab_route where cid = ? ";
        //执行sql 语句
        return template.queryForObject(sql,Integer.class,cid);
    }

    /**
     * 根据cid,start ,pageSize 查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        //定义sql 语句
        String sql = "select * from tab_route where cid = ? limit ?,?";
        //执行sql语句
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid,start,pageSize);
    }
}
