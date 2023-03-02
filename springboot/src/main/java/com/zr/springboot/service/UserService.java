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
        userDTO.setUser_pw(getKey(userDTO.getUser_pw()));//加密
        List<User> users = userMapper.findAll();
        for(int i=0;i<users.size();i++){
            System.out.println(users.get(i).getUser_phone());
            System.out.println(users.get(i).getUser_pw());
            if(Objects.equals(users.get(i).getUser_phone(),userDTO.getUser_phone())&&
            Objects.equals(users.get(i).getUser_pw(),userDTO.getUser_pw()))
                return true;
        }
        return false;

    }
    public boolean save(User user){
        user.setUser_pw(getKey(user.getUser_pw()));//加密
        if(user.getUser_id()==null)
            return userMapper.insert(user);
        else
            return userMapper.update(user);
    }
    public boolean save(UserDTO userDTO){
        userDTO.setUser_pw(getKey(userDTO.getUser_pw()));//加密
        return userMapper.insert2(userDTO);
    }

    public String getKey(String user_pw){//加密法
        int[] keyR = {9,4,8,1,4,2,3};
        StringBuilder key;
        key = new StringBuilder(user_pw + user_pw);
        int user_pw_num = key.length();
        while(user_pw_num<9){
            key.append("0");
            user_pw_num++;
        }
        int keyR_num=0;
        for(int i=0;i<key.length();i++){
            key.replace(i,i+1, String.valueOf(key.charAt(keyR[keyR_num%7])));
            keyR_num++;
        }
        return key.toString();
    }

}
