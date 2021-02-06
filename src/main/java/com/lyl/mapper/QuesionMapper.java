package com.lyl.mapper;

import com.lyl.pojo.Quesion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuesionMapper {

    /*收集用户问题反馈*/
    @Insert("insert into quesion values(default,#{uid},#{aim},#{evaluation},#{msg})")
    public void insQuesion(Quesion quesion);

    /*删除问题*/
    @Delete("delete from quesion where qid=#{qid}")
    public void delQuesion(long qid);

    /*查询用户问题*/
    @Select("select * from quesion")
    public List<Quesion> selAllQuesion();
}
