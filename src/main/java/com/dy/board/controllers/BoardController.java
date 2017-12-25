package com.dy.board.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class BoardController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
}