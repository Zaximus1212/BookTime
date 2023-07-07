package com.devmountain.specaptsone.BookTime.controller;

import com.devmountain.specaptsone.BookTime.DTO.UserDTO;
import com.devmountain.specaptsone.BookTime.service.UserService;
import com.devmountain.specaptsone.BookTime.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDTO userDTO) {
        String passHash = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(passHash);
        return userService.addUser(userDTO);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDTO userDTO) {return userService.userLogin(userDTO);}
}
