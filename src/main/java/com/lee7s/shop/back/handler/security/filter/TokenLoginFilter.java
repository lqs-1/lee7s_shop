package com.lee7s.shop.back.handler.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.SecurityUser;
import com.lee7s.shop.back.entity.User;
import com.lee7s.shop.back.handler.security.utils.JwtToken;
import com.lee7s.shop.back.handler.security.utils.ResponseUtils;
import com.lee7s.shop.back.service.UserService;
import com.lee7s.shop.back.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author somg
 * @date 2023/3/18 16:24
 * @do 登录拦截器
 */
@Slf4j
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private JwtToken jwtToken;

    private AuthenticationManager authenticationManager;

    private UserService userService;


    public TokenLoginFilter(AuthenticationManager authenticationManager, JwtToken jwtToken, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
        this.userService = userService;
        // 不是只允许post请求,才能经过这个filter
        this.setPostOnly(false);
        // 设置登录的路径和请求方式
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login", "POST"));
    }

    /**
     * 执行认证的方法 给认证做准备工作，获取前端传递的数据
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 获取表单提交的数据
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("登录认证之前的准备工作执行了");

        try {
            User user = objectMapper.readValue(request.getInputStream(), User.class);

            // 校验 认证的过程
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassWord(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 认证成功以后
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        // 先验证图形验证码是否正确
        String imageKey = request.getParameter("imageKey");
        String code = request.getParameter("code");


        // 获取当前用户 UserDetails
        SecurityUser securityUser = (SecurityUser) authResult.getPrincipal();

        log.info(securityUser.getUsername() + " 登录成功了");
        String username = securityUser.getUsername();


        // 组织token对象
        User user = new User();
        user.setUserName(securityUser.getUsername());
        user.setPassWord(securityUser.getPassword());

        // 生成token
        String token = jwtToken.createTokenObjectAsBody(user);


        log.info(username + " 登录成功返回数据");

        // 更新登录时间

        // 设置请求头
        response.addHeader(jwtToken.authHeaderName, token);
        response.addHeader(Constant.ACCESS_CONTROL_EXPOSE_HEADERS, jwtToken.authHeaderName);

        // 回写数据
        ResponseUtils.out(response, R.ok(REnum.LOGIN_SUCCESS.getStatusCode(), REnum.LOGIN_SUCCESS.getStatusMsg()).put("token", token));

    }


    /**
     * 认证失败
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtils.out(response, R.error(REnum.LOGIN_FAIL.getStatusCode(),REnum.LOGIN_FAIL.getStatusMsg()));
    }
}
