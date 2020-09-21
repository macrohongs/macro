package com.glf.recomm.mapper;

import java.util.List;

import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.model.RoboStock;

public interface RecommDetailMapper{
	
	/**
     * 查询智投组合信息集合
     */
    public List<RecommDetail> selectList(RecommDetail recommDetail);
    

	/**
     * 查询智投组合信息集合
     */
    public List<RecommDetail> queryRecommList(String detailId);

	/**
     * 查询智投组合持仓信息集合
     */
    public List<RoboStock> queryRoboStockList(RoboStock roboStock);
    
}
