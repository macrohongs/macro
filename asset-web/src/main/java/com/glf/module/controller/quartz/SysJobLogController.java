package com.glf.module.controller.quartz;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.glf.quartz.model.SysJobLog;
import com.glf.quartz.service.ISysJobLogService;
import com.glf.utils.poi.ExcelUtil;

/**
 * 调度日志操作处理
 */
@Controller
@RequestMapping("/quartz/jobLog")
public class SysJobLogController extends BaseController{
    private String prefix = "quartz/job";

    @Autowired
    private ISysJobLogService jobLogService;

    @RequiresPermissions("quartz:job:view")
    @GetMapping()
    public String jobLog(){
        return prefix + "/jobLog";
    }

    @RequiresPermissions("quartz:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJobLog jobLog){
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        return getDataTable(list);
    }

    @Log(title = "调度日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("quartz:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJobLog jobLog){
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
        return util.exportExcel(list, "调度日志");
    }

    @Log(title = "调度日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("quartz:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        return toAjax(jobLogService.deleteJobLogByIds(ids));
    }

    @RequiresPermissions("quartz:job:detail")
    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap){
        mmap.put("name", "jobLog");
        mmap.put("jobLog", jobLogService.selectJobLogById(jobLogId));
        return prefix + "/detail";
    }

    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("quartz:job:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean(){
        jobLogService.cleanJobLog();
        return success();
    }
}

