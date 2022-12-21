package com.itmo.security;

import com.itmo.entity.Owner;
import com.itmo.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    OwnerRepository ownerRepositoryImpl;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepositoryImpl.findFirstByName(username);
        return SecurityUser.toSecuredUser(owner);
    }
}
