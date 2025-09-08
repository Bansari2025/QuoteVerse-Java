package com.bansarishah.QuoteVerse.security;

import com.bansarishah.QuoteVerse.User;
import com.bansarishah.QuoteVerse.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Find the user in your database by their username
        User appuser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // 2. Convert your User object into a UserDetails object that Spring Security understands
        return new org.springframework.security.core.userdetails.User(appuser.getUsername(), appuser.getPassword(), Collections.emptyList());
    }
}
