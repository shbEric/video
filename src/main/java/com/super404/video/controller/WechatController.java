package com.super404.video.controller;

import com.super404.video.config.WeChatConfig;
import com.super404.video.domain.JsonData;
import com.super404.video.domain.User;
import com.super404.video.service.UserService;
import com.super404.video.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;

    /**
     * 拼装微信扫一扫登录url
     * @return
     */
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page", required = true) String accessPage) throws UnsupportedEncodingException {

        String redirectUrl = weChatConfig.getOpenRedirectUrl(); //获取开发平台重定向地址

        String callbackUrl = URLEncoder.encode(redirectUrl, "GBK"); //进行编码

        String qrcodeUrl = String.format(WeChatConfig.getOpenQrcodeUrl(),
                weChatConfig.getOpenAppid(), callbackUrl, accessPage);

        return JsonData.buildSuccess(qrcodeUrl);
    }

    /**
     * 微信扫码登录回调url
     * @param code
     * @param state
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code", required = true) String code,
                                   String state, HttpServletResponse response) throws IOException {
        User user = userService.saveWeChatUser(code);
        if (user != null){
            //生成jwt
            String token = JwtUtils.genJsonWebToken(user);
            //state 当前用户的页面地址，需要拼接 http:// 这样才不会站内跳转
            response.sendRedirect(state+"?token="+token
                    +"&head_img="+user.getHeadImg()+"&name="+URLEncoder.encode(user.getName(), "UTF-8"));
        }
    }

}
