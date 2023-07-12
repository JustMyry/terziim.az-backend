package com.terziim.backend.security.model;


import com.terziim.backend.icpmodel.BaseModel;
import com.terziim.backend.user.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

//Bu sinifin, sadece sinif oldugu ucun 'BaseEntity' extend etmeyine ehtiyac yoxdur
public class AppUserPrincipal extends BaseModel implements UserDetails {

    private AppUser appUser;
    public AppUserPrincipal(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.appUser.getAuthorities().stream()
                .map(r->new SimpleGrantedAuthority(r.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.isNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return appUser.isActive();
    }

    public String getUserId() { return appUser.getUserId(); }

    public boolean banned(){return appUser.isBanned();}

}
