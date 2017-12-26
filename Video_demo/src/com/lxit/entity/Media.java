package com.lxit.entity;

public class Media {
	private int id;
	private String title;
	private String src;
	private String picture;
	private String descript;
	private String uptime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public Media(int id, String title, String src, String picture, String descript, String uptime) {
		super();
		this.id = id;
		this.title = title;
		this.src = src;
		this.picture = picture;
		this.descript = descript;
		this.uptime = uptime;
	}
	public Media() {
	}
	@Override
	public String toString() {
		return "Media [id=" + id + ", title=" + title + ", src=" + src + ", picture=" + picture + ", descript="
				+ descript + ", uptime=" + uptime + "]";
	}
	
	
	
}
