package com.zr.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer user_id;
    private String user_name;
    private String user_phone;
    private String user_pw;
    private String user_sex;
    private Date   user_birth;
}
