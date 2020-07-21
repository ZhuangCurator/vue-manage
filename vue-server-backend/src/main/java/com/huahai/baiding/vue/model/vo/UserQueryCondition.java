package com.huahai.baiding.vue.model.vo;

/**
 * @Author: BaiDing
 * @Date: 2018/10/26 13:31
 * @Email: liujiabaiding@foxmail.com
 */
public class UserQueryCondition {

    private String email;
    private String startRegisterDate;
    private String endRegisterDate;
    private String userIdArray;
    private int current = 1;
    private int pageSize = 50;
    private int start = 0;
    private long[] idArray;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartRegisterDate() {
        return startRegisterDate;
    }

    public void setStartRegisterDate(String startRegisterDate) {
        this.startRegisterDate = startRegisterDate;
    }

    public String getEndRegisterDate() {
        return endRegisterDate;
    }

    public void setEndRegisterDate(String endRegisterDate) {
        this.endRegisterDate = endRegisterDate;
    }

    public String getUserIdArray() {
        return userIdArray;
    }

    public void setUserIdArray(String userIdArray) {
        this.userIdArray = userIdArray;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
        setStart();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    private void setStart() {
        this.start = this.current > 1 ? (this.current - 1) * pageSize : 0;
    }

    public long[] getIdArray() {
        return idArray;
    }

    public void setIdArray(long[] idArray) {
        this.idArray = idArray;
    }
}
