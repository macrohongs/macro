package com.glf.module.common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.glf.sys.model.JdbcModel;
import com.glf.utils.StringUtils;

public class AccountUtil {
	
	public static Map<String,Object> taskMap;

	public static String url;
	public static String sid;
	public static String name;
	public static String passWord;
	
	public static void setJdbc(JdbcModel jdbcModel){
		if(null != jdbcModel && StringUtils.isNotBlank(jdbcModel.getIp())){
			url = jdbcModel.getIp();
			sid = jdbcModel.getSid();
			name = jdbcModel.getUsername();
			passWord = jdbcModel.getPassword();
		}else{
			if(!StringUtils.isNotBlank(url)){
				url = "192.168.1.126";
				sid = "ksglf";
				name = "JXFUND_TEST";
				passWord = "JXFUND_TEST";
			}
		}
	}
	
	/**
	 * 2：基金信息更新 fundinfo_imp_status = 2
	 * 3：定期定额扣款确认 dqde_imp_status = 2
	 * 4/5：划付基金公司登记簿[上划申购款] payfund_purchase_status = 1
	 * 6：收益计算 initIncome = 1
	 * 7：日结 work_status = 5
	 * 8：总部扎帐  CMN_BRANCH.DAY_END_ = '3'
	 * 9：启动账务处理 account_status = 1,APP_CALENDAR.ACC_STATUS_ = 1
	 * 10：导入T-1日交易确认数据 confirm_imp_status = 1 APP_TAPARAMS.CONFIRM_TRAD_STATUS_ = 2 ?
	 * 11：账户业务数据处理 confirm_imp_status = 2  APP_TAPARAMS.CONFIRM_ACCT_STATUS_ = 1 ?
	 * 12：交易业务数据处理 confirm_imp_status = 5  APP_TAPARAMS.CONFIRM_ACCT_STATUS_ = 2
	 * 13：T日申请数据准备 data_exp_status = 1 APP_TAPARAMS.REQUEST_DATA_STATUS_ = 1
	 * 14：导出T日申请数据 data_exp_status = 2 APP_TAPARAMS.REQUEST_DATA_STATUS_ = 2
	 * 15：定投申购扣款数据准备 dqde_exp_status = 1
	 * 16：定投申购扣款数据导出 dqde_exp_status = 2
	 * 17：划付投资人无效认申购退款 paycust_return_status = 1
	 * 18：划付投资人赎回、分红、认购退款 paycust_redeem_status = 1
	 * 19：划付投资人回退款确认 paycust_cfm_status = 2
	 * 20：T日日切
	 * @param map
	 * @throws SQLException 
	 */
	public static void putTaskMap(JdbcModel jdbcModel) throws SQLException{
		if(null == taskMap){
			taskMap = new HashMap<>();
		}
		setJdbc(jdbcModel);
		taskMap.putAll(OracleTools.queryAppParams());
		taskMap.put("20", "0");
	}

	public static String getCurrentTask(JdbcModel jdbcModel) throws SQLException{
		putTaskMap(jdbcModel);
		int i = 2;
		while(i <= 19){
			if(!getTaskIndex(String.valueOf(i),
					taskMap.get(String.valueOf(i)).toString())){
				i--;
				return String.valueOf(i);
			}
			i++;
		}
		return "19";
	}
	
	private static boolean getTaskIndex(String taskId,String task){
		boolean b = false;
		System.out.println("taskId:" + taskId + "-" + task);
		switch(taskId){
			case "4":
			case "5":
			case "6":
			case "9":
			case "10":
			case "13":
			case "15":
			case "17":
			case "18":
				return (Integer.valueOf(task) >= 1)?true:false;
			case "2":
			case "3":
			case "11":
			case "14":
			case "16":
			case "19":
				return (Integer.valueOf(task) >= 2)?true:false;
			case "8":
				return (Integer.valueOf(task) >= 3)?true:false;
			case "7":
			case "12":
				return (Integer.valueOf(task) >= 5)?true:false;
			case "20":
				return true;
		}
		return b;
	}
	
	public void setTask(String taskId,String oldTaskId){
		for(int i = Integer.valueOf(oldTaskId) + 1; i <= Integer.valueOf(taskId); i++){
			switch(i){
				case 2:
					getSql("2","fundinfo_imp_status");
					break;
				case 3:
					getSql("2","dqde_imp_status");
					break;
				case 4:
				case 5:
					getSql("1","payfund_purchase_status");
					break;
				case 6:
					getSql("1","initIncome");
					break;
				case 7:
					getSql("5","work_status"); 
					break;
				case 9:
					getSql("1","account_status"); 
					break;
				case 10:
					getSql("1","confirm_imp_status");
					break;
				case 11:
					getSql("2","confirm_imp_status"); 
					break;
				case 12:
					getSql("5","confirm_imp_status"); 
					break;
				case 13:
					getSql("1","data_exp_status"); 
					break;
				case 14:
					getSql("2","data_exp_status");
					break;
				case 15:
					getSql("1","dqde_exp_status"); 
					break;
				case 16:
					getSql("2","dqde_exp_status"); 
					break;
				case 17:
					getSql("1","paycust_return_status"); 
					break;
				case 18:
					getSql("1","paycust_redeem_status"); 
					break;
				case 19:
					getSql("2","paycust_cfm_status"); 
					break;
			}
		}
	}
	
	private String getSql(String keyValue,String keyName){
		String taskIdSql = "UPDATE APP_PARAMS SET KEY_VALUE_='"  + keyValue + "' WHERE KEY_NAME_='"  + keyName + "'"; 
		return taskIdSql;
	}
	
}
