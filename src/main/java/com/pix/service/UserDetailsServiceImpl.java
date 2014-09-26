package com.pix.service;

import com.pix.model.PixUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PixService pixService;
    @Autowired
    private Assembler assembler;


    @Transactional(readOnly = true)
    @Override
    public MyAuthUser loadUserByUsername(String username)
            throws UsernameNotFoundException {
        PixUser userEntity = pixService.getUser(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        MyAuthUser userDetails = assembler.buildUserFromUserEntity(userEntity);
        return userDetails;
    }

}
