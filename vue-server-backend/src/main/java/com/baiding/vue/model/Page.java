package com.baiding.vue.model;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/12 14:18
 * @Email: liujiabaiding@foxmail.com
 */
public class Page<T> {
    /**
     * 默认页大小
     */
    private final static int defaultPageSize=50;
    /**
     * 每页显示记录数
     */
    private int pageSize=defaultPageSize;
    /**
     * 当前页数
     */
    private int pageNow=1;
    /**
     * 总页数
     */
    private int pageTotal;
    /**
     * 总纪录数
     */
    private int recordTotal;
    /**
     * 当前页面所携带的数据
     */
    private List<T> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal() {
        if(recordTotal % pageSize == 0){
            this.pageTotal=recordTotal / pageSize;
        }else{
            this.pageTotal=(recordTotal / pageSize) + 1;
        }
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
        setPageTotal();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static class Builder<T>{

        private Page<T> instance;

        Builder(Page<T> p){this.instance = p;}

        public Builder(){this(new Page<>());}

        public Builder<T> setPageNow(int pageNow){
            this.instance.pageNow = pageNow;
            return this;
        }

        public Builder<T> setRecordTotal(int recordTotal){
            this.instance.recordTotal = recordTotal;
            this.instance.setPageTotal();
            return this;
        }

        public Builder<T> setPageTotal(int pageTotal){
            this.instance.pageTotal = pageTotal;
            return this;
        }

        public Builder<T> setList(List<T> list){
            this.instance.list = list;
            return this;
        }

        public Page<T> build(){
            return this.instance;
        }
    }
}
