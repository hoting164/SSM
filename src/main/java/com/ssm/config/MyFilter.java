package com.ssm.config;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class MyFilter implements Filter {
//    private static final List<String> list = new ArrayList<String>();
//    static {
//        list.add("/user/login");
//        list.add("/toLogin");k
//        list.add("/assert");
//        list.add("/dist");
//        list.add("/plugins");
//        list.add("/toRegister");
//        list.add("/register");
//    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) res;
//        HttpServletResponse response = (HttpServletResponse) resp;
//        String uri = request.getRequestURI();
//        for (String str: list) {
//            if(uri.contains(str)){
//                filterChain.doFilter(res,resp);
//                return;
//            }
//        }
//        if(request.getSession().getAttribute("username")==null) {
//            response.sendRedirect("/toLogin");
//        }else {
//             filterChain.doFilter(request, response);
//        }
        System.out.println("CharacterFilter，doFilter方法");
        //字符集设置
        //处理服务端到客户端乱码
        response.setContentType("text/html;charset=utf-8");
        //处理客户端到服务端乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");

        //禁止浏览器缓存页面-设置响应头
        HttpServletResponse resp = (HttpServletResponse) response;
        //Pragma用来包含实现特定的指令，将它设置为“no-cache”；
        resp.setHeader("Pragma","no-cache");
        //Cache-Control属性指定请求和响应的缓存机制，将它设置为no-cache
        resp.setHeader("Cache-Control","no-cache");
        //Expires设置过期的时间期限,-1缓存已经过期
        resp.setDateHeader("Expires", -1);

        //正常放行
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig){
        System.out.println("start");
    }

    public void destroy() {
        System.out.println("end");
    }
}
