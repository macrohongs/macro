package com.glf.recomm.service;

import java.util.List;

import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.model.RoboStock;

/**
 * 智投组合 服务层
 */
public interface IRecommDetailService {

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
