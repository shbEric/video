package com.super404.video.service.impl;

import com.super404.video.config.WeChatConfig;
import com.super404.video.domain.User;
import com.super404.video.mapper.UserMapper;
import com.super404.video.service.UserService;
import com.super404.video.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserMapper userMapper;

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

        User dbUser = userMapper.findByOpenid(openId);
        //如果存在openid,更新用户，或直接返回
        if (dbUser != null) {
            return dbUser;
        }

        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(),
                accessToken, openId);
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
        if (baseUserMap == null || baseUserMap.isEmpty()){
            return null;
        }
        String nickname = (String) baseUserMap.get("nickname");
        Double sexTemp = (Double) baseUserMap.get("sex");
        Integer sex = sexTemp.intValue();
        String province = (String) baseUserMap.get("province");
        String city = (String) baseUserMap.get("city");
        String country = (String) baseUserMap.get("country");
        String headimgurl = (String) baseUserMap.get("headimgurl");
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress = sb.toString();
        //解决乱码
        try {
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());

        //如果不存在openid,则保存用户信息
        userMapper.save(user);

        return user;
    }
}
