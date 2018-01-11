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

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserService service;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginGET(@ModelAttribute("dto") LoginDTO dto) {

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

        return "home";
    }
}
