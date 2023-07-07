package com.devmountain.specaptsone.BookTime.service;

import com.devmountain.specaptsone.BookTime.DTO.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(UserDTO userDTO);

    List<String> userLogin(UserDTO userDTO);
}
