package com.zr.springboot.controller;

import com.zr.springboot.entity.User;
import com.zr.springboot.entity.dto.UserDTO;
import com.zr.springboot.mapper.UserMapper;
import com.zr.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    //注册功能
    @PostMapping("/register")
    public boolean resign(@RequestBody User user){
        return userService.save(user);
    }

    //登录功能
    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO){
        String user_phone = userDTO.getUser_phone();
        String user_pw = userDTO.getUser_pw();
        if(user_phone == null||user_pw == null)
            return false;
        return userService.userLogin(userDTO);
    }
    //新增和修改
    @PostMapping("/add")
    public boolean save(@RequestBody User user){
        return userService.save(user);
    }
    //删除
    @DeleteMapping("/{user_phone}")
    public int delete(@PathVariable String user_phone){
        return userMapper.deleteByUserPhone(user_phone);
    }


}
