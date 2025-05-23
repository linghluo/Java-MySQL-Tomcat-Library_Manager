package com.linghluo.controller;

import com.google.gson.Gson;
import com.linghluo.pojo.Book;
import com.linghluo.servlet.BookService;
import com.linghluo.servlet.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/book/*")
public class BookController extends HttpServlet {
    private BookService bookService = new BookServiceImpl();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] paths = requestURI.split("/");
        String method = paths[paths.length - 1];

        switch (method) {
            case "add" -> addBook(req, resp);
            case "list" -> listBooks(req, resp);
            case "borrow" -> borrowBook(req, resp);
            case "return" -> returnBook(req, resp);
            default -> resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }

    // 处理图书添加请求
    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        Book book = new Book(0, title);
        boolean result = bookService.addBook(book);

        if (result) {
            resp.sendRedirect(req.getContextPath() + "/book_list.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }

    // 处理图书列表展示请求
    private void listBooks(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        List<Book> books = bookService.listBooks();

        if (books != null) {
            String jsonResponse = new Gson().toJson(books);
            resp.getWriter().write(jsonResponse);
        } else {
            // 如果查询图书失败，返回错误页面的 URL
            resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }

    // 处理借阅图书请求
    private void borrowBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String username = req.getParameter("user");

        boolean result = bookService.borrowBook(bookId, username);

        // 返回结果给前端，前端决定是否跳转
        resp.setContentType("application/json");
        if (result) {
            String jsonResponse = "{\"status\":\"success\",\"redirectUrl\":\"/success.html\"}";
            resp.getWriter().write(jsonResponse);
        } else {
            String jsonResponse = "{\"status\":\"error\",\"redirectUrl\":\"/noFound404.html\"}";
            resp.getWriter().write(jsonResponse);
        }
    }

    // 处理归还图书请求
    private void returnBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String username = req.getParameter("user");

        boolean result = bookService.returnBook(bookId, username);

        if (result) {
            resp.sendRedirect(req.getContextPath() + "/success.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/noFound404.html");
        }
    }
}
