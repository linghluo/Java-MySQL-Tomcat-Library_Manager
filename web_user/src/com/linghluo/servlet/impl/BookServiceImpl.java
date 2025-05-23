package com.linghluo.servlet.impl;

import com.linghluo.pojo.Book;
import com.linghluo.servlet.BookService;
import com.linghluo.dao.BookDao;
import com.linghluo.dao.impl.BookDaoImpl;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public boolean addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public List<Book> listBooks() {
        return bookDao.listBooks();
    }

    @Override
    public boolean borrowBook(int bookId, String username) {
        return bookDao.borrowBook(bookId, username);
    }

    @Override
    public boolean returnBook(int bookId, String username) {
        return bookDao.returnBook(bookId, username);
    }
}
