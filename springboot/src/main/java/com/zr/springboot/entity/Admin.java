package com.zr.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Admin {
    private Integer ad_id;
    @JsonIgnore
    private String ad_pw;
}
