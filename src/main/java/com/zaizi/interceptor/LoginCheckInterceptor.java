package com.zaizi.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zaizi.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override  // 目标资源方法运行前运行，ture则放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("perHandle...");

        //1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}",url);

        //2.判断请求url中是否包含login，是则放行
        if(url.contains("login")) {
            log.info("登录操作，放行...");
            return true;
        }

        String jwt = "";
        if(request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        } else {
            jwt = request.getHeader("Authorization");
            jwt = jwt.substring(7);
        }

        //3.获取请求头中的令牌（token）
//        jwt = request.getHeader("Authorization");


        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的信息");
            // 手动转换对象--json ---------->阿里巴巴的fastJSON
            JSONObject.toJSONString("NOT_LOGIN");
            response.getWriter().write("NOT_LOGIN");
            return false;
        }
        //5.解析token，解析失败返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            // 手动转换对象--json ---------->阿里巴巴的fastJSON
            String notLogin = JSONObject.toJSONString("NOT_LOGIN");
            response.getWriter().write(notLogin);
            return false;
        }
        //6.放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override  // 目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override  // 试图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
