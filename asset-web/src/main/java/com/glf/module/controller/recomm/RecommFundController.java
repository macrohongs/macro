package com.glf.module.controller.recomm;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glf.annotation.Log;
import com.glf.core.controller.BaseController;
import com.glf.core.domain.AjaxResult;
import com.glf.core.page.TableDataInfo;
import com.glf.enums.BusinessType;
import com.glf.framework.util.ShiroUtils;
import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.model.RecommFund;
import com.glf.recomm.service.IRecommFundService;
import com.glf.utils.poi.ExcelUtil;

/**
 * 智投组合
 */
@Controller
@RequestMapping("/recomm/fund")
public class RecommFundController extends BaseController{

	private String prefix = "recomm/fund";
	
	@Autowired
    private IRecommFundService recommFundService;

    @RequiresPermissions("recomm:fund:view")
    @GetMapping()
    public String operlog(){
        return prefix + "/fund";
    }
    
    @RequiresPermissions("recomm:fund:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RecommFund recommFund){
        startPage();
        List<RecommFund> list = recommFundService.selectList(recommFund);
        return getDataTable(list);
    }

    @RequiresPermissions("recomm:fund:view")
    @PostMapping("/listItem")
    @ResponseBody
    public TableDataInfo listItem(RecommDetail recommDetail){
        TableDataInfo rspData = new TableDataInfo();
        System.out.println("recommFund:" + recommDetail.getRecommId());
        List<RecommDetail> list = recommFundService.selectDetailList(recommDetail);
        if(null != list){
        	rspData.setRows(list);
            rspData.setTotal(list.size());
        }
        return rspData;
    }
    
    @Log(title = "智投组合", businessType = BusinessType.EXPORT)
    @RequiresPermissions("recomm:fund:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RecommFund recommFund){
        List<RecommFund> list = recommFundService.selectList(recommFund);
        ExcelUtil<RecommFund> util = new ExcelUtil<RecommFund>(RecommFund.class);
        return util.exportExcel(list, "智投组合");
    }
    
    /**
     * 修改智投组合
     */
    @GetMapping("/edit/{recommId}")
    public String edit(@PathVariable("recommId") Long recommId, ModelMap mmap){
        mmap.put("recommfund", recommFundService.selectRecommFund(recommId));
        return prefix + "/edit";
    }

    /**
     * 修改智投组合
     */
    @RequiresPermissions("recomm:fund:edit")
    @Log(title = "智投组合", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated RecommFund recommFund){
    	recommFund.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(recommFundService.updateRecommFund(recommFund));
    }
	
}
