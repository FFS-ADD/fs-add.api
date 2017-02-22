package com.accenture.fsadd.web.setting.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.user.entity.User;
import com.accenture.fsadd.web.setting.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findAllValidUsers() {
        // TODO Auto-generated method stub
        return null;
    }


}
