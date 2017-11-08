package com.dy.board.controllers;

import com.dy.board.domains.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController3 {
    private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);

    @RequestMapping("doD")
    public String doD(Model model) {
        logger.info("***************************************************************");
        logger.info("doD called");
        logger.info("***************************************************************");

        ProductVO product = new ProductVO("sample product", 10000);
        model.addAttribute(product);

        return "productDetail";
    }
}
