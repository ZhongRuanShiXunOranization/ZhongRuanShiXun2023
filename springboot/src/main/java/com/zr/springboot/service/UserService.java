package com.zr.springboot.service;


import com.zr.springboot.entity.User;
import com.zr.springboot.entity.dto.UserDTO;
import com.zr.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public boolean userLogin(UserDTO userDTO){
        List<User> users = userMapper.findAll();
        for(int i=0;i<users.size();i++){
            if(Objects.equals(users.get(i).getUser_phone(),userDTO.getUser_phone())&&
            Objects.equals(users.get(i).getUser_pw(),userDTO.getUser_pw()))
                return true;
        }
        return false;

    }
    public boolean save(User user){
        if(user.getUser_id()==null)
            return userMapper.insert(user);
        else
            return userMapper.update(user);
    }
    public boolean save(UserDTO userDTO){
        return userMapper.insert(userDTO);
    }
}
