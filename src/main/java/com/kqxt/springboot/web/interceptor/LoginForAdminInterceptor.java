
package com.kqxt.springboot.web.interceptor;


import com.kqxt.springboot.model.Admin;
import com.kqxt.springboot.service.AdminService;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginForAdminInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private AdminService adminService;
    //private static final String[] IGNORE_URI = {};//定义不拦截的资源


    /**
     * 在执行action里面的处理逻辑之前执行，它返回的是boolean，这里如果我们返回true在接着执行postHandle和afterCompletion，如果我们返回false则中断执行。
     */


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        System.out.println(url);
        boolean flag = false;//默认验证失败，即拦截请求
        String message = null;//用于传递到前台的信息
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            //获取最新账户信息，并且如果账户被在其他地方修改密码，那么将被迫下线
            admin = adminService.login(admin.getA_username(), admin.getA_password());
            if (admin != null) {
                flag = true;
                session.setAttribute("admin", admin);
            } else {
                message = "系统提示：您的账户信息已经更改，请重新登录";
                //移除可能过时的账户信息
                session.removeAttribute("admin");
            }
        } else {
            message = "登录超时，请重新登录";
        }
        if (flag == false) {//如果没有登录或登录异常
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {//如果是ajax请求
                //返回错误信息
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("status", -1);
                map.put("myMessage", "请先登录！");
                JSONObject jsonObject = JSONObject.fromObject(map);
                //注意，必须加上这个，才能让前端JS认为是JSON格式来进行相应处理
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print(jsonObject.toString());
                out.flush();
                out.close();
            } else {//如果是同步请求
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
                //对信息进行编码处理，否则中文无法传递到前台
                message = java.net.URLEncoder.encode(message.toString(), "utf-8");
                response.sendRedirect(basePath + "login?myMessage=" + message);


/**
 * 说明：request.getRequestDispatcher跳转时，由于地址还是被拦截的Controller请求，因此会导致执行两次
 * 从而可能导致异常，因此使用response.sendRedirect
 */


                //request.setAttribute("myMessage",message);
                //request.getRequestDispatcher("/admin/toLogin").forward(request,response);
            }
        }
        return flag;
    }

    //在执行action里面的逻辑后返回视图之前执行。
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

    }

    //在执行action里面的逻辑后返回视图之后执行。
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);

    }
}


