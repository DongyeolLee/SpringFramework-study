package com.dy.board.controllers;

import com.dy.board.domains.Criteria;
import com.dy.board.domains.PageMaker;
import com.dy.board.domains.ReplyVO;
import com.dy.board.services.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno) {

        ResponseEntity<List<ReplyVO>> entity = null;

        try {
            entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{rno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo) {

        ResponseEntity<String> entity = null;

        try {
            vo.setRno(rno);
            service.modifyReply(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {

        ResponseEntity<String> entity = null;

        try {
            service.removeReply(rno);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") Integer bno, @PathVariable("page") Integer page) {

        ResponseEntity<Map<String, Object>> entity = null;

        try {
            Criteria cri = new Criteria();
            PageMaker pageMaker = new PageMaker();

            cri.setPage(page);
            pageMaker.setCri(cri);

            Map<String, Object> map = new HashMap<String, Object>();
            List<ReplyVO> list = service.listReplyPage(bno, cri);

            map.put("list", list);

            int replyCount = service.count(bno);
            pageMaker.setTotalCount(replyCount);

            map.put("pageMaker", pageMaker);

            entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
