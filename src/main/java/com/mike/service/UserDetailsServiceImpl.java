package com.mike.service;

import com.mike.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserDetailsService} interface.
 *
 * @author Denisenko Mikhail
 * @version 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOG = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findUserByName(name);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getTitle()));
        LOG.info("UserDetailsInfo " + name + Arrays.toString(grantedAuthorities.toArray()));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
    }
}
