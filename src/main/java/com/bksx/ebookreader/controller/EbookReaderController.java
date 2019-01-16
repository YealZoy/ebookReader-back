package com.bksx.ebookreader.controller;

import com.alibaba.fastjson.JSON;
import com.bksx.ebookreader.bean.*;
import com.bksx.ebookreader.service.BookService;
import com.bksx.ebookreader.service.UserBookService;
import com.bksx.ebookreader.service.UserService;
import com.bksx.ebookreader.service.WechartService;
import com.bksx.ebookreader.util.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class EbookReaderController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private WechartService wechartService;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private UserBookService userBookService;

    private String TOKEN = "wechat";


    private String appid = "wx578d3b84be4aa096";

    private String callBack = "http://ebookreader.zhengyuyan.com/callBack";

    private String scope = "snsapi_userinfo";

    private String appsecret = "7d7614cc8f1597d9251c28092c20aba4";

    @GetMapping("/")
    public String welcome(){
        return "Hello world";
    }

    // 验证服务有效性
    @GetMapping("/wechat")
    public String checkName(String signature,String timestamp,String nonce,String echostr){
        log.info("signature的值为:{}",signature);
        log.info("timestamp的值为:{}",timestamp);
        log.info("nonce的值为:{}",nonce);
        log.info("echostr的值为:{}",echostr);
        String sortString = WechartUtil.sort(TOKEN, timestamp, nonce);

        // 2.sha1加密
        String myString = WechartUtil.sha1(sortString);

        // 3.字符串校验
        if (myString != null && myString != "" && myString.equals(signature)) {
            log.info("微信-签名校验通过");
            //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            log.info("回复给微信的 echostr 字符串:{}", echostr);
            return echostr;
        } else {
            log.error("微信-签名校验失败");
            return "";
        }
    }

    // 获取授权码
    @GetMapping("/getcode")
    public Result getCode()throws Exception{
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ appid +"&redirect_uri="+ URLEncoder.encode(callBack,"utf-8") +"&response_type=code&scope="+scope+"&state=STATE#wechat_redirect";
        log.info("url地址为,{}",url);
        wechartService.getcode(url);
        return Result.success("/static/image/code.png");
    }


    @RequestMapping("/callBack")
    public Result callBack(String code,String state)throws Exception{
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
        String tokenJson = wechartService.getToken(url);
        JSONObject jsonObject = new JSONObject(tokenJson);
        String access_token = (String)jsonObject.get("access_token");

        // 拉去用户信息
        String infourl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+appid+"&lang=zh_CN";
        String winfo = HttpRequestUtils.httpGet(infourl,null,null);
        log.info("微信用户信息为:{}",winfo);

        User u = wechartService.wechatLogin(winfo);
        if(u == null){
            Result.failure(ResultCode.DATA_IS_WRONG);
        }
        return Result.success(u);
    }


    /**
     * 通过用户名和密码登陆
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        String uname = user.getUname();
        String password = user.getPassword();
        User u = userService.login(uname,password);
        if(u != null){
            u.setPassword("");
            return Result.success(u);
        }else{
            return Result.failure(ResultCode.DATA_IS_WRONG,"用户名和密码匹配失败");
        }
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        int i = userService.registerUser(user);
        if(i <= 0){
            return Result.failure(ResultCode.DATA_IS_WRONG,"注册失败");
        }else{
            return Result.success("注册成功");
        }
    }

    /**
     * 添加书籍
     * @param book
     * @return
     */
    @PostMapping("/addbook")
    public Result addBook(@RequestBody Book book){
        int i = bookService.addBook(book);
        if(i <= 0){
            return Result.failure(ResultCode.DATA_IS_WRONG,"书籍添加失败");
        }else{
            return Result.success("添加成功");
        }
    }

    /**
     * 书-用户关联表  记录新增
     * @param userBook
     * @return
     */
    @PostMapping("/addrecord")
    public Result addRecord(@RequestBody UserBook userBook){
        int i = userBookService.addUserBook(userBook);
        if(i <= 0){
            return Result.failure(ResultCode.DATA_IS_WRONG,"记录添加失败");
        }else{
            return Result.success("记录添加成功");
        }
    }

    /**
     * 根据用户id 和 书籍名称 查询书籍列表
     * @param uid
     * @param bname
     * @return
     */
    @GetMapping("/listbook")
    public Result listBook(String uid,String bname){
        List<UserBookListBean> listBeans = bookService.listBook(uid,bname);
        return Result.success(listBeans);
    }

    //处理头像上传
    @PostMapping("/headimg")
    public Result headimg(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) {
        String contentType = file.getContentType();
        //String fileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replaceAll("-","");
        String filePath = uploadFolder + "/headimg/";
        System.out.print(filePath);
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // 上传文件失败
            return Result.failure(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR);
        }
        //返回json
        return Result.success(fileName);
    }

    // 处理书籍封面上传
    @PostMapping("/coverimg")
    public Result coverimg(@RequestParam("file") MultipartFile file,
                           HttpServletRequest request){
        String contentType = file.getContentType();
        //String fileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replaceAll("-","");
        String filePath = uploadFolder + "/coverimg/";
        System.out.print(filePath);
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // 上传文件失败
            return Result.failure(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR);
        }
        //返回json
        return Result.success(fileName);

    }

    @PostMapping("/bookfile")
    public Result bookfile(@RequestParam("file") MultipartFile file,
                           HttpServletRequest request){
        String contentType = file.getContentType();
        //String fileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString().replaceAll("-","");
        String filePath = uploadFolder + "/bookfile/";
        System.out.print(filePath);
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // 上传文件失败
            return Result.failure(ResultCode.INTERFACE_OUTTER_INVOKE_ERROR);
        }
        return Result.success(fileName);
    }

}
