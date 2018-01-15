package com.dy.board.interceptor;

import com.dy.board.domains.UserVO;
import com.dy.board.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor extends HandlerInterceptorAdapter{
    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    @Inject
    private UserService service;

    private void saveDest(HttpServletRequest req) {
        String uri = req.getRequestURI();
        String query = req.getQueryString();

        if(query == null || query.equals("null")) {
            query = "";
        } else {
            query = "?" + query;
        }

        if(req.getMethod().equals("GET")) {
            logger.info("dest uri : " + uri);
            logger.info("dest query : " + query);
            req.getSession().setAttribute("dest", uri + query);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        logger.info("Auth interceptor start *****************************************88");
        if(session.getAttribute("login") == null) {
            logger.info("current user is not logined");

            saveDest(request);
            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if(loginCookie != null) {
                UserVO userVo = service.checkLoginBefore(loginCookie.getValue());

                logger.info("UserVO : " + userVo);
                if(userVo != null) {
                    session.setAttribute("login", userVo);
                    return  true;
                }
            }

            response.sendRedirect("/user/login");
            return false;
        }

        return true;
    }
}
