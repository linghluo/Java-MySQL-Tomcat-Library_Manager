package com.linghluo.dao;

import com.linghluo.pojo.Book;

import java.util.List;

public interface BookDao {
    boolean addBook(Book book);//添加书籍
    List<Book> listBooks();//已有图书列表
    boolean borrowBook(int bookId, String username);//借书
    boolean returnBook(int bookId, String username);//还书
}
