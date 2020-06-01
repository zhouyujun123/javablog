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
import com.example.demo.vo.UserVO;
import com.ramostear.captcha.HappyCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     *
     * @param code 验证码 大小写敏感
     */
    @PostMapping("/login")
    public ApiResult login(String code, String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //Verification Captcha
        boolean flag = HappyCaptcha.verification(request, code, false);
        if (flag) {
            HappyCaptcha.remove(request);
            return userService.login(username, password, response);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.VERIFY_CODE_FAILED);
        }
    }

    /**
     * 注册
     *
     * @param captcha 验证码
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
     * <p>
     * 输入邮箱则验证码发向邮箱
     * 不输邮箱 则响应到界面
     *
     * @param mailbox 邮箱
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha(@RequestParam(value = "mailbox", required = false) String mailbox, HttpServletRequest request, HttpServletResponse response) {
        if (mailbox == null) {
            HappyCaptcha.require(request, response).build().finish();
        } else {
            Object[] objects = VerifyUtil.createImage();
            // 获取验证码
            String yzm = (String) objects[0];
            log.info("验证码为 =======> " + yzm);
            currentMap.put(mailbox, yzm);
            mailService.sendAttachmentsMail(mailbox, "这是您注册的验证码===>请查收", "验证码为:" + yzm);
        }
    }

    /**
     * 修改用户信息
     *
     * @param newPsw 新密码  老密码传在TUser对象中的userPsw字段
     * @return
     */
    @PostMapping("/updateUserInfo")
    public ApiResult updateUserInfo(TUser user, String newPsw, HttpServletRequest request) {
        if (StrUtil.isNotBlank(user.getUserPsw())) {
            String userId = (String) request.getAttribute("userId");
            try {
                String password = MD5Util.md5LowerCase(user.getUserPsw());
                boolean hasPeople = userService.hasPeopleByUserIdAndPsw(userId, password);
                if (hasPeople) {
                    newPsw = MD5Util.md5LowerCase(newPsw);
                    user.setUserPsw(newPsw);
                } else {
                    return ApiResult.resultWith(ResultCodeEnum.ORIGINAL_PSW_FAIL);
                }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (userService.update(user) > 0) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.resultWith(ResultCodeEnum.USER_UPDATE_FAIL);
        }
    }

    @GetMapping("/getUserInfo/{userId}")
    public ApiResult getUserInfo(@PathVariable("userId") Long userId) {
        TUser user = userService.queryById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, userVO);
    }


}
