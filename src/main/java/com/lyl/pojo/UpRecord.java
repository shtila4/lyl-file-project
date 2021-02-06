package com.lyl.pojo;

public class UpRecord {
    private long pid;
    private long uid;
    private String fname;
    private String type;
    private long size;
    private String location;
    private String udate;

    public UpRecord(long uid, String fname, String type, long size,String udate,long pid){
        this.uid = uid;
        this.fname = fname;
        this.type = type;
        this.size = size;
        this.udate = udate;
        this.pid=pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getPid() {
        return pid;
    }

    public UpRecord(long uid, String fname, String type, long size, String location){
        this.uid = uid;
        this.fname = fname;
        this.type = type;
        this.size = size;
        this.location=location;
    }
    public UpRecord(long uid, String fname, String type, long size, String location, String udate) {
        this.uid = uid;
        this.fname = fname;
        this.type = type;
        this.size = size;
        this.location = location;
        this.udate = udate;
        
    }

    @Override
    public String toString() {
        return "UpRecord{" +
                "uid=" + uid +
                ", fname='" + fname + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", location='" + location + '\'' +
                ", udate=" + udate +
                '}';
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUdate(String udate) {
        this.udate = udate;
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

    public String getUdate() {
        return udate;
    }
}
