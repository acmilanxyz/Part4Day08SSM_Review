package org.spoto.utils;

import org.spoto.model.Users;

import java.util.List;

/**
 * 封装列表数据，通过对象获取两个方法的数据
 */

public class TableData<T> {     //泛型类

    /**
     * 页面索引
     */
    private Integer pageIndex;

    /**
     * 页面数据条数(每页的数据条数)
     */
    private Integer pageSize;

    /**
     * 总页数（一共可以显示多少页，依据dataCount和pageSize计算出来的）
     */
    private Integer pageCount;

    /**
     * 总数据条数（从数据库查询出来的数据总条数）
     */
    private Integer dataCount;

    /**
     * 数据列表
     */
    private List<T> dataList;

    public TableData() {
    }

    public TableData(Integer pageIndex, Integer pageSize, Integer pageCount, Integer dataCount, List<T> dataList) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.dataCount = dataCount;
        this.dataList = dataList;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    //直接调用工具类计算获取
    public Integer getPageCount() {
        Integer lastPage = PageUtils.getLastPage(this.dataCount, this.pageSize);
        this.pageCount = lastPage;
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
