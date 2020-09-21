package com.glf.recomm.model;

import com.glf.annotation.Excel;
import com.glf.annotation.Excel.ColumnType;
import com.glf.core.domain.BaseEntity;

/**
 * 智投组合
 * @author macro
 */
public class RecommFund extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
    @Excel(name = "组合编号", cellType = ColumnType.NUMERIC)
    private Long recommId;

    @Excel(name = "组合名称")
    private String recommName;

    @Excel(name = "风险等级")
    private String risk;

	public Long getRecommId() {
		return recommId;
	}
	public void setRecommId(Long recommId) {
		this.recommId = recommId;
	}

	public String getRecommName() {
		return recommName;
	}
	public void setRecommName(String recommName) {
		this.recommName = recommName;
	}

	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}

}
