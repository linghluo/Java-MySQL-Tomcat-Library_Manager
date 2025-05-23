package com.linghluo.servlet;

import com.linghluo.pojo.Book;

import java.util.List;

public interface BookService {
    boolean borrowBook(int bookId, String username);  // 借书
    boolean returnBook(int bookId, String username);  // 还书
    boolean addBook(Book book);  // 添加图书
    List<Book> listBooks();  // 获取图书列表
}
