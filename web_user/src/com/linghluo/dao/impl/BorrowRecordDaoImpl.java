package com.linghluo.dao.impl;

import com.linghluo.dao.BorrowRecordDao;
import com.linghluo.pojo.BorrowRecord;
import com.linghluo.pool.DruidTest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDaoImpl implements BorrowRecordDao {

    @Override
    public List<BorrowRecord> getBorrowHistoryByUser(String username) {
        String sql = "SELECT b.id AS bookId, b.title AS bookTitle, br.borrow_date AS borrowDate, br.returned " +
                "FROM borrow_record br " +
                "JOIN book b ON br.book_id = b.id " +
                "WHERE br.user = ?";  // 查询所有的借阅记录

        try (Connection conn = DruidTest.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);  // 设置用户名

            try (ResultSet rs = stmt.executeQuery()) {
                List<BorrowRecord> borrowRecords = new ArrayList<>();
                while (rs.next()) {
                    BorrowRecord record = new BorrowRecord();
                    record.setBookId(rs.getInt("bookId"));  // 获取并设置 bookId
                    record.setBookTitle(rs.getString("bookTitle"));
                    record.setBorrowDate(rs.getDate("borrowDate"));
                    record.setReturned(rs.getBoolean("returned"));
                    borrowRecords.add(record);
                }
                return borrowRecords;  // 返回借阅记录
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // 如果查询出错或没有记录，返回 null
    }
}