package com.lyl.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.lyl.mapper.FileMapper;
import com.lyl.mapper.QuesionMapper;
import com.lyl.mapper.UserMapper;
import com.lyl.pojo.*;
import com.lyl.util.Constant;

import com.lyl.pojo.User;


import com.lyl.vo.PojoViewVO;
import com.lyl.vo.FileParamVO;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.*;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class UserAndFileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private QuesionMapper quesionMapper;

    /**
     * 登录  ok
     */
    @RequestMapping("SSF-login")
    public String loginPage() {
        return "login.html";
    }

    /*登录  ok*/
    @RequestMapping("login")
    public String initialPage(Long uid, String password, HttpServletRequest req) {
        System.out.println(uid + "  " + password);
        User user = userMapper.selAUserById(uid);
        if (user != null) {
            String pass = user.getPassword();
            if (!pass.equals(password)) {
                System.out.println("不相等");
                return "login.html";
            } else {
                // 获取每个用户都唯一的session对象
                HttpSession session = req.getSession();
                // 把当前登录用户的email存储在该用户的session中
                session.setAttribute("uid", uid);
                String identity = user.getIdentity();
                if (identity.equals("admin")) {
                    return "admin.html";
                } else {
                    return "index.html";
                }
            }
        } else {
            return "register.html";

        }
    }

    /*检查登录用户的身份用于登录的友好提示 ok*/
    @RequestMapping("loginCheck")
    public void checkLogin(long uid, String password, HttpServletResponse resp) throws IOException {
        System.out.println("ajav" + uid + password);
        User user = userMapper.selAUaerForLogin(uid, password);
        // 响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        // 响应ajax请求
        if (user == null) {
            resp.getWriter().write("错误");
        } else {
            resp.getWriter().write("正确");
        }
    }

    /*修改密码*/
    @RequestMapping("updPassword")
    @ResponseBody
    public String modifyPassword(String old_password, String new_password, String again_password, HttpServletRequest req) {
        System.out.println("修改密码开始");
        System.out.println(old_password);
        Long uid = (Long) req.getSession().getAttribute("uid");
        User user = userMapper.selAUserById(uid);
        String password = user.getPassword();
        if (!old_password.equals(password)) {
            return "旧密码不对";
        } else if (!new_password.equals(again_password)) {
            return "两次密码不一致";
        } else if (new_password.equals(old_password)) {
            return "原密码与新密码一致";
        } else {
            userMapper.updUserPassword(new_password, uid);
            return "1";
        }
    }

    /*检查注册用户用于注册的友好提示 ok*/
    @RequestMapping("registerCheck")
    public void checkRegister(long uid, HttpServletResponse resp) throws IOException {
        System.out.println("ajav" + uid);
        User user = userMapper.selAUserById(uid);
        // 响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        // 响应ajax请求
        if (user != null) {
            resp.getWriter().write("错误");
        } else {
            resp.getWriter().write("正确");
        }
    }

    /*提交注册信息 ok*/
    @RequestMapping("register")
    public String register(Long uid, String password, HttpServletRequest req) throws IOException {
        // 绑定请求中的提示信息
        System.out.println("注册");
        if (userMapper.selAUserById(uid) == null) {
            User user = new User(uid, password, "D:/photos/", 104857600, "普通用户");
            userMapper.insRegister(user);
            return "redirect:/login.html";
        } else {
            return "redirect:/register.html";
        }

    }

    /*查询该用户身份用户验证是否为会员 ok*/
    @RequestMapping("checkUserIdentity")
    public void checkUserIdentity(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("检查用户身份");
        // 获取用户id
        HttpSession session = req.getSession();
        long uid = (long) session.getAttribute("uid");
        // 查询用户身份
        String identity = userMapper.selAUserById(uid).getIdentity();
        // 响应编码格式
        res.setContentType("text/html;charset=utf-8");
        // 响应ajax请求
        if (identity.equals("普通用户")) {
            res.getWriter().write("普通用户");
        } else {
            res.getWriter().write("会员");
        }

    }

    /*开通会员 ok*/
    @RequestMapping("OpenMember")
    public String OpenMember(HttpServletRequest req) {
        System.out.println("开通会员");
        // 获取用户id
        HttpSession session = req.getSession();
        long uid = (long) session.getAttribute("uid");
        String identity = "会员";
        // 扩容:指定容量为100
        long volume = userMapper.selAUserById(uid).getVolume() * 2;
        userMapper.updVolume(volume, uid);
        // 修改身份
        session.setAttribute("identity", "充钱玩家");
        userMapper.updIdentity(identity, uid);
        return "opensuccess.jsp";
    }

    /*查看我的容量  ok*/
    @RequestMapping("myCapacity")
    @ResponseBody
    public long myCapacity(HttpServletRequest req) {
        System.out.println("查看我的容量");
        // 当前用户的uid
        Long uid = (Long) req.getSession().getAttribute("uid");
        User user = userMapper.selAUserById(uid);
        String identity = user.getIdentity();
        long capacity = user.getVolume();
        return capacity;
    }

    /*查看我今日使用空间  ok*/
    @RequestMapping("todayToMyCapacity")
    @ResponseBody
    public Long todayMyCapacity(HttpServletRequest req) {
        Long uid = (Long) req.getSession().getAttribute("uid");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String cdate = df.format(new Date());
        Long Capacity = fileMapper.selCollectionSize(uid, cdate);
        long l = 0;
        if (Capacity == null) {
            return l;
        } else {
            return Capacity;
        }

    }


    /*查看帮助 ok 前端已修改*/
    @RequestMapping("showHelp")
    public String showHelper() {

        return "help.jsp";
    }

    /*显示该用户的收藏记录 ok*/
    @RequestMapping("toPersonalCollection")
    @ResponseBody
    public PojoViewVO<Collection> showCollection(HttpServletRequest req) {
        System.out.println("显示收藏记录");
        // 获取用户id，登录时用户id加入session
        HttpSession session = req.getSession();
        long uid = (long) session.getAttribute("uid");
        // 回显
        List<Collection> list = fileMapper.selCollectionFile(uid);
        PojoViewVO<Collection> pojoViewVO = new PojoViewVO<>();
        pojoViewVO.setCode(0);
        pojoViewVO.setCount(100);
        pojoViewVO.setMsg("");
        pojoViewVO.setData(list);
        return pojoViewVO;


    }

    /*显示该用户的删除记录 ok*/
    @RequestMapping("showDelrecord")
    @ResponseBody
    public PojoViewVO<DelRecord> showDelrecord(HttpServletRequest req) {
        System.out.println("显示删除记录");
        // 获取用户id
        HttpSession session = req.getSession();
        long uid = (long) session.getAttribute("uid");
        // 回显
        List<DelRecord> list = fileMapper.selRecycleBinFile(uid);
        PojoViewVO<DelRecord> pojoViewVO = new PojoViewVO<>();
        pojoViewVO.setCode(0);
        pojoViewVO.setCount(100);
        pojoViewVO.setMsg("");
        pojoViewVO.setData(list);
        return pojoViewVO;

    }

    /*用户视图显示共享记录审核通过的 ok*/
    @RequestMapping("showShare")
    @ResponseBody
    public PojoViewVO<Share> showShare(String fname, HttpServletRequest req) {
        System.out.println("显示共享记录");

        if (fname == null) {
            System.out.println("查询全部");
            // 查询全部文件
            List<Share> list = fileMapper.selFileFromShare();
            PojoViewVO<Share> pojoViewVO = new PojoViewVO<>();
            pojoViewVO.setCode(0);
            pojoViewVO.setCount(100);
            pojoViewVO.setMsg("");
            pojoViewVO.setData(list);
            return pojoViewVO;
        } else {
            System.out.println("范围查询");
            // 查询符合条件的文件
            List<Share> list = fileMapper.selQueryFileFromShare("%" + fname + "%");
            PojoViewVO<Share> pojoViewVO = new PojoViewVO<>();
            pojoViewVO.setCode(0);
            pojoViewVO.setCount(100);
            pojoViewVO.setMsg("");
            pojoViewVO.setData(list);
            return pojoViewVO;
        }

    }

    /*显示下载记录 ok*/
    @RequestMapping("myDownload")
    @ResponseBody
    public PojoViewVO<DownRecord> showDownRecord(HttpServletRequest req) {
        System.out.println("显示下载记录");
        HttpSession session = req.getSession();
        long uid = (long) session.getAttribute("uid");
        List<DownRecord> downRecords = fileMapper.selDownRecord(uid);
        PojoViewVO<DownRecord> pojoViewVO = new PojoViewVO<>();
        pojoViewVO.setCode(0);
        pojoViewVO.setCount(100);
        pojoViewVO.setMsg("");
        pojoViewVO.setData(downRecords);
        return pojoViewVO;
    }

    /*显示个人的分享（个人上传）记录 ok*/
    @RequestMapping("toMyShare")
    @ResponseBody
    public PojoViewVO<UpRecord> showUploadRecord(HttpServletRequest req) {
        System.out.println("显示我的分享");
        // 当前用户的uid
        Long uid = (Long) req.getSession().getAttribute("uid");
        List<UpRecord> records = fileMapper.selUploadRecord(uid);
        PojoViewVO<UpRecord> pojoViewVO = new PojoViewVO<>();
        pojoViewVO.setCode(0);
        pojoViewVO.setCount(100);
        pojoViewVO.setMsg("");
        pojoViewVO.setData(records);
	/*	System.out.println(records);
		req.setAttribute("records", records);*/
        return pojoViewVO;
    }

    /*收藏文件  ok*/
    @RequestMapping("addToCollection")
    public String addToCollection(long sid, HttpServletRequest req) throws IOException {
        System.out.println("收藏文件");

        // 获取用户id
        HttpSession session = req.getSession();
        long uid = (long) session.getAttribute("uid");
        // 查询该文件信息
        Share share = fileMapper.selAFileFromShare(sid);
        Collection collection = new Collection(0, uid, share.getFname(), share.getType(), share.getSize(),
                share.getLocation(), new Date());
        long volume = userMapper.selAUserById(uid).getVolume();
        if (volume < share.getSize()) {
            return "volume.jsp";
        } else {
            volume = volume - share.getSize();
            // 修改用户的容量
            userMapper.updVolume(volume, uid);
            // 加入到收藏记录
            fileMapper.insAFileToCollection(collection);
            return "redirect:/showShare";

        }

    }

    /*加入回收站 ok*/
    @RequestMapping("addToRecycleBin")
    @ResponseBody
    public void addToRecycleBin(long cid, HttpServletRequest req) {
        System.out.println("加入回收站");
        Collection collection = fileMapper.selAFileFromCollection(cid);
        // 构造删除记录
        DelRecord delRecord = new DelRecord(0, collection.getUid(), collection.getFname(), collection.getType(),
                collection.getSize(), collection.getLocation(), new Date());
        // 计算用户的容量
        long volume = userMapper.selAUserById(collection.getUid()).getVolume() + collection.getSize();
        // 修改用户的容量
        userMapper.updVolume(volume, collection.getUid());
        // 插入一条删除记录
        fileMapper.insAFileToRecycleBin(delRecord);
        // 删除一条共享记录
        fileMapper.delAFileFromCollection(cid);
    }

    /*删除回收站文件 ok*/
    @RequestMapping("deleteFileFromRecycleBin")
    @ResponseBody
    public void deleteFileFromRecycleBin(Long did, HttpServletRequest req) {
        System.out.println("删除回收站文件");
        fileMapper.delAFileFromRecycleBin(did);

    }

    /*恢复回收站文件 ok*/
    @RequestMapping("recoveryFileFromRecycleBin")
    @ResponseBody
    public void recoveryFileFromRecycleBin(Long did, HttpServletRequest req) {
        System.out.println("恢复文件");
        DelRecord delRecord = fileMapper.selAFileFromRecycleBin(did);

        // 构造对应收藏记录对象
        Collection collection = new Collection(0, delRecord.getUid(), delRecord.getFname(), delRecord.getType(),
                delRecord.getSize(), delRecord.getLocation(), delRecord.getDdate());
        // 计算该用户容量
        long volume = userMapper.selAUserById(delRecord.getUid()).getVolume();
        if (volume < delRecord.getSize()) {
            //return "volume.jsp";
        } else {
            volume = volume - delRecord.getSize();
            // 修改用户容量
            userMapper.updVolume(volume, delRecord.getUid());
            // 插入一收藏记录
            fileMapper.insAFileToCollection(collection);
            // 删除对应删除记录
            fileMapper.delAFileFromRecycleBin(did);
            // 回显
            //return "redirect:/showDelrecord";
        }

    }

    /*删除下载记录 ok*/
    @RequestMapping("deleteFileFromDownRecord")
    public String deleteARecordFromDown(long wid, HttpServletRequest req) {
        System.out.println("down:" + wid);
//        HttpSession session = req.getSession();
//        long uid = (long) session.getAttribute("uid");
        fileMapper.delADownRecord(wid);
        return "redirect:/myDownload";
    }

    /*分享文件 待改*/
    @RequestMapping("shareFile")
    String shareFile(Long pid) {
        UpRecord upRecord = fileMapper.getUpRecord(pid);
        fileMapper.insShare(upRecord);
        return "shareSpace.jsp";
    }

    /*删除我的分享文件 ok*/
    @RequestMapping("delARecordFromUpload")
    @ResponseBody
    public void deleteARecordFromUpload(long pid, HttpServletRequest req) {

        fileMapper.delAUpRecord(pid);

    }

    /*跳往上传文件页面 待改*/
    @RequestMapping("toUploadFile")
    public String toUpload() {
        return "uploadFile.jsp";
    }

    /*上传文件 待改*/
    @RequestMapping("uploadFile")
    @ResponseBody
    public void uploadFile(String tt, MultipartFile file, HttpServletRequest req) throws IOException {
        System.out.println("上传文件" + tt);
        // 当前用户的uid
        Long uid = (Long) req.getSession().getAttribute("uid");
        // 解析文件名
        String fileName = file.getOriginalFilename().split("[.]")[0];
        // 解析文件格式
        String type = file.getOriginalFilename().split("[.]")[1];
        // 书籍文件的存储位置
        String fileUrl = Constant.ROOT_DIR + new Random().nextInt() + "_" + file.getOriginalFilename();
        // 获取文件的字节数组
        byte[] bytes = file.getBytes();
        // 获取上传文件字节数大小
        long size = bytes.length;
        // 把书籍文件存储到磁盘
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileUrl);
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 封装对象
        UpRecord upRecord = new UpRecord(uid, fileName, type, size, fileUrl, new SimpleDateFormat().format(new Date()));
        // 插入上传记录
        fileMapper.insUploadFile(upRecord);
        // 插入共享记录
        fileMapper.insShare(upRecord);
        //return "uploadFile.jsp";
    }

    /*文件下载 默认路径版 ok*/
    @RequestMapping("downloadFile")
    @ResponseBody
    public ResponseEntity<byte[]> download(String fileUrl, String fname, String type, HttpServletRequest req) throws IOException {
        Long uid = (Long) req.getSession().getAttribute("uid");
        System.out.println(fileUrl + fname);
        FileInputStream fis = null;
        byte[] file = null;
        try {
            fis = new FileInputStream(new File(fileUrl));
            file = new byte[fis.available()];
            fis.read(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 将响应数据、请求头信息封装到ResponseEntity中
        HttpHeaders headers = new HttpHeaders();
        // 设置下载文件名称
        String filename = fname + "." + type;
        // 设置请求头以附件形式下载
        headers.add("Content-Disposition", "attachment;filename=" + filename);
        // 设置正确响应码200
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(file, headers, statusCode);

        FileUtils.copyFileToDirectory(new File(fileUrl), new File(Constant.PATH_DOWN));
        // 插入下载记录
        DownRecord downRecord = new DownRecord(0, uid, filename, type, file.length, new Date());
        fileMapper.insADownRecord(downRecord);
        return entity;
    }

    /*文件下载自选路径版 ok*/
    @RequestMapping("down")
    @ResponseBody
    public void downFile(long cid) throws IOException {
        System.out.println(cid);
        Collection downFile = fileMapper.selAFileFromCollection(cid);
        String path = downFile.getLocation();
        System.out.println(path);
        FileUtils.copyFileToDirectory(new File(path), new File(Constant.PATH_DOWN));

    }


    /*文件下载 附件版*/
    @RequestMapping("download")
    @ResponseBody
    public void download(String openStyle, String fileUrl, HttpServletResponse response) throws IOException {
        //获取打开方式
        openStyle = openStyle == null ? "attachment" : openStyle;
        //获取文件信息
        //根据文件信息中文件名字 和 文件存储路径获取文件输入流
        String realpath = fileUrl.substring(0, 13);
        String fname = fileUrl.substring(13);
        System.out.println(realpath + " " + fname);
        //获取文件输入流
        FileInputStream is = new FileInputStream(new File(realpath, fname));
        //附件下载
        response.setHeader("content-disposition", openStyle + ";fileName=" + URLEncoder.encode(fname + "00", "UTF-8"));
        //获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    /*在线预览 待改 ok*/
    @RequestMapping("readingForCollection")
    public void readingForCollection(long cid, long uid, String fname, String type, long size, String location,
                                     HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        History history = new History(cid, uid, fname, type, size, location, new Date());
        System.out.println("预览");
        fileMapper.insAHistoryForCollection(history);

    }

    /*在线预览 待改 ok*/
    @RequestMapping("readingForShare")
    public void readingForShare(long sid, long uid, String fname, String type, long size, String location,
                                HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        History history = new History(sid, uid, fname, type, size, location, new Date());
        System.out.println("预览");
        fileMapper.insAHistoryForCollection(history);

    }

    /*查询全网用户数量 ok*/
    @RequestMapping("userCount")
    @ResponseBody
    public int userCount() {
        System.out.println("全网用户数量");
        //统计用户数量
        return userMapper.selUserCount();
    }

    /*查询全网下载次数 ok*/
    @RequestMapping("downRecordCount")
    @ResponseBody
    public int downRecordCount() {
        System.out.println("全网下载次数");
        return fileMapper.selDownRecordCount();
    }

    /*查询全网上传次数 ok*/
    @RequestMapping("upRecordCount")
    @ResponseBody
    public int upRecordCount() {
        System.out.println("全网上传次数");
        return fileMapper.selUpRecordCount();
    }

    /*文件类型饼状图统计*/
    @RequestMapping("showBingZ")
    @ResponseBody
    public List<FileParamVO> showBingZ(HttpServletRequest req) {
        System.out.println("饼状图统计");
        Long uid = (Long) req.getSession().getAttribute("uid");
        List<FileParamVO> fileParamVOS = fileMapper.selCollectionByUid(uid);
        if (fileParamVOS == null) {
            return null;
        } else {
            return fileParamVOS;
        }


    }

    /*返回用户收藏喜好*/
    @RequestMapping("showBingZTonNum")
    @ResponseBody
    public String showBingZTonNum(HttpServletRequest req) {
        Long uid = (Long) req.getSession().getAttribute("uid");
        List<FileParamVO> fileParamVOS = fileMapper.selCollectionByUid(uid);
        long max = fileParamVOS.get(0).getValue();
        int k = 0;
        for (int i = 0; i < fileParamVOS.size(); i++) {
            if (fileParamVOS.get(i).getValue() > max) {
                k = i;

            }

        }
        return fileParamVOS.get(k).getName();
    }

    /*管理员视图，显示未审核的共享记录*/
    @RequestMapping("adminShowShare")
    @ResponseBody
    public PojoViewVO<Share> adminShowShare() {
        System.out.println("显示未审核的共享记录");
        // 查询符合条件的文件
        List<Share> list = fileMapper.selShareByState();
        PojoViewVO<Share> pojoViewVO = new PojoViewVO<>();
        pojoViewVO.setCode(0);
        pojoViewVO.setCount(100);
        pojoViewVO.setMsg("");
        pojoViewVO.setData(list);
        return pojoViewVO;
    }

    /*管理员视图，审核文件给出通过或者不通过*/
    @RequestMapping("adminCheckShare")
    @ResponseBody
    public void adminCheckShare(int result, long sid) {
        System.out.println("管理员审核开始");
        fileMapper.updShareToStateAndResult(result, sid);
    }

    /*删除违规文件*/
    @RequestMapping("adminDelShare")
    @ResponseBody
    public void adminDelShare(long sid) {
        System.out.println("管理员删除违规文件");
        fileMapper.delAShareBySid(sid);

    }


    /*展示用户问题*/
    @RequestMapping("showQuesion")
    @ResponseBody
    public PojoViewVO<Quesion> showQuesion() {
        System.out.println("显示用户问题");
        List<Quesion> quesions = quesionMapper.selAllQuesion();
        PojoViewVO<Quesion> pojoViewVO = new PojoViewVO<>();
        pojoViewVO.setCode(0);
        pojoViewVO.setCount(100);
        pojoViewVO.setMsg("");
        pojoViewVO.setData(quesions);
        return pojoViewVO;

    }

    /*收集用户问题反馈*/
    @RequestMapping("CollectQuestion")
    @ResponseBody
    public void CollectQuestion(long uid, String aim, String evaluation, String msg) {
        System.out.println("进入问题反馈");
        Quesion quesion = new Quesion(0, uid, aim, evaluation, msg);
        quesionMapper.insQuesion(quesion);
    }

    /*删除用户问题*/
    @RequestMapping("delUserQuestion")
    @ResponseBody
    public void delUserQuestion(long qid) {
        System.out.println("删除用户问题");
        quesionMapper.delQuesion(qid);

    }


    /*显示session用户的全部浏览历史*/
    @RequestMapping("showHistory")
    @ResponseBody
    public PojoViewVO<History> showHistory(HttpServletRequest req) {
        System.out.println("显示我的浏览历史");
        // 当前用户的uid
        Long uid = (Long) req.getSession().getAttribute("uid");
        List<History> histories = fileMapper.selAllHistory(uid);

        PojoViewVO<History> pojoViewVO = new PojoViewVO<>();
        pojoViewVO.setCode(0);
        pojoViewVO.setCount(100);
        pojoViewVO.setMsg("");
        pojoViewVO.setData(histories);
        return pojoViewVO;
    }

    /*删除session用户的浏览历史*/
    @RequestMapping("delHistory")
    @ResponseBody
    public void delHistory(long hid) {
        System.out.println("删除浏览历史");
        fileMapper.delAHistory(hid);
    }


}
