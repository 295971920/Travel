package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author : XIAOSHAN
 * @Contact_QQ : 295971920
 * @CreateDate : 2019-11-24 09:06
 * @Description : 转发
 **/
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("BaseServlet的方法被执行了...");
        //获取方法分发
        String uri = req.getRequestURI();
        System.out.println("uri:"+uri);
        //获取方法名称
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println("方法名称："+methodName);
        //获取对象的Method
        //谁调用我？我代表睡
        System.out.println("当前this:"+this);
        try {
            //通过getDeclaredMethod获取方法可以忽略权限异常
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            // method.setAccessible(true);
            //执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接将传入的对象序列化为json，并且写回客户端
     * @param obj
     * @param response
     * @throws IOException
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入的对象序列化为json ,返回
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }


    /**
     * 校验验证码是否正确
     * @param request
     * @param response
     * @throws IOException
     */
    public void checkCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //验证用户校验
        String check = (String) request.getParameter("check");
        System.out.println("check中的验证码：" + check);
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        System.out.println("获取：" + session.getId());
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        System.out.println("Session中获取的验证码:" + checkcode_server);
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
    }
}









