package com.glf.module.controller.interfac;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glf.core.controller.BaseController;
import com.glf.core.page.TableDataInfo;
import com.glf.module.common.JxInterfaceUtil;
import com.glf.module.common.ThreadUtil;

/**
 * 接口模拟
 * @author macro
 */
@Controller
@RequestMapping("/interface/trade")
public class InterfaceTradeController extends BaseController{

	private String prefix = "interface/trade";

	@RequiresPermissions("interface:trade:view")
    @GetMapping()
    public String detail(ModelMap mmap){
        return prefix + "/trade";
    }
	
	@PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TradeModel tm) throws Exception{
        TableDataInfo rspData = new TableDataInfo();
        String request = "";
        String response = "";
        String responseBak = "";
		if(StringUtils.isNotBlank(tm.getInterfaceFlag())){
	        System.out.println("########start-");
	        System.out.println("########end-");
			System.out.println("tradeModel:" + tm.getInterfaceFlag());
			request = JxInterfaceUtil.requestHeader(tm.getInterfaceFlag(),tm.getPageRows(), JxInterfaceUtil.StartRows(tm.getPageRows(), tm.getStartRows())) + 
					JxInterfaceUtil.requestBody(tm);
			response = JxInterfaceUtil.Client(tm.getIp(), tm.getPort(), request);
			if(Integer.valueOf(tm.getTask()) > 1 && Integer.valueOf(tm.getThread()) > 1){
				responseBak = ThreadUtil.task(tm,request);
			}
	        List<TradeResponseModel> list = new ArrayList<>();
	        rspData.setRows(JxInterfaceUtil.getResult(tm.getInterfaceFlag(),Integer.valueOf(tm.getPageRows()),response,tm.getFieldCnt()));
	        rspData.setTotal(list.size());
	        rspData.setRemark(JxInterfaceUtil.responseHeader(response));
	        rspData.setRequestDesc(request);
	        rspData.setResponseDesc(response + "<br/>##########<br/>" + responseBak);
		}
        return rspData;
    }
	
	
}
