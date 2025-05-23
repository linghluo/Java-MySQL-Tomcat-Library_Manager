package com.linghluo.controller;

import com.linghluo.pojo.Info;
import com.linghluo.pojo.Regist;
import com.linghluo.servlet.UserServlet;
import com.linghluo.servlet.impl.UserServletimpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class SysUserController extends HttpServlet {
    private UserServlet userServlet = new UserServletimpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] paths = requestURI.split("/");
        String method = paths[paths.length - 1];

        switch (method) {
            case "regist" -> regist(req, resp);//注册
            case "login" -> login(req, resp);//登入
            case "del" -> del(req, resp);//注销
            case "setusername" -> stnm(req, resp);//设置用户名
            case "update" -> update(req, resp);//更新
            default -> resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String registname = req.getParameter("registname");
        String registpassword = req.getParameter("registpassword");
        Regist regist = new Regist(registname,registpassword,null);

        boolean tag = userServlet.regist(regist);
        if (tag) {
            resp.sendRedirect(req.getContextPath() + "/registrationstart.html");
        }else {
            resp.sendRedirect(req.getContextPath() + "/registrationFail.html");
        }
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginname = req.getParameter("loginname");
        String loginpassword = req.getParameter("loginpassword");
        Regist login = new Regist(loginname,loginpassword,null);

        Info info = userServlet.login(login);
        if (info.getTag() == 0) {
            resp.sendRedirect(req.getContextPath() + "/loginFail.html");
        }else if (info.getTag() == 1) {
            resp.sendRedirect(req.getContextPath() + "/newWorld.html");
        }else{
            resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }
    protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delname = req.getParameter("delname");
        String delpassword = req.getParameter("delpassword");
        Regist regist = new Regist(delname,delpassword,null);

        boolean tag = userServlet.del(regist);
        if (tag) {
            resp.sendRedirect(req.getContextPath() + "/success.html");
        }else {
            resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }

    protected void stnm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stname = req.getParameter("stname");
        String stpassword = req.getParameter("stpassword");
        String stown = req.getParameter("stown");
        Regist regist = new Regist(stname,stpassword,stown);

        boolean tag = userServlet.stnm(regist);
        if (tag) {
            resp.sendRedirect(req.getContextPath() + "/success.html");
        }else {
            resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stname = req.getParameter("stname");
        String stpassword = req.getParameter("stpassword");
        String stown = req.getParameter("stown");
        Regist regist = new Regist(stname,stpassword,stown);

        boolean tag = userServlet.update(regist);
        if (tag) {
            resp.sendRedirect(req.getContextPath() + "/success.html");
        }else {
            resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }
}
