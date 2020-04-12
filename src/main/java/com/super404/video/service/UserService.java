package com.super404.video.service;

import com.super404.video.domain.User;

/**
 * 用户业务接口类
 */
public interface UserService {

    User saveWeChatUser(String code);
}
