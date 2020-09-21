package com.glf.module.controller.recomm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glf.annotation.Log;
import com.glf.core.controller.BaseController;
import com.glf.core.page.TableDataInfo;
import com.glf.enums.BusinessType;
import com.glf.json.JSON;
import com.glf.module.common.PortfolioCommon;
import com.glf.recomm.model.FundTrade;
import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.model.RoboStock;
import com.glf.recomm.service.IRecommDetailService;

/**
 * 智投组合
 */
@Controller
@RequestMapping("/recomm/trade")
public class RecommTradeController extends BaseController{

	private String prefix = "recomm/trade";
	
	@Autowired
    private IRecommDetailService recommDetailService;
	
    @RequiresPermissions("recomm:trade:view")
    @GetMapping()
    public String detail(ModelMap mmap){
    	System.out.println("111:");
        return prefix + "/trade";
    }
    
    @GetMapping("/trade")
    public String add(ModelMap mmap){
    	System.out.println("222:");
        mmap.put("userName", "macro");
        return prefix + "/trade";
    }
    
    /**
     * 新增保存用户
     * @throws Exception 
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/trade")
    @ResponseBody
    public String addSave(RoboStock roboStock,ModelMap mmap) throws Exception{
    	System.out.println("trade:" + roboStock.getBizFlag());
    	System.out.println("trade:" + roboStock.getFinanceAcc());
    	System.out.println("trade:" + roboStock.getRecommId());
        Map<String,Object> map = new HashMap<>();
        List<RoboStock> stock = null;
        List<RecommDetail> detail = null;
        List<FundTrade> res = null;
        if(roboStock.getBizFlag().equals("pur")){
        	stock = recommDetailService.queryRoboStockList(roboStock);
        	detail = recommDetailService.queryRecommList(roboStock.getDetailId());
        	res = PortfolioCommon.getPurList(roboStock.getRoboPurchaseAmt(), detail, stock);
        }else if(roboStock.getBizFlag().equals("redeem")){
        	stock = recommDetailService.queryRoboStockList(roboStock);
        	res = PortfolioCommon.getRedeemList(roboStock.getRoboValidVol(), roboStock.getFundRate(), stock);
        }
        if(null != res){
            map.put("result", JSON.marshal(res));
        }else{
            map.put("result", JSON.marshal(new ArrayList<FundTrade>()));
        }
        System.out.println("result:" + map);
        return JSON.marshal(map);
    }
    
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RoboStock roboStock){
    	List<RoboStock> stock = null;
        List<RecommDetail> detail = null;
        List<FundTrade> res = null;
        if(null != roboStock && null != roboStock.getBizFlag()){
            if(roboStock.getBizFlag().equals("pur")){
            	if(null != roboStock.getRoboPurchaseAmt() 
            			&& !"".equals(roboStock.getRoboPurchaseAmt())
            			&& null != roboStock.getDetailId()){
                	stock = recommDetailService.queryRoboStockList(roboStock);
                	detail = recommDetailService.queryRecommList(roboStock.getDetailId());
                	res = PortfolioCommon.getPurList(roboStock.getRoboPurchaseAmt(), detail, stock);
            	}
            }else if(roboStock.getBizFlag().equals("redeem")){
            	if((null != roboStock.getRoboValidVol() 
            			&& !"".equals(roboStock.getRoboValidVol())) || 
            		(null != roboStock.getFundRate()
                		&& !"".equals(roboStock.getFundRate()))){
                	stock = recommDetailService.queryRoboStockList(roboStock);
                	res = PortfolioCommon.getRedeemList(roboStock.getRoboValidVol(), roboStock.getFundRate(), stock);
            	}
            }
        }
        TableDataInfo rspData = new TableDataInfo();
        if(null != res){
            rspData.setRows(res);
            rspData.setTotal(res.size());
        }
        return rspData;
    }
    
}
