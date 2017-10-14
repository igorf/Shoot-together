package com.github.igorf.shoot.logic.auth;

import com.github.igorf.shoot.logic.dao.ProfileDao;
import com.github.igorf.shoot.logic.domain.Profile;
import com.github.igorf.shoot.logic.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ProfileDao profileDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profile user = profileDao.findByLogin(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (user != null) {
            if (user.getRoles() != null) {
                for (Role role : user.getRoles()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
                }
            }
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
        }

        return null;
    }
}