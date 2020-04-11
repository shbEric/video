package com.super404.video;

import com.super404.video.domain.User;
import com.super404.video.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

public class CommonTest {

    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setId(999);
        user.setHeadImg("www.super404.com");
        user.setName("xd");

        String token = JwtUtils.genJsonWebToken(user);

        System.out.println(token);
    }

    @Test
    public void testCheckJwt(){
        String token = "1eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlciIsImlkIjo5OTksIm5hbWUiOiJ4ZCIsImltZyI6Ind3dy5zdXBlcjQwNC5jb20iLCJpYXQiOjE1ODY0MzQ0MTMsImV4cCI6MTU4NzAzOTIxM30.tGlQC7iSsfPcOM2M_q-Zg0pxqQUjarPgFRILYbaYiFE";
        Claims claims = JwtUtils.checkJWT(token);
        if (claims != null){
            String name = (String) claims.get("name");
            String img = (String) claims.get("img");
            int id = (Integer) claims.get("id");
            System.out.println(name);
            System.out.println(img);
            System.out.println(id);
        } else {
            System.out.println("非法token，解密失败");
        }
    }
}
