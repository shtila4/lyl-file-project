package com.lyl.mapper;



import com.lyl.pojo.*;
import com.lyl.vo.FileParamVO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface FileMapper {

    /**
     * 对collection表的操作
     */
    //插入一条收藏记录
    @Insert("insert into collection values(default,#{uid},#{fname},#{type},#{size},#{location},#{cdate})")
    public void insAFileToCollection(Collection collection);

    // 删除一条收藏记录
    @Delete("delete from collection where cid=#{cid}")
    public void delAFileFromCollection(long cid);

    // 查找该用户的全部收藏记录
    @Select("select * from collection where uid=#{uid}")
    public List<Collection> selCollectionFile(long uid);

    // 查找该用户的一条收藏记录
    @Select("select * from collection where cid=#{cid}")
    public Collection selAFileFromCollection(long cid);

    /**
     * 对delrecord表的操作
     */
    // 插入一条删除记录
    @Insert("insert into delrecord values(default,#{uid},#{fname},#{type},#{size},#{location},#{ddate})")
    public void insAFileToRecycleBin(DelRecord delRecord);

    // 查找该用户的全部删除记录
    @Select("select * from delrecord where uid=#{uid}")
    public List<DelRecord> selRecycleBinFile(long uid);

    // 删除一条删除记录
    @Delete("delete from delrecord where did=#{did}")
    void delAFileFromRecycleBin(long did);


    // 查找该用户的一条删除记录
    @Select("select * from delrecord where did=#{did}")
    public DelRecord selAFileFromRecycleBin(long did);

    /**
     * 对share表的操作
     */
    //显示全部收藏记录,审核通过给用户展示的
    @Select("select * from share where result=1")
    public List<Share> selFileFromShare();

    //查询一条共享记录
    @Select("select * from share where sid=#{sid}")
    public Share selAFileFromShare(long sid);

    //查找文件
    @Select("select * from share where fname like #{param1} and result=1")
    public List<Share> selQueryFileFromShare(String fname);

    //管理员审核，显示未审核共享记录
    @Select("select * from share where state=0")
    public List<Share> selShareByState();

    //管理员审核
    @Update("update share set state=1,result=#{result} where sid=#{sid}")
    public void updShareToStateAndResult(int result ,long sid);

    //管理删除
    @Delete("delete from share where sid=#{sid}")
    public void delAShareBySid(long sid);


    @Insert("insert into share (uid,fname,type,size,location,sdate,state,result) values(#{uid},#{fname},#{type},#{size},#{location},current_date(),0,0)")
    public void insShare(UpRecord upRecord);
    /**
     * 对uprecord表的操作
     */

    //文件上传：插入到上传记录
    @Insert("insert into uprecord (uid,fname,type,size,location,udate) values(#{uid},#{fname},#{type},#{size},#{location},#{udate})")
    public void insUploadFile(UpRecord upRecord);

    // 查找该用户的全部上传记录
    @Select("select uid,fname,type,size,udate,pid from uprecord where uid=#{uid}")
    public List<UpRecord> selUploadRecord(long uid);

    //根据主键查询上传记录
    @Select("select uid,fname,type,size,location from uprecord where pid=#{param1}")
    UpRecord getUpRecord(Long pid);

    //删除一条记录
    @Delete("delete from uprecord where pid=#{pid}")
    public void delAUpRecord(long pid);

    /**
     * 对downrecord表的操作
     */
    //显示用户个人下载记录
    @Select("select * from downrecord where uid=#{uid}")
    public List<DownRecord> selDownRecord(long uid);

    //插入一条下载记录
    @Insert("insert into downrecord values(default,#{uid},#{fname},#{type},#{size},default,#{wdate})")
    public void insADownRecord(DownRecord downRecord);

    //删除一条下载记录
    @Delete("delete from downrecord where wid=#{wid}")
    public void delADownRecord(long wid);

    /*
     *新增统计方法
     *
     * */
    //统计全网下载次数
    @Select("select count(*) from downrecord")
    public int selDownRecordCount();

    //统计全网上传次数
    @Select("select count(*) from uprecord")
    public int selUpRecordCount();

    //统计今日使用容量
    @Select("select SUM(size) from collection where uid=#{uid} and cdate = #{cdate}")
    public Long selCollectionSize(long uid,String cdate);

    //查询用户收藏的每种文件的数量
    @Select("select type name,count(*) value from collection  where uid =#{uid} GROUP BY type ")
    public List<FileParamVO> selCollectionByUid(long uid);

    /*对history表的操作*/
    //查询全部历史预览记录
    @Select("select * from history where uid=#{uid}")
    public List<History> selAllHistory(long uid);
     //删除历史浏览记录
    @Delete("delete from history where hid=#{hid}")
    public void delAHistory(long hid);

    //插入浏览记录
    @Insert("insert into history values(default,#{uid},#{fname},#{type},#{size},#{location},#{hdate})")
    public void insAHistoryForCollection(History history);




}
