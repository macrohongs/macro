package com.glf.recomm.service;

import java.util.List;

import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.model.RecommFund;

/**
 * 智投组合 服务层
 */
public interface IRecommFundService {

	/**
     * 查询智投组合信息集合
     */
    public List<RecommFund> selectList(RecommFund recommFund);
    
	/**
     * 查询智投组合信息集合
     */
    public List<RecommDetail> selectDetailList(RecommDetail recommDetail);
    
    /**
     * 通过ID查询智投组合
     */
    public RecommFund selectRecommFund(Long recommId);
    
    /**
     * 修改智投组合信息
     */
    public int updateRecommFund(RecommFund recommFund);
    
}
