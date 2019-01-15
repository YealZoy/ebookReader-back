package com.bksx.ebookreader.controller;

import com.bksx.ebookreader.bean.Book;
import com.bksx.ebookreader.bean.User;
import com.bksx.ebookreader.bean.UserBook;
import com.bksx.ebookreader.bean.UserBookListBean;
import com.bksx.ebookreader.service.BookService;
import com.bksx.ebookreader.service.UserBookService;
import com.bksx.ebookreader.service.UserService;
import com.bksx.ebookreader.util.FileUtil;
import com.bksx.ebookreader.util.Result;
import com.bksx.ebookreader.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class EbookReaderController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    private UserBookService userBookService;

    @GetMapping("/")
    public String welcome(){
        return "Hello world";
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
