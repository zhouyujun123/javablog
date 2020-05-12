package com.example.demo.service;

import java.awt.image.BufferedImage;

/**
 * @Author mintaoyu
 * Date on 2020-04-20  09:16
 */
public interface MailService {

    /**
     * 发送附件
     * @param to 邮件接受者
     * @param subject 主题
     * @param content 内容
     */
    void sendAttachmentsMail(String to, String subject,  String content);
}
