package com.super404.video.service.impl;

import com.super404.video.config.WeChatConfig;
import com.super404.video.domain.User;
import com.super404.video.service.UserService;
import com.super404.video.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;

    @Override
    public User saveWeChatUser(String code) {

        //获取accessToken
        String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(),
                weChatConfig.getOpenAppid(), weChatConfig.getOpenAppsecret(), code);
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
        if (baseMap == null || baseMap.isEmpty()){
            return null;
        }
        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");

        //获取用户基本信息

        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(),
                accessToken, openId);
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
        if (baseUserMap == null || baseUserMap.isEmpty()){
            return null;
        }
        String nickname = (String) baseUserMap.get("nickname");
        Integer sex = (Integer) baseUserMap.get("sex");
        String province = (String) baseUserMap.get("province");
        String city = (String) baseUserMap.get("city");
        String country = (String) baseUserMap.get("country");
        String headimgurl = (String) baseUserMap.get("headimgurl");


        return null;
    }
}
