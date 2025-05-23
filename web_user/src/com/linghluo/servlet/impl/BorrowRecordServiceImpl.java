package com.linghluo.servlet.impl;

import com.linghluo.pojo.BorrowRecord;
import com.linghluo.servlet.BorrowRecordService;
import com.linghluo.dao.BorrowRecordDao;
import com.linghluo.dao.impl.BorrowRecordDaoImpl;
import java.util.List;

public class BorrowRecordServiceImpl implements BorrowRecordService {
    private BorrowRecordDao borrowRecordDao = new BorrowRecordDaoImpl();  // 注入 Dao 层

    @Override
    public List<BorrowRecord> getBorrowHistory(String username) {
        return borrowRecordDao.getBorrowHistoryByUser(username);  // 通过 Dao 获取借阅记录
    }
}
