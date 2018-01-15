package com.dy.board.controllers;

import com.dy.board.domains.UserVO;
import com.dy.board.dto.LoginDTO;
import com.dy.board.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserService service;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginGET(@ModelAttribute("dto") LoginDTO dto) {

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        Object obj = session.getAttribute("login");

        if(obj != null) {
            UserVO userVO = (UserVO) obj;

            session.removeAttribute("login");
            session.invalidate();

            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if(loginCookie != null) {
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
                service.keepLogin(userVO.getUid(), session.getId(), new Date());
            }
        }
    }

    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public String loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {
        UserVO vo = service.login(dto);

        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< controller ");
        logger.info("vo : " + vo);

        if(vo == null) {
            logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< if ");
            return "/user/login";
        }
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< controller mid");
        model.addAttribute("userVO", vo);
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< controller end");
        if(dto.isUseCookie()) {
            int amount = 60 * 60 * 24 * 7;

            Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
            service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
        }
        return "home";
    }
}
