package com.lyl.vo;

import lombok.*;

import java.util.List;


public class PojoViewVO<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T>data;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCount() {
        return count;
    }

    public List<T> getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public PojoViewVO(Integer code, String msg, Integer count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public PojoViewVO() {
    }
}
