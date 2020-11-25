package service;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import security.SecurityUser;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CustomUserDetailService implements UserDetailsService {

    IUserService userService;

    @Autowired
    public CustomUserDetailService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(s);

        if (user!=null){
            SecurityUser securityUser= (SecurityUser) SecurityUser.fromUser(user);
            return securityUser;
        } else{
            throw new UsernameNotFoundException("User not found");}

    }
}
