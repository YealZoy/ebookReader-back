package com.bksx.ebookreader.interceptor;

import com.bksx.ebookreader.util.JWTToken;
import com.bksx.ebookreader.util.Result;
import com.bksx.ebookreader.util.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class TokenInterceptor  implements HandlerInterceptor {
    @Autowired
    private JWTToken jwtToken;

    long start = System.currentTimeMillis();
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        start = System.currentTimeMillis();
        String token = httpServletRequest.getHeader("token");
        if(StringUtils.isNoneBlank(token)){
            if(jwtToken.volidateToken(token)){
                return true;
            }
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        Result result = Result.failure(ResultCode.USER_NOT_LOGGED_IN);
        JSONObject jsonObject = new JSONObject(result);
        printWriter.write(JSONObject.valueToString(jsonObject));
        return false;

    }

}
