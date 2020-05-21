package com.example.demo.controller;

import cn.hutool.core.map.FixedLinkedHashMap;
import cn.hutool.core.util.StrUtil;
import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TUser;
import com.example.demo.service.MailService;
import com.example.demo.service.TUserService;
import com.example.demo.utils.MD5Util;
import com.example.demo.utils.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:21
 */

@Slf4j
@RestController
public class UserController {

    @Autowired
    private TUserService userService;

    @Autowired
    MailService mailService;

    /**
     * 当超出2000时，自动移除最老的key
     */
    private static Map<String, String> currentMap = new FixedLinkedHashMap<>(2000);

    /**
     * 登陆
     */
    @PostMapping("/login")
    public ApiResult login(String username, String password, HttpServletResponse response) {
        return userService.login(username, password, response);
    }

    /**
     * 注册
     * @param captcha  验证码
     * @return
     */
    @PostMapping("/registered")
    public ApiResult registered(TUser user, String captcha) {
        if (user.getUserEmail() != null && captcha.equals(currentMap.get(user.getUserEmail()))) {
            return userService.registered(user);
        } else {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.ERROR));
        }
    }


    /**
     * 获取验证码
     *
     * @param mailbox 邮箱
     */
    @GetMapping("/getCaptcha")
    public ApiResult getCaptcha(String mailbox) {
        Object[] objects = VerifyUtil.createImage();
        // 获取验证码
        String yzm = (String) objects[0];
        log.info("验证码为 =======> " + yzm);
        currentMap.put(mailbox, yzm);
        mailService.sendAttachmentsMail(mailbox, "这是您注册的验证码===>请查收", "验证码为:" + yzm);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
    }

    /**
     * 修改用户信息
     * @return
     */
    @PostMapping("/updateUserInfo")
    public ApiResult updateUserInfo(TUser user){
        if (StrUtil.isNotBlank(user.getUserPsw())){
            try {
                String password = MD5Util.md5LowerCase(user.getUserPsw());
                user.setUserPsw(password);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (userService.update(user)>0){
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }else {
            return ApiResult.resultWith(ResultCodeEnum.USER_UPDATE_FAIL);
        }
    }


}
