package com.hjb.entity;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 19:12
 */
public class PageBean<T> {

    private List<T> list;//保存当前分页的集合数据
    private int currentPage;//当前页
    private int pageSize;//每页显示条数
    private int totalPage;//总页数
    private int totalCount;//总条数

    public PageBean() {
    }

    public PageBean(List<T> list, int currentPage, int pageSize, int totalCount) {
        this.list = list;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        //10 3 3
        //算出总页数
        return (int) Math.ceil(this.totalCount*1.0/this.pageSize);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
