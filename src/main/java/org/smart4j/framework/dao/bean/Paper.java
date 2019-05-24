package org.smart4j.framework.dao.bean;

import org.smart4j.framework.core.bean.BaseBean;

import java.util.List;

/**
 * 分页对象
 */
public class Paper<T> extends BaseBean {
    private static final long serialVersiiionUID = 4423502157612253594L;
    //页面编号
    private int pageNumber;
    //每页条数
    private int pageSize;
    //总记录数
    private long totalRecord;
    //总页面数
    private long totalPage;
    //数据列表
    private List<T> recordList;

    public Paper() {

    }

    public Paper(int pageNumber, int pageSize, long totalRecord, List<T> recordList) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.recordList = recordList;
        if (pageSize != 0) {
            totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public List<T> getRecordList() {
        return recordList;
    }



    public boolean isFirstPage(){
        return pageNumber==1;
    }

    public boolean isLastPage(){
        return pageNumber==totalPage;
    }

    public boolean isPrevPage(){
        return pageNumber>1 && pageNumber<=totalPage;
    }

    public boolean isNextPage(){
        return pageNumber<totalPage;
    }


}
