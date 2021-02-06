package com.lyl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

public class History {

    private long hid;
    private long uid;
    private String fname;
    private String type;
    private long size;
    private String location;
    private Date hdate;

    public History(long hid, long uid, String fname, String type, long size, String location, Date hdate) {
        this.hid = hid;
        this.uid = uid;
        this.fname = fname;
        this.type = type;
        this.size = size;
        this.location = location;
        this.hdate = hdate;
    }

    public History() {
    }

    public long getHid() {
        return hid;
    }

    public void setHid(long hid) {
        this.hid = hid;
    }

    public long getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "History{" +
                "hid=" + hid +
                ", uid=" + uid +
                ", fname='" + fname + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", location='" + location + '\'' +
                ", hdate=" + hdate +
                '}';
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getHdate() {
        return hdate;
    }

    public void setHdate(Date hdate) {
        this.hdate = hdate;
    }
}
