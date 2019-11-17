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
}
