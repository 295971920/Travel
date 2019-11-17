package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.jws.WebService;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-14 20:37
 * @Description :
 **/
//@WebService
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //定义SQL语句
            String sql = "select * from tab_user where username = '"+username+"'";
            //select * from tab_user where username = 'usernae';
            //执行SQL
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class));
        } catch (DataAccessException e) {
//            e.printStackTrace();
            System.out.println("用户已存在："+username.toString());
            return null;
        }
        return user;
    }

    /**
     * 用户保存
     *
     * @param user
     */
    @Override
    public void save(User user) {
        //定义SQL
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email)" +
                "values(?,?,?,?,?,?,?) ";
        //执行SQL
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }
}











