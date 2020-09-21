package com.glf.module.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.glf.module.controller.interfac.TradeModel;

public class OracleTools {

    public static Map<String,Object> queryAppParams() throws SQLException{
    	Map<String,Object> map = new HashMap<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
    	try {
			Class.forName("oracle.jdbc.OracleDriver");
			String urls = "jdbc:oracle:thin:@" + AccountUtil.url + ":1521:" + AccountUtil.sid;
			conn = DriverManager.getConnection(urls, AccountUtil.name, AccountUtil.passWord);
			st = conn.createStatement();
			rs = st.executeQuery(buildSql());
			String key = "";
				while(rs.next()){
					key = rs.getString(1);
					switch(key){
					case "account_status":
						map.put("9", rs.getString(2));
						break;
					case "confirm_imp_status":
						map.put("10", rs.getString(2));
						map.put("11", rs.getString(2));
						map.put("12", rs.getString(2));
						break;
					case "data_exp_status":
						map.put("13", rs.getString(2));
						map.put("14", rs.getString(2));
						break;
					case "dqde_exp_status":
						map.put("15", rs.getString(2));
						map.put("16", rs.getString(2));
						break;
					case "dqde_imp_status":
						map.put("3", rs.getString(2));
						break;
					case "initIncome":
						map.put("6", rs.getString(2));
						break;
					case "payfund_purchase_status":
						map.put("4", rs.getString(2));
						map.put("5", rs.getString(2));
						break;
					case "work_status":
						map.put("7", rs.getString(2));
						break;
					case "fundinfo_imp_status":
						map.put("2", rs.getString(2));
						break;
					case "paycust_return_status":
						map.put("17", rs.getString(2));
						break;
					case "paycust_redeem_status":
						map.put("18", rs.getString(2));
						break;
					case "paycust_cfm_status":
						map.put("19", rs.getString(2));
						break;
				}
			}
			map.put("8", "3");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != rs){
				rs.close();
			}
			if(null != st){
				st.close();
			}
			if(null != conn){
				conn.close();
			}
		}
    	return map;
    }
    
    private static String buildSql(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("SELECT KEY_NAME_,KEY_VALUE_ FROM APP_PARAMS WHERE KEY_NAME_ IN ")
    	  .append(" ('fundinfo_imp_status','dqde_imp_status','payfund_purchase_status','initIncome','work_status',")
    	  .append("'paycust_cfm_status','paycust_redeem_status','paycust_return_status','account_status','confirm_imp_status','data_exp_status','dqde_exp_status')");
    	return sb.toString();
    }
    

    public static String queryWorkDate() throws SQLException{
    	String workDate = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
    	try {
			Class.forName("oracle.jdbc.OracleDriver");
			String urls = "jdbc:oracle:thin:@" + AccountUtil.url + ":1521:" + AccountUtil.sid;
			conn = DriverManager.getConnection(urls, AccountUtil.name, AccountUtil.passWord);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT KEY_VALUE_ FROM APP_PARAMS WHERE KEY_NAME_='work_date'");
			while(rs.next()){
				workDate = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != rs){
				rs.close();
			}
			if(null != st){
				st.close();
			}
			if(null != conn){
				conn.close();
			}
		}
    	return workDate;
    }
    
    public static String queryPreDate() throws SQLException{
    	String workDate = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
    	try {
			Class.forName("oracle.jdbc.OracleDriver");
			String urls = "jdbc:oracle:thin:@" + TaData.url + ":1521:" + TaData.sid;
			conn = DriverManager.getConnection(urls, TaData.name, TaData.passWord);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT PRI_DATE_ FROM APP_CALENDAR WHERE WORK_DATE_ = (SELECT KEY_VALUE_ FROM APP_PARAMS WHERE KEY_NAME_='work_date')");
			while(rs.next()){
				workDate = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != rs){
				rs.close();
			}
			if(null != st){
				st.close();
			}
			if(null != conn){
				conn.close();
			}
		}
    	return workDate;
    }
    
    public static Map<String,Integer> queryTaTable(String taCode) throws SQLException{
    	Map<String,Integer> map = new LinkedHashMap<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
    	try {
    		AccountUtil.setJdbc(null);
			Class.forName("oracle.jdbc.OracleDriver");
			String urls = "jdbc:oracle:thin:@" + AccountUtil.url + ":1521:" + AccountUtil.sid;
			conn = DriverManager.getConnection(urls, AccountUtil.name, AccountUtil.passWord);
			st = conn.createStatement();
			String sql = "SELECT DD.ORDER_,AA.LENGTH_ FROM DICT_TABCOLUMN AA,DICT_TABMAP DD ";
			sql += "  WHERE AA.INTF_TYPE_='N90' AND DD.INTF_TYPE_='N90' AND AA.COL_ID_=DD.COL_ID_ "; 
			sql += "  AND AA.INTF_TYPE_=DD.INTF_TYPE_ AND DD.TAB_ID_='" + taCode + "' ORDER BY DD.ORDER_"; 
			rs = st.executeQuery(sql);
			while(rs.next()){
				map.put(rs.getString(1), Integer.valueOf(rs.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != rs){
				rs.close();
			}
			if(null != st){
				st.close();
			}
			if(null != conn){
				conn.close();
			}
		}
    	return map;
    }
    
    public static List<TradeModel> queryTaList(String taCode,String navDate) throws SQLException{
    	List<TradeModel> list = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		TradeModel t = null;
    	try {
    		AccountUtil.setJdbc(null);
			Class.forName("oracle.jdbc.OracleDriver");
			String urls = "jdbc:oracle:thin:@" + TaData.url + ":1521:" + TaData.sid;
			conn = DriverManager.getConnection(urls, TaData.name, TaData.passWord);
			st = conn.createStatement();
			String sql = "SELECT FUND_NAME_,fund_code_,(REDEEM_NAV_ + (select round(dbms_random.value(-1,1),4) from dual))*10000 as NAV,";
			sql += "E_APPSUBSAMT_ as InstAppSubsAmnt,E_MINSUBSAMT_ as MinAmountByInst,P_MINACCOUNTBAL_ as MinAccountBalance,"; 
			sql += "IPO_STARTDATE_ as IPOStartDate,IPO_ENDDATE_ as IPOEndDate,P_APPSUBSAMT_ as IndiAppSubsAmount,";
			sql += "DIVEND_DATE_ as DividentDate,DIVREG_DATE_ as RegistrationDate,P_MINBIDSAMT_ as MinBidsAmountByIndi,";
			sql += "E_MINBIDSAMT_ as MinBidsAmountByInst,P_MINAPPBIDSAMT_ as MinAppBidsAmountByIndi,E_MINAPPBIDSAMT_ as MinAppBidsAmountByInst,";
			sql += "P_MINREDEMVOL_ as MinRedemptionVol,P_MINCONVERTVOL_ as MinInterconvertVol,P_MAXSUBSAMT_ as IndiMaxPurchase,";
			sql += "E_MAXSUBSAMT_ as InstMaxPurchase  FROM CMN_FUND WHERE TA_CODE_='" + taCode  + "'";
			rs = st.executeQuery(sql);
			while(rs.next()){
				t = new TradeModel();
				t.setField1(rs.getString(1));t.setField2("0000000000000000");t.setField3(rs.getString(2));
				t.setField4("0");t.setField5(rs.getString(3));t.setField6(navDate);
				t.setField7("0");t.setField8(navDate);t.setField9("0");
				t.setField10("0");t.setField11("0");t.setField12("0000000000000000");
				t.setField13("156");t.setField14("0");t.setField15("1");
				t.setField16(rs.getString(4));t.setField17(rs.getString(4));t.setField18(rs.getString(5));
				t.setField19(rs.getString(5));t.setField20("004");t.setField21("0000000000000000");
				t.setField22("        ");t.setField23("0009999999999900");t.setField24(rs.getString(6));
				t.setField25(rs.getString(7));t.setField26(rs.getString(8));t.setField27("04");
				t.setField28("0000000000000100");t.setField29(rs.getString(9));t.setField30("0000000000000100");
				t.setField31("0000000000000100");t.setField32("04");t.setField33("04");
				t.setField34(rs.getString(3));t.setField35("0010000");t.setField36(rs.getString(10));
				t.setField37(rs.getString(11));t.setField38("        ");t.setField39("0009999999999900");
				t.setField40("0009999999999900");t.setField41("0009999999999900");t.setField42("0009999999999900");
				t.setField43("0000000000000000");t.setField44("0000000000000000");t.setField45("0000000000000000");
				t.setField46("0000000000000000");t.setField47(rs.getString(12));t.setField48(rs.getString(13));
				t.setField49(rs.getString(14));t.setField50(rs.getString(15));t.setField51(rs.getString(16));
				t.setField52(rs.getString(17));t.setField53("1");t.setField54("1");
				t.setField55("0");t.setField56("1");t.setField57("        ");
				t.setField58("0000000");t.setField59("00000000");t.setField60("00000000");
				t.setField61("0");t.setField62("00000000");t.setField63("0");
				t.setField64("0000000");t.setField65("00000000");t.setField66("0");
				t.setField67(rs.getString(18));t.setField68(rs.getString(19));t.setField69("0009999999999900");
				t.setField70("0099999999999900");t.setField71("0999999999999900");t.setField72("0999999999999900");
				t.setField73("0099999999999900");t.setField74("0099999999999900");t.setField75("0");
				t.setField76("0000000000000000");t.setField77("0");t.setField78(" ");
				t.setField79(" ");t.setField80(" ");t.setField81("基金管理有限公司");
				t.setField82("4008850099");t.setField83("www.baidu.com");
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != rs){
				rs.close();
			}
			if(null != st){
				st.close();
			}
			if(null != conn){
				conn.close();
			}
		}
    	return list;
    }

}
