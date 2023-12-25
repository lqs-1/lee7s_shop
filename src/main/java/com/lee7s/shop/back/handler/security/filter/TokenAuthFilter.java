package com.lee7s.shop.back.handler.security.filter;

import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.constant.REnum;
import com.lee7s.shop.back.entity.User;
import com.lee7s.shop.back.handler.security.utils.JwtToken;
import com.lee7s.shop.back.handler.security.utils.ResponseUtils;
import com.lee7s.shop.back.utils.R;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.CollectionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author somg
 * @date 2023/3/18 17:04
 * @do 处理权限的过滤器 请求过来就会来到这里 除了登录请求
 */

@Slf4j
public class TokenAuthFilter extends BasicAuthenticationFilter {


    public final static ThreadLocal<String> userNameThreadLocal = new ThreadLocal<>();

    private JwtToken jwtToken;


    private AuthenticationManager authenticationManager;


    public TokenAuthFilter(AuthenticationManager authenticationManager, JwtToken jwtToken) {
        super(authenticationManager);
        this.jwtToken = jwtToken;
    }


    /**
     * 权限相关操作 将权限从redis中取出来 请求进来之后就来这里（除了登录和注册）
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


            try{
                // 获取token
                String token = request.getHeader("token");

                if (!StringUtils.isNullOrEmpty(token)){

                    User user = jwtToken.parseObject(token, User.class);

                    // 刷新token
                    if (jwtToken.refresh(token)){

                        response.addHeader(jwtToken.authHeaderName, jwtToken.refreshSingleParamToken(token));
                        response.addHeader(Constant.ACCESS_CONTROL_EXPOSE_HEADERS, jwtToken.authHeaderName);

                    }


                    // 生成权限信息对象
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),token, null);
                    // 把权限信息对象存入到权限上下文中
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    userNameThreadLocal.set(user.getUserName());
                }

                log.info((userNameThreadLocal.get() == null ? "匿名用户" : userNameThreadLocal.get()) + " 获取了权限 -> 访问路径 [" + request.getRequestURI() + "] -> 放行");
                // 放行
                chain.doFilter(request, response);

            }catch (Exception e){

                ResponseUtils.out(response, R.error(REnum.AUTH_FAIL.getStatusCode(), REnum.AUTH_FAIL.getStatusMsg()));

            }

    }
}
