package com.linghluo.controller;

import com.google.gson.Gson;
import com.linghluo.pojo.BorrowRecord;
import com.linghluo.servlet.BorrowRecordService;
import com.linghluo.servlet.impl.BorrowRecordServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/borrow/*")
public class BorrowHistoryController extends HttpServlet {
    private BorrowRecordService borrowRecordService = new BorrowRecordServiceImpl();  // 注入 Service 层

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] paths = requestURI.split("/");
        String method = paths[paths.length - 1];

        switch (method) {
            case "records" -> records(req, resp);  // 获取借阅记录
            default -> resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }

    // 获取借阅记录
    private void records(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user");  // 获取传递的用户名

        if (username == null || username.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "用户名不能为空");
            return;
        }

        // 调用服务层获取借阅记录
        List<BorrowRecord> borrowRecords = borrowRecordService.getBorrowHistory(username);

        // 如果没有借阅记录
        if (borrowRecords == null || borrowRecords.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);  // 返回无内容
            return;
        }

        // 转换借阅记录为 JSON 格式并返回
        String jsonResponse = new Gson().toJson(borrowRecords);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(jsonResponse);  // 返回 JSON 格式的数据
    }
}



