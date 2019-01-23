package com.ssm.common.pager;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @param <T>
 * @author Administrator
 */
public class Pager<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页的大小
     */
    private int pageSize;
    /**
     * 分页的起始页
     */
    private int currentPage;
    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 分页的数据
     */
    private List<T> datas;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "com.ssm.common.dao.Pager";
    }

}
