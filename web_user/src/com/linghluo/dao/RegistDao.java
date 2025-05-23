package com.linghluo.dao;

import com.linghluo.pojo.Info;
import com.linghluo.pojo.Regist;

import java.util.List;


public interface RegistDao {

    boolean insert(Regist regist);//注册（插入）

    Info selectByDo(Regist regist);//登入（查找）

    boolean update(Regist regist);//修改用户名密码

    boolean delete(Regist regist);//注销

    boolean stnm(Regist regist);//修改用户名

}
