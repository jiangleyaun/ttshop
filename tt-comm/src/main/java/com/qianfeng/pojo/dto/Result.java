package com.qianfeng.pojo.dto;

import java.util.List;

/**
 * easyUI的表格分页的响应的参数
 * @param <T>
 */

public class Result<T> {
    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
