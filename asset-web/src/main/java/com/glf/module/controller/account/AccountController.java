package com.glf.module.controller.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glf.core.controller.BaseController;
import com.glf.json.JSON;
import com.glf.module.common.AccountUtil;
import com.glf.module.common.OracleTools;
import com.glf.sys.model.JdbcModel;

/**
 * 嘉兴账务处理
 * @author macro
 */
@Controller
@RequestMapping("/account/deal")
public class AccountController extends BaseController{

	private String prefix = "account/deal";

	@RequiresPermissions("account:deal:view")
    @GetMapping()
    public String detail(ModelMap mmap) throws Exception{
		mmap.addAttribute("fieldStep", AccountUtil.getCurrentTask(null));
		mmap.addAttribute("ip", AccountUtil.url);
		mmap.addAttribute("sid", AccountUtil.sid);
		mmap.addAttribute("username", AccountUtil.name);
		mmap.addAttribute("password", AccountUtil.passWord);
		mmap.addAttribute("workDate", OracleTools.queryWorkDate());
        return prefix + "/view";
    }
	
    @PostMapping("/trade")
    @ResponseBody
    public String trade(JdbcModel jdbcModel,ModelMap mmap) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("fieldStep", AccountUtil.getCurrentTask(jdbcModel));
    	System.out.println("map:" + map);
    	return JSON.marshal(map);
    }
	
}
