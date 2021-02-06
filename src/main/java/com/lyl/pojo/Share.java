package com.lyl.pojo;

import java.util.Date;

public class Share {
	/*private long sid;
	private long uid;
	private String fname;
	private String type;
	private long size;
	private String location;
	private Date sdate;

	public Share(long sid, long uid, String fname, String type, long size, String location, Date sdate) {
		super();
		this.sid = sid;
		this.uid = uid;
		this.fname = fname;
		this.type = type;
		this.size = size;
		this.location = location;
		this.sdate = sdate;
	}

	public Share() {
		super();
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
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

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@Override
	public String toString() {
		return "Share [sid=" + sid + ", uid=" + uid + ", fname=" + fname + ", type=" + type + ", size=" + size
				+ ", location=" + location + ", sdate=" + sdate + "]";
	}*/
	private long sid;
	private long uid;
	private String fname;
	private String type;
	private long size;
	private String location;
	private Date sdate;
	private int state;
	private int result;

	public long getSid() {
		return sid;
	}

	@Override
	public String toString() {
		return "Share{" +
				"sid=" + sid +
				", uid=" + uid +
				", fname='" + fname + '\'' +
				", type='" + type + '\'' +
				", size=" + size +
				", location='" + location + '\'' +
				", sdate=" + sdate +
				", state=" + state +
				", result=" + result +
				'}';
	}

	public Share() {
	}

	public Share(long sid, long uid, String fname, String type, long size, String location, Date sdate, int state, int result) {
		this.sid = sid;
		this.uid = uid;
		this.fname = fname;
		this.type = type;
		this.size = size;
		this.location = location;
		this.sdate = sdate;
		this.state = state;
		this.result = result;
	}

	public void setSid(long sid) {
		this.sid = sid;
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

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
