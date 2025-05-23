package com.linghluo.servlet.impl;

import com.linghluo.dao.RegistDao;
import com.linghluo.dao.impl.RegistDaoImpl;
import com.linghluo.pojo.Info;
import com.linghluo.pojo.Regist;
import com.linghluo.servlet.UserServlet;
import jakarta.servlet.http.Part;

public class UserServletimpl implements UserServlet {
    private RegistDao registDao = new RegistDaoImpl();

    @Override
    public boolean regist(Regist regist) {
       return registDao.insert(regist);
    }

    @Override
    public Info login(Regist login) {
        return registDao.selectByDo(login);
    }

    @Override
    public boolean del(Regist regist) {
        return registDao.delete(regist);
    }

    @Override
    public boolean stnm(Regist regist) {
        return registDao.stnm(regist);
    }

    @Override
    public boolean update(Regist regist) {
        return registDao.update(regist);
    }

}
