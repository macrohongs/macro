package com.glf.module.controller.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glf.core.controller.BaseController;
import com.glf.core.page.TableDataInfo;
import com.glf.json.JSON;
import com.glf.json.JsonUtils;
import com.glf.module.common.OracleTools;
import com.glf.module.common.TaData;
import com.glf.module.common.TaTempUtil;
import com.glf.module.controller.interfac.TradeModel;
import com.glf.quartz.model.SysJob;
import com.glf.sys.model.JdbcModel;

/**
 * 嘉兴行情文件模拟
 * @author macro
 */
@Controller
@RequestMapping("/account/fund")
public class TaFundController extends BaseController{

	private String prefix = "account/fund";
	
	@RequiresPermissions("account:fund:view")
    @GetMapping()
    public String detail(ModelMap mmap) throws Exception{
		TaData.setJdbc(null);
		mmap.addAttribute("ip", TaData.url);
		mmap.addAttribute("sid", TaData.sid);
		mmap.addAttribute("username", TaData.name);
		mmap.addAttribute("password", TaData.passWord);
		mmap.addAttribute("navDate", OracleTools.queryPreDate());
        return prefix + "/view";
    }
	
    @PostMapping("/trade")
    @ResponseBody
    public String trade(JdbcModel jdbcModel,ModelMap mmap) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("taFund", "0");
    	System.out.println("map:" + map);
    	return JSON.marshal(map);
    }
    
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JdbcModel jdbcModel) throws Exception{
    	System.out.println("fund-list:");
    	TableDataInfo t = new TableDataInfo();
    	List<TradeModel> list = OracleTools.queryTaList(jdbcModel.getTaCode(), jdbcModel.getNavDate());
    	t.setRows(list);
        t.setTotal(list.size());
        return t;
    }
    
    @PostMapping("/fund_edit")
    @ResponseBody
    public String fundEdit(TradeModel trade,ModelMap mmap) throws Exception{
        Map<String,Object> map = JsonUtils.objectToMap(trade);
        String field = map.get("field1").toString();
    	TaData.putTaCode(map.get("field3").toString(), field, map.get(field).toString());
    	System.out.println("list:" + JsonUtils.toJSONString(TaData.taCodeList));
    	return JSON.marshal(map);
    }


}
