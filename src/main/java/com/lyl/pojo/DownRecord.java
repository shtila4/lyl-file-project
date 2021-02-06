package com.lyl.pojo;

import java.util.Date;

public class DownRecord {
    private long wid;
    private long uid;
    private String fname;
    private String type;
    private long size;
    private String location;
    private Date wdate;

    public DownRecord() {
    }

    public DownRecord(long wid, long uid, String fname, String type, long size, String location, Date wdate) {
        this.wid = wid;
        this.uid = uid;
        this.fname = fname;
        this.type = type;
        this.size = size;
        this.location = location;
        this.wdate = wdate;
    }
    public DownRecord(long wid, long uid, String fname, String type, long size, Date wdate) {
        this.wid = wid;
        this.uid = uid;
        this.fname = fname;
        this.type = type;
        this.size = size;

        this.wdate = wdate;
    }

    public long getWid() {
        return wid;
    }

    public long getUid() {
        return uid;
    }

    public String getFname() {
        return fname;
    }

    public String getType() {
        return type;
    }

    public long getSize() {
        return size;
    }

    public String getLocation() {
        return location;
    }

    public Date getWdate() {
        return wdate;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWdate(Date wdate) {
        this.wdate = wdate;
    }

    @Override
    public String toString() {
        return "DownRecord{" +
                "wid=" + wid +
                ", uid=" + uid +
                ", fname='" + fname + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", location='" + location + '\'' +
                ", wdate=" + wdate +
                '}';
    }
}
