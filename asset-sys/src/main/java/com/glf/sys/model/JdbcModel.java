package com.glf.sys.model;

public class JdbcModel {
	
	private String fieldStep;
	private String ip;
	private String sid;
	private String username;
	private String password;
	private String taCode;
	private String navDate;
	
	public String getNavDate() {
		return navDate;
	}
	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}
	public String getTaCode() {
		return taCode;
	}
	public void setTaCode(String taCode) {
		this.taCode = taCode;
	}
	public String getFieldStep() {
		return fieldStep;
	}
	public void setFieldStep(String fieldStep) {
		this.fieldStep = fieldStep;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
