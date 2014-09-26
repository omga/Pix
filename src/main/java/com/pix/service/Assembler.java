package com.pix.service;

import com.pix.model.PixUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("assembler")
public class Assembler {


    @Transactional(readOnly = true)
    MyAuthUser buildUserFromUserEntity(final PixUser userEntity) {
        String username = userEntity.getUserName();
        String password = userEntity.getPassword();
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        if (userEntity.getUserName().equals("admin")) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new MyAuthUser(username, password, true, true, true, true, authList, userEntity);

    }

    ;
}

