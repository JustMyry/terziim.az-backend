package com.terziim.backend.security.service;

import com.terziim.backend.security.model.AppUserPrincipal;
import com.terziim.backend.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    public UserDetailsServiceImpl(UserRepository repository){
        this.repository=repository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserPrincipal principal = new AppUserPrincipal(repository.findUserFromLogin(username));
        return principal;
    }
}

