package com.glf.recomm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glf.recomm.mapper.RecommDetailMapper;
import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.model.RoboStock;
import com.glf.recomm.service.IRecommDetailService;

@Service
public class IRecommDetailServiceImpl implements IRecommDetailService{

    @Autowired
    private RecommDetailMapper recommDetailMapper;
    
	@Override
	public List<RecommDetail> selectList(RecommDetail recommDetail) {
		return recommDetailMapper.selectList(recommDetail);
	}
	
	@Override
	public List<RecommDetail> queryRecommList(String detailId) {
		return recommDetailMapper.queryRecommList(detailId);
	}
	
	@Override
	public List<RoboStock> queryRoboStockList(RoboStock roboStock) {
		return recommDetailMapper.queryRoboStockList(roboStock);
	}
	
}
