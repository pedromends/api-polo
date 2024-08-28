package com.ifmg.apipolo.login;

import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MyCustomUserDetails extends User implements UserDetails  {

    private final User user;

    public MyCustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();

        if(this.user.getRole().equals(Roles.CODEMASTER.toString())){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_CODEMASTER");
            updatedAuthorities.add(authority);
        }
        else if(this.user.getRole().equals(Roles.ADMIN.toString())){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            updatedAuthorities.add(authority);
        } else {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            updatedAuthorities.add(authority);
        }

        return updatedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return this.user.getId();
    }

    public String getFirstName() {
        return this.user.getFirstName();
    }

    public String getLastName() {
        return this.user.getLastName();
    }
}
