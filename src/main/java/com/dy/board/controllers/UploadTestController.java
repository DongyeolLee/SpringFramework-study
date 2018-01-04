package com.dy.board.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

@Controller
public class UploadTestController {

    @Resource(name = "uploadPath")
    private String uploadPath;
    private static final Logger logger = LoggerFactory.getLogger(UploadTestController.class);

    @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
    public void uploadForm() {
        logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&& upload get");
    }

    @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
    public String uploadForm(MultipartFile file, Model model) throws Exception {
        logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&& upload file");
        logger.info("originName:" + file.getOriginalFilename());
        logger.info("size:" + file.getSize());
        logger.info("contentType:" + file.getContentType());

        String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

        model.addAttribute("savedName", savedName);
        return "uploadResult";
    }

    private String uploadFile(String originName, byte[] fileData) throws Exception {
        UUID uid = UUID.randomUUID();
        String savedName = uid.toString() + "_" + originName;
        File target = new File(uploadPath, savedName);

        FileCopyUtils.copy(fileData, target);
        return savedName;
    }

    @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
    public void uploadAjax() {

    }

    @RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
        logger.info("&&&&&&&&&&&&&&&&&&&&&&&&&& upload file Ajax");
        logger.info("originName:" + file.getOriginalFilename());
        logger.info("size:" + file.getSize());
        logger.info("contentType:" + file.getContentType());

        return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
    }
}
