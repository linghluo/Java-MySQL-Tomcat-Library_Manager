package com.linghluo.dao;

import com.linghluo.pojo.BorrowRecord;
import java.util.List;

public interface BorrowRecordDao {
    List<BorrowRecord> getBorrowHistoryByUser(String username);// 根据用户名查询借阅记录
}
