package com.lyl.pojo;

import java.util.Date;

public class Collection {
	private long cid;
	private long uid;
	private String fname;
	private String type;
	private long size;
	private String location;
	private Date cdate;

	@Override
	public String toString() {
		return "CollectRecord [cid=" + cid + ", uid=" + uid + ", fname=" + fname + ", type=" + type + ", size=" + size
				+ ", location=" + location + ", cdate=" + cdate + "]";
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public long getUid() {
		return uid;
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

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Collection() {
		super();
	}

	public Collection(long cid, long uid, String fname, String type, long size, String location, Date cdate) {
		super();
		this.cid = cid;
		this.uid = uid;
		this.fname = fname;
		this.type = type;
		this.size = size;
		this.location = location;
		this.cdate = cdate;
	}

}
