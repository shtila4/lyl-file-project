package com.lyl.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class SendMailUtil {


    /*发送文字*/
    public static void sendSimpleMail(JavaMailSender mailSender, String from, String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);//主人
        message.setTo(to);//客人
        message.setSubject(subject);//主题
        message.setText(content);//简单描述，内容
        mailSender.send(message);
    }

    /*发送文件*/
    public static void sendAttachmentsMail(JavaMailSender mailSender, String from, String to, String subject, String content, String fileArray) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            //验证文件数据是否为空
            if (null != fileArray) {
                FileSystemResource file = null;
                file = new FileSystemResource(fileArray);
                helper.addAttachment(fileArray, file);
            }
            mailSender.send(message);
            System.out.println("带附件的邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
        }
    }

}