package com.lyl.mapper;

import com.lyl.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    /**
     * 对user表的操作
     */
    //修改用户的容量
    @Update("update user set volume=#{param1} where uid=#{param2} ")
    public void updVolume(long volume, long uid);

    //查询用户
    @Select("select * from user where uid=#{param1}")
    public User selAUserById(long uid);

    //修改用户密码
    @Update("update user set password=#{password} where uid=#{uid}")
    public void updUserPassword(String password,long uid);

    //查询用户判断用户是否存在
    @Select("select * from user where uid=#{param1} and password=#{param2}")
    public User selAUaerForLogin(long uid, String password);

    //修改用户的身份
    @Update("update user set identity=#{param1} where uid=#{param2} ")
    public void updIdentity(String identity, long uid);

    //用户注册
   @Insert("insert into user  values(#{uid},#{password},#{photo},#{volume},#{identity})")
    public void insRegister(User user);

   //统计全网用户数量
    @Select("select count(*) from user")
     public int selUserCount();


}
