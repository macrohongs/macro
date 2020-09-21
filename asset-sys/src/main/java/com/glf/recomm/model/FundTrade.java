package com.glf.recomm.model;

public class FundTrade {
	
	private String fundCode;
	private String bizFlag;//1:申购  2：赎回  3.转出  4.转入
	private String appAmt;
	private String appVol;
	private String fundNav;
	private String fundRate;
	private String fundRateBak;
	
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getBizFlag() {
		return bizFlag;
	}
	public void setBizFlag(String bizFlag) {
		this.bizFlag = bizFlag;
	}
	public String getAppAmt() {
		return appAmt;
	}
	public void setAppAmt(String appAmt) {
		this.appAmt = appAmt;
	}
	public String getAppVol() {
		return appVol;
	}
	public void setAppVol(String appVol) {
		this.appVol = appVol;
	}
	public String getFundNav() {
		return fundNav;
	}
	public void setFundNav(String fundNav) {
		this.fundNav = fundNav;
	}
	public String getFundRate() {
		return fundRate;
	}
	public void setFundRate(String fundRate) {
		this.fundRate = fundRate;
	}
	public String getFundRateBak() {
		return fundRateBak;
	}
	public void setFundRateBak(String fundRateBak) {
		this.fundRateBak = fundRateBak;
	}
	
}
