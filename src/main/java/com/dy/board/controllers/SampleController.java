package com.dy.board.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @RequestMapping("doA")
    public String doA() {
        logger.info("***************************************************************");
        logger.info("doA called");
        logger.info("***************************************************************");

        return "home";
    }

    @RequestMapping("doB")
    public String doB(Locale locale, Model model) {
        logger.info("***************************************************************");
        logger.info("doB called");
        logger.info("***************************************************************");

        model.addAttribute("result", "DoB Result");

        return "home";
    }
}
