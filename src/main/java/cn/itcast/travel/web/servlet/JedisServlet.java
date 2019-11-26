package cn.itcast.travel.web.servlet;

import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-27 01:06
 * @Description :
 **/
public class JedisServlet {
    public static void main(String[] args) {
        /*//1. 获取连接
        Jedis jedis = new Jedis("localhost",6379);
        //输入密码
        jedis.auth("password");
        //2. 操作
        jedis.set("username","zhangsan");
        String username = jedis.get("username");
        System.out.println(username);
        //3. 关闭连接
        jedis.close();*/
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("Hello","LiSi");
        String hello = jedis.get("Hello");
        System.out.println(hello);
        //关闭jedis连接
        jedis.close();
    }
}
