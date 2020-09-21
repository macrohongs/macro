package com.glf.module.controller.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.glf.core.controller.BaseController;
import com.glf.core.domain.AjaxResult;
import com.glf.core.page.TableDataInfo;
import com.glf.module.common.TaTempUtil;
import com.glf.quartz.model.SysJob;

/**
 * ta模拟
 * @author macro
 */
@Controller
@RequestMapping("/account/ta")
public class TaTempController extends BaseController{
	
    private String prefix = "account/ta";
    
    
    @RequiresPermissions("account:ta:view")
    @GetMapping()
    public String job(){
        return prefix + "/view";
    }
    
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(@RequestParam(value="files")List<MultipartFile> list, boolean updateSupport) throws Exception{
    	String taCode = "";
    	Map<String,Object> map = new HashMap<>();
    	for (MultipartFile file : list) {
    		System.out.println("file:" + file.getName() + "-" + file.getOriginalFilename() + "-" + file.getSize());
    		taCode = TaTempUtil.getTaCode(file.getOriginalFilename());
        	map.put(taCode, taCode);
        	TaTempUtil.putTaData(taCode, TaTempUtil.importFile(taCode, file.getInputStream()));
    	}
        return AjaxResult.success("smy",map);
    }
    
    @GetMapping("/edit")
    public String edit(ModelMap mmap){
    	System.out.println("1111");
        return prefix + "/edit";
    }
    
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TaFundModel ta){
    	System.out.println("2222");
    	TableDataInfo t = new TableDataInfo();
    	List<TaFundModel> list = new ArrayList<>();
    	TaFundModel tf = new TaFundModel();
    	for(int i=0;i<15;i++){
        	tf = new TaFundModel();
        	tf.setFundId("00100" + i);
        	tf.setNav("1." + i);
        	list.add(tf);
    	}
    	t.setRows(list);
    	t.setTotal(list.size());
        return t;
    }
    

    @RequiresPermissions("account:ta:view")
    @PostMapping("/list01")
    @ResponseBody
    public TableDataInfo list01(SysJob job) throws Exception{
        return TaTempUtil.getTaList("01");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list02")
    @ResponseBody
    public TableDataInfo list02(SysJob job) throws Exception{
        return TaTempUtil.getTaList("02");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list03")
    @ResponseBody
    public TableDataInfo list03(SysJob job) throws Exception{
        return TaTempUtil.getTaList("03");
    }

    @RequiresPermissions("account:ta:view")
    @PostMapping("/list04")
    @ResponseBody
    public TableDataInfo list04(SysJob job) throws Exception{
        return TaTempUtil.getTaList("04");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list05")
    @ResponseBody
    public TableDataInfo list05(SysJob job) throws Exception{
        return TaTempUtil.getTaList("05");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list06")
    @ResponseBody
    public TableDataInfo list06(SysJob job) throws Exception{
        return TaTempUtil.getTaList("06");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list07")
    @ResponseBody
    public TableDataInfo list07(SysJob job) throws Exception{
        return TaTempUtil.getTaList("07");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list08")
    @ResponseBody
    public TableDataInfo list08(SysJob job) throws Exception{
        return TaTempUtil.getTaList("08");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list09")
    @ResponseBody
    public TableDataInfo list09(SysJob job) throws Exception{
        return TaTempUtil.getTaList("09");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list10")
    @ResponseBody
    public TableDataInfo list10(SysJob job) throws Exception{
        return TaTempUtil.getTaList("10");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list11")
    @ResponseBody
    public TableDataInfo list11(SysJob job) throws Exception{
        return TaTempUtil.getTaList("11");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list12")
    @ResponseBody
    public TableDataInfo list12(SysJob job) throws Exception{
        return TaTempUtil.getTaList("12");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list21")
    @ResponseBody
    public TableDataInfo list21(SysJob job) throws Exception{
        return TaTempUtil.getTaList("21");
    }
    
    @RequiresPermissions("account:ta:view")
    @PostMapping("/list24")
    @ResponseBody
    public TableDataInfo list24(SysJob job) throws Exception{
        return TaTempUtil.getTaList("24");
    }

    @RequiresPermissions("account:ta:view")
    @PostMapping("/list25")
    @ResponseBody
    public TableDataInfo list25(SysJob job) throws Exception{
        return TaTempUtil.getTaList("25");
    }

    @RequiresPermissions("account:ta:view")
    @PostMapping("/list26")
    @ResponseBody
    public TableDataInfo list26(SysJob job) throws Exception{
        return TaTempUtil.getTaList("26");
    }

}
