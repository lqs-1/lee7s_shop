package com.lee7s.shop.back.service.mpl;

import com.lee7s.shop.back.constant.Constant;
import com.lee7s.shop.back.entity.SecurityUser;
import com.lee7s.shop.back.entity.User;
import com.lee7s.shop.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author somg
 * @date 2023/3/20 12:13
 * @do 用户实现 和SpringSecurity登录逻辑实现
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private BCryptPasswordEncoder encoder;


    /**
     * 重写UserDetailsService实现登录逻辑
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = new User();
        if (username.equals(Constant.ADMIN_NAME)){
            encoder = new BCryptPasswordEncoder();
            user.setPassWord(encoder.encode(Constant.ADMIN_PASSWORD));
            user.setUserName(Constant.ADMIN_NAME);
        }
        // System.out.println(user);

        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUser(user);
        return securityUser;


    }
}
