package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.DTO.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(userName);

        if( user == null){
            logger.error("user not found: " + userName);
            throw new UsernameNotFoundException("Email not found");
        }

        logger.info("User found: " + userName);
        return user;
    }

    @Transactional(readOnly = true)
    public UserDTO getLoogedUser(){
        User user = authService.authenticated();

        return new UserDTO(user);
    }
}
