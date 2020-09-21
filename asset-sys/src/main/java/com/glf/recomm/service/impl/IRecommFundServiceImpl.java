package com.glf.recomm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glf.recomm.mapper.RecommFundMapper;
import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.model.RecommFund;
import com.glf.recomm.service.IRecommFundService;

@Service
public class IRecommFundServiceImpl implements IRecommFundService{

    @Autowired
    private RecommFundMapper recommFundMapper;
    
	@Override
	public List<RecommFund> selectList(RecommFund recommFund) {
		return recommFundMapper.selectList(recommFund);
	}
	
	@Override
	public List<RecommDetail> selectDetailList(RecommDetail recommDetail) {
		String detailId = recommFundMapper.selectDetailId(recommDetail.getRecommId());
		recommDetail.setDetailId(detailId + recommDetail.getRecommId());
		return recommFundMapper.selectDetailList(recommDetail);
	}

	@Override
	public RecommFund selectRecommFund(Long recommId) {
		return recommFundMapper.selectRecommFund(recommId);
	}

	@Override
	public int updateRecommFund(RecommFund recommFund) {
		return recommFundMapper.updateRecommFund(recommFund);
	}

}
