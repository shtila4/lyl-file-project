package com.lyl.controller;


import com.lyl.mapper.FileMapper;

import com.lyl.util.Constant;
import com.lyl.util.SendMailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    //从配置文件中读属性
    @Value("${mail.fromMail.addr}")
    private String from;

    @RequestMapping("/sendMailForOther")
    @ResponseBody
    public void sendMailForOther(String to, String subject, String fileName, String type, String content) {
        if (fileName == null || type == null) {
            SendMailUtil.sendSimpleMail(mailSender, from, to, subject, content);
        } else {
            String fileArray = (Constant.ROOT_DIR + fileName + type).replace(" ", "");
            System.out.println(fileArray);
            SendMailUtil.sendAttachmentsMail(mailSender, from, to, subject, content, fileArray);
        }

    }


}
