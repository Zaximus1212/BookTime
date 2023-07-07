package com.devmountain.specaptsone.BookTime.service;

import com.devmountain.specaptsone.BookTime.DTO.UserDTO;
import com.devmountain.specaptsone.BookTime.entity.User;
import com.devmountain.specaptsone.BookTime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDTO userDTO) {
        List<String> response = new ArrayList<>();
        User user = new User(userDTO);
        userRepository.saveAndFlush(user);
        response.add("http://localhost:8080/login.html");
        response.add("User added successfully.");
        return response;
    }

    @Override
    public List<String> userLogin(UserDTO userDTO) {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDTO.getUsername());
        if (userOptional.isPresent()) {
            if (passwordEncoder.matches(userDTO.getPassword(), userOptional.get().getPassword())) {
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getId()));
                response.add("User login was successful");
            } else {
                response.add("Username or password is incorrect");
            }
        } else {
            response.add("Username or password is incorrect");
        }
        return response;
    }

}
