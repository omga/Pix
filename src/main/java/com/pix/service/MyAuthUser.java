package com.pix.service;

import com.pix.model.PixUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyAuthUser extends User {
    private PixUser pixUser;

    public MyAuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, PixUser pixUser) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.pixUser = pixUser;
    }

    public MyAuthUser(PixUser pixUser, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.pixUser = pixUser;
    }

    public PixUser getPixUser() {
        return pixUser;
    }

    public void setPixUser(PixUser pixUser) {
        this.pixUser = pixUser;
    }
}
