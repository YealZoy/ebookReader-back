package com.bksx.ebookreader.service;

import com.bksx.ebookreader.bean.User;
import com.bksx.ebookreader.bean.Wechart;
import com.bksx.ebookreader.bean.WechartToken;
import com.bksx.ebookreader.mapper.UserMapper;
import com.bksx.ebookreader.mapper.WechartMapper;
import com.bksx.ebookreader.util.HttpRequestUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WechartService {

    @Autowired
    private WechartMapper wechartMapper;

    @Autowired
    private UserMapper userMapper;



    /**
     * 添加微信用户
     * @param wechart 微信
     * @return
     */
    public int addWechart(Wechart wechart){
        return wechartMapper.addWechart(wechart);
    }

    /**
     * 微信授权 获取code
     * @param url
     *
     */
    public void getcode(String url)throws Exception{
        HttpRequestUtils.httpGet(url,null,null);
    }

    /**
     * 微信获取token
     * @param url
     * @return
     */
    public String getToken(String url) throws Exception{
        return HttpRequestUtils.httpGet(url,null,null);
    }

    /**
     * 获取微信用户信息后登陆
     * @param jsonobj
     * @return
     */
    public User wechatLogin(String jsonobj){
        JSONObject jsonObject =  new JSONObject(jsonobj);
        String openid = (String)jsonObject.get("open");
        int i = wechartMapper.getOpenid(openid);
        if(i <= 0){
            String nickname = (String)jsonObject.get("nickname");
            String sex = (String) jsonObject.get("sex");
            String province = (String)jsonObject.get("province");
            String city = (String)jsonObject.get("city");
            String country = (String)jsonObject.get("country");
            String headimgurl = (String)jsonObject.get("headimgurl");
            Wechart wechart = new Wechart();
            wechart.setOpenid(openid);
            wechart.setNickname(nickname);
            wechart.setSex(sex);
            wechart.setProvince(province);
            wechart.setCity(city);
            wechart.setCountry(country);
            wechart.setHeadimgurl(headimgurl);

            wechartMapper.addWechart(wechart);

            User user = new User();
            user.setHeadimgurl(headimgurl);
            user.setOpenid(openid);
            user.setUname(nickname);

            userMapper.addUser(user);
        }

        return userMapper.findByOpenId(openid);
    }
}
