package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.Book;
import com.bksx.ebookreader.bean.UserBookListBean;
import com.bksx.ebookreader.mapper.BookMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    /**
     * 添加书
     * @param book 书
     * @return
     */
    public int addBook(Book book){
        book.setBid(UUID.randomUUID().toString().replaceAll("-",""));
        book.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        book.setUseful("1");
        return bookMapper.addBook(book);
    }

    /**
     * 插叙书籍id 是否有效
     * @param bid
     * @return
     */
    public int volidateBookId(String bid){
        return bookMapper.findByBid(bid);
    }

    /**
     * 根据用户 查询书籍列表
     * @param uid
     * @return
     */
    public List<UserBookListBean> listBook(String uid,String bname){
        List<UserBookListBean> listBeans = bookMapper.queryBookByUid(uid,bname);
        if(listBeans != null && listBeans.size() > 0){
            for(UserBookListBean userBookListBean:listBeans){
                // bookurl
                String bookurl = userBookListBean.getBookurl();
                String coverimg = userBookListBean.getCoverimg();

                if(StringUtils.isNotEmpty(bookurl)){
                    userBookListBean.setBookurl(uploadFolder + bookurl);
                }
                // coverimg
                if(StringUtils.isNotEmpty(coverimg)){
                    userBookListBean.setBookurl(uploadFolder + coverimg);
                }
            }
        }
        return listBeans;
    }
}
