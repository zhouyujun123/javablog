package com.example.demo.controller;

import cn.hutool.core.map.FixedLinkedHashMap;
import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.service.LoginServer;
import com.example.demo.service.MailService;
import com.example.demo.utils.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:21
 */

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginServer loginServer;

    @Autowired
    MailService mailService;

    /**
     * 当超出2000时，自动移除最老的key
     */
    private static Map<String, String> currentMap = new FixedLinkedHashMap<>(2000);

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public ApiResult login(String username, String password) {
        return loginServer.hasPeople(username, password);
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param mailbox  邮箱
     * @param captcha  验证码
     * @return
     */
    @RequestMapping("/registered")
    public ApiResult registered( String username, String password, String mailbox, String captcha) {
        if (mailbox != null && captcha.equals(currentMap.get(mailbox))) {
            return loginServer.registered(username, password, mailbox);
        } else {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.ERROR));
        }
    }


    /**
     * 获取验证码
     *
     * @param mailbox 邮箱
     */
    @RequestMapping("/getCaptcha")
    public ApiResult getCaptcha(String mailbox) {
        Object[] objects = VerifyUtil.createImage();
        // 获取验证码
        String yzm = (String) objects[0];
        log.info("验证码为 =======> " + yzm);
        currentMap.put(mailbox, yzm);
        mailService.sendAttachmentsMail(mailbox, "这是您注册的验证码===>请查收", "验证码为:"+yzm);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
    }




}
