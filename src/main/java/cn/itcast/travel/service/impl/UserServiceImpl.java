package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import javax.jws.WebService;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-14 20:39
 * @Description :
 **/
//@WebService
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //        //       //判断u是否为null
        if(u != null){
            //用户名存在，注册失败
            System.out.println(u.toString());
            return false;
        }
        //保存用户信息
        //设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //设置激活码状态
        user.setStatus("N");
        //激活邮件发送，邮件正文？
        String content = "<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        //保存用户信息
        userDao.save(user);
        return true;
    }

    /**
     * 邮件激活
     *
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据激活码查询用户对象
        User user = userDao.findCode(code);
        if (user != null) {
            //调用dao 的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 用户登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}















