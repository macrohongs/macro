package com.glf.module.controller.recomm;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glf.annotation.Log;
import com.glf.core.controller.BaseController;
import com.glf.core.domain.AjaxResult;
import com.glf.core.page.TableDataInfo;
import com.glf.enums.BusinessType;
import com.glf.recomm.model.RecommDetail;
import com.glf.recomm.service.IRecommDetailService;
import com.glf.utils.poi.ExcelUtil;

/**
 * 智投组合
 */
@Controller
@RequestMapping("/recomm/detail")
public class RecommDetailController extends BaseController{

	private String prefix = "recomm/detail";
	
	@Autowired
    private IRecommDetailService recommDetailService;

    @RequiresPermissions("recomm:detail:view")
    @GetMapping()
    public String detail(){
        return prefix + "/detail";
    }
    
    @RequiresPermissions("recomm:detail:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RecommDetail recommDetail){
        startPage();
        List<RecommDetail> list = recommDetailService.selectList(recommDetail);
        return getDataTable(list);
    }
    
    @Log(title = "智投组合", businessType = BusinessType.EXPORT)
    @RequiresPermissions("recomm:detail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RecommDetail recommDetail){
        List<RecommDetail> list = recommDetailService.selectList(recommDetail);
        ExcelUtil<RecommDetail> util = new ExcelUtil<RecommDetail>(RecommDetail.class);
        return util.exportExcel(list, "智投组合");
    }
    
}
