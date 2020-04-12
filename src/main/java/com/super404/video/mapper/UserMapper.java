package com.super404.video.mapper;

import com.super404.video.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * user数据访问层
 */
public interface UserMapper {

    /**
     * 根据主键id查找
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") int userId);

    /**
     * 根据openid找用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User findByOpenid(@Param("id") String openid);

    /**
     *保存用户信息
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` (`openid`, `name`, `head_img`, `phone`, " +
            "`sign`, `sex`, `city`, `create_time`) " +
            "VALUES " +
            "(#{openid}, #{name}, #{headImg}, #{phone}, #{sign}, #{sex}, #{city}, #{createTime});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(User user);




}
