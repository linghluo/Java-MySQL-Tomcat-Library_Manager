package com.linghluo.servlet;

import com.linghluo.pojo.Info;
import com.linghluo.pojo.Regist;

public interface UserServlet {

    boolean regist(Regist regist);

    Info login(Regist login);

    boolean del(Regist regist);

    boolean stnm(Regist regist);

    boolean update(Regist regist);
}
