package com.lyl.pojo;

import java.util.Date;

public class DelRecord {
	private long did;
	private long uid;
	private String fname;
	private String type;
	private long size;
	private String location;
	private Date ddate;

	public DelRecord(long did, long uid, String fname, String type, long size, String location, Date ddate) {
		super();
		this.did = did;
		this.uid = uid;
		this.fname = fname;
		this.type = type;
		this.size = size;
		this.location = location;
		this.ddate = ddate;
	}

	public DelRecord() {
		super();
	}

	public long getDid() {
		return did;
	}

	public void setDid(long did) {
		this.did = did;
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

	public Date getDdate() {
		return ddate;
	}

	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}

	@Override
	public String toString() {
		return "DelRecord [did=" + did + ", uid=" + uid + ", fname=" + fname + ", type=" + type + ", size=" + size
				+ ", location=" + location + ", ddate=" + ddate + "]";
	}

}
