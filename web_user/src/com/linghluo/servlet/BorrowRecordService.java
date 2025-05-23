package com.linghluo.servlet;

import com.linghluo.pojo.BorrowRecord;
import java.util.List;

public interface BorrowRecordService {
    List<BorrowRecord> getBorrowHistory(String username);  // 获取用户的借阅历史
}
