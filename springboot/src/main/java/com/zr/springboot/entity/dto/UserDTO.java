package com.zr.springboot.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {
    private String user_phone;
    @JsonIgnore
    private String user_pw;
}
