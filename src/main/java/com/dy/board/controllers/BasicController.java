package com.dy.board.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BasicController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @RequestMapping(value = "/")
    public String test(){
        logger.info("***************************************************************");
        logger.info("test called");
        logger.info("***************************************************************");
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void ajaxTest() {
        logger.info("*********************************************");
        logger.info("home controller");
        logger.info("*********************************************");
    }

    @RequestMapping(value = "/handlebarsTest", method = RequestMethod.GET)
    public void handlebars() {
        logger.info("*********************************************");
        logger.info("home controller -> handlebars");
        logger.info("*********************************************");
    }
}
