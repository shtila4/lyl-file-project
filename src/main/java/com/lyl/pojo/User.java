package com.lyl.pojo;

public class User {
	private long uid;
	private String password;
	private String photo;
	private long volume;
	private String identity;

	public User() {
		super();
	}

	public User(long uid, String password, String photo, long volume, String identity) {
		this.uid = uid;
		this.password = password;
		this.photo = photo;
		this.volume = volume;
		this.identity = identity;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", password=" + password + ", photo=" + photo + ", volume=" + volume + ", identity="
				+ identity + "]";
	}

}
