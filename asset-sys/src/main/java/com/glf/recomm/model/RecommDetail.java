package com.glf.recomm.model;

import com.glf.annotation.Excel;
import com.glf.annotation.Excel.ColumnType;
import com.glf.core.domain.BaseEntity;

public class RecommDetail extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Excel(name = "组合编号", cellType = ColumnType.NUMERIC)
    private Long recommId;

    @Excel(name = "组合编号")
	private String detailId;
    
    @Excel(name = "日期")
	private String tradeDate;

    @Excel(name = "产品编号")
	private String fundCode;

    @Excel(name = "产品比例")
	private String fundRate;
    
	private String fundNav;
	private String bizFlag;
	
	public String getFundNav() {
		return fundNav;
	}
	public void setFundNav(String fundNav) {
		this.fundNav = fundNav;
	}
	public String getBizFlag() {
		return bizFlag;
	}
	public void setBizFlag(String bizFlag) {
		this.bizFlag = bizFlag;
	}
	public Long getRecommId() {
		return recommId;
	}
	public void setRecommId(Long recommId) {
		this.recommId = recommId;
	}

	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundRate() {
		return fundRate;
	}
	public void setFundRate(String fundRate) {
		this.fundRate = fundRate;
	}
	
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	
}
