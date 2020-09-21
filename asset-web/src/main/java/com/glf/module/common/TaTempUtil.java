package com.glf.module.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glf.core.page.TableDataInfo;
import com.glf.exception.BusinessException;
import com.glf.json.JsonUtils;
import com.glf.module.controller.interfac.TradeModel;

public class TaTempUtil {
	
	public static List<String> taList = null;
	public static Map<String,List<TradeModel>> dataList = null;
	
	public static void putTa(String taCode){
		if(null == taList){
			taList = new ArrayList<>();
		}
		taList.add(taCode);
	}
	
	public static void removeTa(String taCode){
		if(null != taList && taList.contains(taCode)){
			taList.remove(taCode);
		}
		if(null != taList && taList.size() == 0){
			taList = null;
		}
	}
	
	public static boolean containsTa(String taCode){
		if(null != taList && taList.contains(taCode)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		TaList("03");
    }
	
	public static List<TradeModel> TaList(String taCode) throws Exception{
		List<TradeModel> list = new ArrayList<>();
		String strs = "03920191112000000016    00035502019111212014681601065500010881039      0000000000000000000000000009000002201177218094010000                       156039                                      10000000                                                        00000                 0000000714                              0000000000000000                                                                  00000000000000000        1        0                                  039                                                       00                                                          00000 0000000000000000    10000                        0000000000000000000000000";
		Map<String,String> result = new HashMap<>();
		Map<String,Integer> map = TaData.getTaList("03");
		int start = 0;
		int end = 0;
		String field = "";
		for(int i = 1;i <= 105;i ++){
			for(Map.Entry<String, Integer> entry:map.entrySet()){
				end = start + entry.getValue();
				field = strs.substring(start, end);
				field = subStr(strs,entry.getValue());
				result.put("field" + entry.getKey(), field);
				start = start + Integer.valueOf(entry.getValue());
			}
			list.add(JsonUtils.getBean(JsonUtils.toJSONNoFeatures(result), TradeModel.class));
			result = new HashMap<>();
			end = 0;start = 0;
		}
		return list;
	}
	
	public static void putTaData(String taCode,List<TradeModel> list){
		if(null == dataList){
			dataList = new HashMap<>();
		}
    	putTa(taCode);
		dataList.put(taCode, list);
	}
	
	public static void removeTaData(String taCode){
		if(null != dataList && dataList.containsKey(taCode)){
			dataList.remove(taCode);
		}
		if(null != dataList && dataList.size() == 0){
			dataList = null;
		}
		removeTa(taCode);
	}
	
	public static List<TradeModel> getTaData(String taCode){
		if(null != dataList && dataList.containsKey(taCode)){
			return dataList.get(taCode);
		}
		return new ArrayList<>();
	}
	
	public static String getTaCode(String fileName){
		String taCode = fileName;
		if(!taCode.contains(".") || !taCode.contains("_")){
			throw new BusinessException("无效的TA文件：" + fileName);
		}
		try {
			taCode = taCode.substring(taCode.lastIndexOf("_") + 1, taCode.indexOf("."));
		} catch (Exception e) {
			throw new BusinessException("无效的TA文件：" + fileName);
		}
		return taCode;
	}
	
	public static Map<String,String> TaData(String taCode,String strs) throws Exception{
		Map<String,String> result = new HashMap<>();
		Map<String,Integer> map = TaData.getTaList(taCode);
		String field = "";
		for(Map.Entry<String, Integer> entry:map.entrySet()){
			field = subStr(strs,entry.getValue());
			result.put("field" + entry.getKey(), field);
			strs = strs.substring(field.length(), strs.length());
		}
		return result;
	}

	public static List<TradeModel> importFile(String taCode,InputStream is){
        BufferedReader br = null;
		List<TradeModel> list = new ArrayList<>();
        try { 
        	InputStreamReader isr = new InputStreamReader(is, "GBK");
        	br = new BufferedReader(isr);
            String line = ""; 
            int i = 0;
            int fieldSize = 0;
            int listSize = 0;
            while ((line = br.readLine()) != null) { 
            	i++;
            	if(i == 10){
            		fieldSize = Integer.valueOf(line);
            		listSize = 10 + fieldSize + 1;
            	}
            	
            	if(i > 10 && i > listSize){
            		if(line.equals("OFDCFEND")){
            			break;
            		}
        			list.add(JsonUtils.getBean(JsonUtils.toJSONNoFeatures(TaData(taCode,line)), TradeModel.class));
            	}
            	
            }
        }catch (Exception e) {
        	e.printStackTrace();
			throw new BusinessException("读取文件异常……");
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }	

	public static String subStr(String str, int subSLength) throws UnsupportedEncodingException{   
        if (str == null)    
            return "";    
        else{   
            int tempSubLength = subSLength;//截取字节数  
            String subStr = str.substring(0, str.length()<subSLength ? str.length() : subSLength);//截取的子串    
            int subStrByetsL = subStr.getBytes("GBK").length;//截取子串的字节长度   
            // 说明截取的字符串中包含有汉字    
            while (subStrByetsL > tempSubLength){    
                int subSLengthTemp = --subSLength;  
                subStr = str.substring(0, subSLengthTemp>str.length() ? str.length() : subSLengthTemp);    
                subStrByetsL = subStr.getBytes("GBK").length;  
            }    
            return subStr;   
        }  
    }  
	
	public static TableDataInfo getTaList(String taCode){
    	TableDataInfo t = new TableDataInfo();
		if(TaTempUtil.containsTa(taCode)){
        	List<TradeModel> list = TaTempUtil.getTaData(taCode);
        	t.setRows(list);
            t.setTotal(list.size());
            TaTempUtil.removeTaData(taCode);
    	}
		return t;
	}
	
}
