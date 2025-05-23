package com.linghluo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Regist implements Serializable {
    private String registName;
    private String registPassword;
    private String registNameOwn;
}
