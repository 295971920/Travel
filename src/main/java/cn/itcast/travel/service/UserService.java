package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-14 20:39
 * @Description :
 **/
public interface UserService {
   /**
    * 注册用户
    * @param user
    * @return
    */
   boolean regist(User user);

    /**
     * 邮件激活
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
