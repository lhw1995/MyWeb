package com.restaurant.controller.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by lhw on 2016/12/19.
 */
@Component("page")
@Scope("prototype")
public class Page {
    //总页数
    private int totalPageCount=1;
    //页面大小，即每页显示记录数
    private int pageSize=0;
    //记录总数
    private int totalCount=0;
    //当前页号
    private int currPageNo=1;

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        this.currPageNo = currPageNo;
    }
}
