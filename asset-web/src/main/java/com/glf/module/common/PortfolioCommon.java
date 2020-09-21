package com.glf.module.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.glf.exception.BusinessException;
import com.glf.recomm.model.FundTrade;
import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.model.RoboStock;

public class PortfolioCommon {

	public static List<FundTrade> getPurList(String amount,List<RecommDetail> rlist,
			List<RoboStock> slist) throws BusinessException{
		List<FundTrade> list = new ArrayList<>();
		FundTrade trade = null;
        String amountTemp = amount;
        String amt = "0";
        String rate = "0";
        int cnt = 0;
        String marketValue = "0";
        Map<String,Object> mv = null;
        String amountSum = "0";
        if(null != slist){
        	mv = new HashMap<>();
    		for(RoboStock s : slist){
    			marketValue = add(marketValue,s.getMarketValue());
    			mv.put(s.getFundCode(), s.getMarketValue());
    		}
            amountSum = add(marketValue,amount);
        }
        String amtTemp = "0";
        
		for(RecommDetail r : rlist){
			cnt ++;
			trade = new FundTrade();
			if(null != slist){
				rate = div(r.getFundRate(),"100",2);
				if(cnt == rlist.size()){
			        trade.setAppAmt(amountTemp);
				}else{
					amtTemp = mul(amountSum,rate,2);
					if(compareTo(amtTemp,mv.get(r.getFundCode()).toString())){
						amt = sub(amtTemp,mv.get(r.getFundCode()).toString());
					}else{
						amt = "0";
					}
			        trade.setAppAmt(amt);
			        amountTemp = sub(amountTemp,amt);
				}
			}else{
				if(cnt == rlist.size()){
			        trade.setAppAmt(amountTemp);
				}else{
					amt = mul(amount,rate,2);
			        trade.setAppAmt(amt);
			        amountTemp = sub(amountTemp,amt);
				}
			}
	        trade.setAppVol("0");
	        trade.setBizFlag("申购");//1
	        trade.setFundCode(r.getFundCode());
	        trade.setFundRate(rate);
	        trade.setFundRateBak(r.getFundRate());
	        trade.setFundNav(r.getFundNav());
			list.add(trade);
		}
		return list;
	}
	
	/**
	 * 组合赎回
	 * @param type 0:按比例赎回 1：按份额赎回
	 * @param rlist
	 * @throws RoboException
	 */
	public static List<FundTrade> getRedeemList(String vol,String rate,List<RoboStock> rlist) throws BusinessException{
		List<FundTrade> list = new ArrayList<>();
		FundTrade trade = null;
		String validVolSum = "0";
		String validVol = "0";
		for(RoboStock s : rlist){
			validVolSum = add(validVolSum,s.getRoboValidVol());
		}
		if(!StringUtils.isNotBlank(rate)){
			if(!compareTo(validVolSum,vol)){
				throw new BusinessException("赎回份额超过可用份额");
			}
			rate = div(vol,validVolSum,4);
		}
		if(!StringUtils.isNotBlank(vol)){
			rate = div(rate,"100",4);
			vol = mul(validVolSum,rate,2);
		}
        int cnt = 0;
		for(RoboStock s : rlist){
			cnt ++;
			trade = new FundTrade();
			if(cnt == rlist.size()){
		        trade.setAppVol(vol);
			}else{
				validVol = mul(vol,rate,2);
				vol = sub(vol,validVol);
		        trade.setAppVol(validVol);
			}
			trade.setAppAmt("0");
	        trade.setBizFlag("赎回");//2
	        trade.setFundCode(s.getFundCode());
	        trade.setFundRate(rate);
	        trade.setFundNav(s.getFundNav());
	        list.add(trade);
		}
		return list;
	}
	
	private static boolean compareTo(String v1, String v2) throws BusinessException{
		if(!StringUtils.isNotBlank(v1) || !StringUtils.isNotBlank(v2)){
			throw new BusinessException("add参数错误");
		}
		if(new BigDecimal(v1).compareTo(new BigDecimal(v2)) >= 0){
			return true;
		}else{
			return false;
		}
	}
	
	private static String round(String v, int decimal){
		return new BigDecimal(v).setScale(decimal, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	private static String add(String v1, String v2) throws BusinessException{
		if(!StringUtils.isNotBlank(v1) || !StringUtils.isNotBlank(v2)){
			throw new BusinessException("add参数错误");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).toString();
	}
	
	private static String sub(String v1, String v2) throws BusinessException{
		if(!StringUtils.isNotBlank(v1) || !StringUtils.isNotBlank(v2)){
			throw new BusinessException("sub参数错误");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).toString();
	}
	
	private static String mul(String v1, String v2, int decimal) throws BusinessException{
		if(!StringUtils.isNotBlank(v1) || !StringUtils.isNotBlank(v2)){
			throw new BusinessException("mul参数错误");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		String b = b1.multiply(b2).toString();
		return round(b,decimal);
	}
	
	private static String div(String v1, String v2, int scale) throws BusinessException{
		if(!StringUtils.isNotBlank(v1) || !StringUtils.isNotBlank(v2)){
			throw new BusinessException("div参数错误");
		}
		if (scale < 0) {
			throw new BusinessException("div scale参数错误");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return (b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP)).toString();
	}
	
}
