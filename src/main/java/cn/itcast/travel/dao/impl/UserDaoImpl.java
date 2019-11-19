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
            String sql = "select * from tab_user where username = '" + username + "'";
            //select * from tab_user where username = 'usernae';
            //执行SQL
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class));
        } catch (DataAccessException e) {
//            e.printStackTrace();
            //如果用户存在，将用户名在控制台打印出来
            System.out.println("用户已存在：" + username.toString());
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
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code)" +
                "values(?,?,?,?,?,?,?,?,?) ";
        //执行SQL
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /**
     * 根据激活码查询用户对象
     *
     * @param code
     * @return
     */
    @Override
    public User findCode(String code) {
        User user = null;
        try {
            //定义SQL语句
            String sql = "select * from tab_user where code = ? ";
            //执行SQL语句
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 修改指定用户激活状态
     *
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        //定义sql
        String sql = "update tab_user set status = 'Y' where uid = ? ";
        template.update(sql, user.getUid());
        //执行SQL
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //定义SQL语句
            String sql = "select * from tab_user where username = ? and password = ? ";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return user;
    }
}











