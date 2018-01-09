package com.dy.board.controllers;

import com.dy.board.util.MimeMediaUtil;
import com.dy.board.util.UploadFileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

        return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping("/displayFile")
    public ResponseEntity<byte []> displayFiles(String fileName) throws Exception {

        InputStream in = null;
        ResponseEntity<byte []> entity = null;

        logger.info("FILE NAME : " + fileName);

        try {
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
            MediaType mType = MimeMediaUtil.getMediaType(formatName);
            HttpHeaders headers = new HttpHeaders();
            in = new FileInputStream(uploadPath + fileName);

            if(mType != null) {
                headers.setContentType(mType);
            } else {
                fileName = fileName.substring(fileName.indexOf("_") + 1);
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content_Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
                logger.info(headers.toString());
            }

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (Exception e) {

            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {

            in.close();
        }

        return entity;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {

        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        MediaType mType = MimeMediaUtil.getMediaType(formatName);

        logger.info("delete file : " + fileName);

        if(mType != null) {

            String front = fileName.substring(0, 12);
            String end = fileName.substring(14);

            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }

        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
}
