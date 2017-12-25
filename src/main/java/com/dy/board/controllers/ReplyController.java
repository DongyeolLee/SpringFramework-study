package com.dy.board.controllers;

import com.dy.board.domains.ReplyVO;
import com.dy.board.services.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Inject
    private ReplyService service;
    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody ReplyVO vo) {
        logger.info("********************");
        ResponseEntity<String> entity = null;
        logger.info("********************");
        try {
            logger.info("start");
            logger.info(vo.toString());
            service.addReply(vo);
            logger.info("service finished");
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
            logger.info("entity return");
        } catch (Exception e) {
            logger.info("error");
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
