package com.linghluo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowRecord implements Serializable {
    private int bookId;  // 传出 bookId 属性
    private String bookTitle;   // 图书的标题
    private Date borrowDate;    // 借阅日期
    private boolean returned;   // 归还状态
}
