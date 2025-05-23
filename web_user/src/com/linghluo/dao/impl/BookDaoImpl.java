package com.linghluo.dao.impl;

import com.linghluo.dao.BookDao;
import com.linghluo.pojo.Book;
import com.linghluo.pool.DruidTest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public boolean addBook(Book book) {
        String sql = "INSERT INTO book (title) VALUES (?)";
        try (Connection conn = DruidTest.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> listBooks() {
        String sql = "SELECT * FROM book";
        try (Connection conn = DruidTest.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title")));
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean borrowBook(int bookId, String username) {
//        if (!isUserExist(username)) {
//            System.out.println("用户不存在");
//            return false;  // 用户不存在
//        }
//
//        if (isBookBorrowed(bookId)) {
//            System.out.println("图书已被借出");
//            return false;  // 图书已被借出
//        }
//
//        if (hasUserBorrowedBook(bookId, username)) {
//            System.out.println("用户已经借阅过该图书");
//            return false;  // 用户已经借阅过该图书
//        }

        String updateBookSql = "UPDATE book SET is_borrowed = true WHERE id = ?";
        String insertBorrowRecordSql = "INSERT INTO borrow_record (user, book_id, borrow_date, returned) VALUES (?, ?, ?, false)";

        try (Connection conn = DruidTest.getConnection()) {
            // 更新图书的借阅状态
            try (PreparedStatement stmt = conn.prepareStatement(updateBookSql)) {
                stmt.setInt(1, bookId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("图书借阅状态更新失败");
                    return false;  // 图书借阅状态更新失败
                }
                System.out.println("图书借阅状态更新成功");
            }

            // 插入借阅记录
            try (PreparedStatement stmt = conn.prepareStatement(insertBorrowRecordSql)) {
                stmt.setString(1, username);  // 设置用户名
                stmt.setInt(2, bookId);       // 设置图书 ID
                stmt.setDate(3, Date.valueOf(LocalDate.now()));  // 设置借阅日期
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("借阅记录插入失败");
                    return false;  // 插入借阅记录失败
                }
                System.out.println("借阅记录插入成功");
            }

            return true;  // 借阅成功

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // 出现异常，返回失败
        }
    }

    @Override
    public boolean returnBook(int bookId, String username) {
        String updateBookSql = "UPDATE book SET is_borrowed = false WHERE id = ?";
        String updateBorrowRecordSql = "UPDATE borrow_record SET returned = true WHERE book_id = ? AND user = ? AND returned = false";

        try (Connection conn = DruidTest.getConnection()) {
            // 更新图书的借阅状态
            try (PreparedStatement stmt = conn.prepareStatement(updateBookSql)) {
                stmt.setInt(1, bookId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    return false;
                }
            }

            // 更新借阅记录
            try (PreparedStatement stmt = conn.prepareStatement(updateBorrowRecordSql)) {
                stmt.setInt(1, bookId);
                stmt.setString(2, username);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    return false;
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 检查用户是否存在
    private boolean isUserExist(String username) {
        String sql = "SELECT COUNT(*) FROM regist WHERE registN = ?";
        try (Connection conn = DruidTest.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;  // 如果返回的 count > 0，表示用户存在
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // 用户不存在
    }

    // 检查图书是否已被借出
    private boolean isBookBorrowed(int bookId) {
        String sql = "SELECT is_borrowed FROM book WHERE id = ?";
        try (Connection conn = DruidTest.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("is_borrowed");  // 如果 is_borrowed 为 true，表示图书已被借出
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // 图书未被借出
    }

    // 检查用户是否已借阅过该图书
    private boolean hasUserBorrowedBook(int bookId, String username) {
        String sql = "SELECT COUNT(*) FROM borrow_record WHERE book_id = ? AND user = ? AND returned = false";
        try (Connection conn = DruidTest.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setString(2, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // 如果返回的 count > 0，表示用户已借阅过该图书
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // 用户没有借阅过该图书
    }
}
