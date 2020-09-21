package com.glf.module.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.glf.exception.BusinessException;
import com.glf.json.JsonUtils;
import com.glf.module.controller.interfac.TradeModel;
import com.glf.module.controller.interfac.TradeResponseModel;
import com.glf.utils.DateUtils;

public class JxInterfaceUtil {
    private static final Logger log = LoggerFactory.getLogger(JxInterfaceUtil.class);
    private static String SP = "|";
	
	public static String Client(String ip,String port,String reqStr){
		StringBuilder response = new StringBuilder();
		try{
			log.info("request:ip[" + ip + "]-port:[" + port + "]-request:[" + reqStr + "]");
			Socket socket = new Socket(ip,Integer.valueOf(port));
			OutputStreamWriter out=new OutputStreamWriter(socket.getOutputStream(),"GBK");
			
			int len = reqStr.getBytes("GBK").length;
			reqStr = StringUtils.leftPad(""+len, 6,"0")+reqStr;
			out.write(reqStr);
			out.flush();
			InputStream stream=socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(stream, "GBK");//读取  
			BufferedReader bufr = new BufferedReader(isr);//缓冲  
			String line = null;  
	        while((line = bufr.readLine())!=null){  
	            response.append(line);
	        }  
	        isr.close();
			stream.close();
			socket.close();
			log.info("response:[" + response.toString() + "]");
		} catch (Exception e) {
			log.info("response-error:[" + e + "]");
			throw new BusinessException(e.getMessage());
		}
		return response.toString();
	}
	
	/**
	 *  setTrscode	CHAR(6)	填写基金后台交易码
	 *	Release	CHAR(4)	版本号，填写“1.00”
	 *	setBranCode	CHAR(10)	交易机构号
	 *	setTell	CHAR(8)	交易柜员号
	 *	setSeqno	NUMBER(10)	前置流水号
	 *	Setdate	DATE(8)	清算日期，格式YYYYMMDD
	 *	Appcode	CHAR(10)	中间业务需要填写
	 *	PageRows	NUMBER(6)	每页条数
	 *	StartRows	NUMBER(6)	起始条数
	 *  @param trscode
	 *  @param pageRows
	 *  @param startRows
	 *  @return
	 */
	public static String requestHeader(String trscode,String pageRows,String startRows){
		StringBuilder header = new StringBuilder();
		header.append(trscode)      //基金后台交易码
		      .append("1.00")       //版本号 
		      .append("990110    ")    //交易机构号
		      .append("9901039 ")   //交易柜员号
		      .append("0000000001") //前置流水号
		      .append(DateUtils.getDate8()) //清算日期
		      .append("          ")         //中间业务
		      .append(addZero(pageRows,6))  //每页条数
		      .append(addZero(startRows,6));//起始条数
		return header.toString();
	}
	
	public static String requestBody(TradeModel tm){
		StringBuilder body = new StringBuilder();
		int start = Integer.valueOf(tm.getFieldStart());
		int end = Integer.valueOf(tm.getFieldEnd());
		Map<String,Object> map = JsonUtils.transBean2Map11(tm);
		for(int i = start; i <= end; i++){
			if(null != map && map.containsKey("field" + i) &&
					StringUtils.isNotBlank(map.get("field" + i).toString())){
				body.append(map.get("field" + i).toString()).append(SP);
				/*if(i == end){
					body.append(map.get("field" + i).toString());
				}else{
					body.append(map.get("field" + i).toString()).append(SP);
				}*/
			}else{
				body.append("").append(SP);
				/*if(i == end){
					body.append("");
				}else{
					body.append("").append(SP);
				}*/
			}
		}
		return body.toString();
	}
	

	/**
	 *  6 报文长度
	 *	setTell	CHAR (8)	交易柜员号，
	 *	setSeqno	NUMBER(10)	填写请求报文头的相应内容
	 *	gettrscode	CHAR(6)	业务代码，
	 *	setDate	CHAR(8)	填写请求报文头的相应内容
	 *	Response	CHAR(4)	响应码，“0000”为成功完成，其他为错误
	 *	Respdesc	CHAR(80)	响应详细信息
	 *	RowCounts	NUMBER(6)	总条数
	 *	returnRows	NUMBER(6)	实际返回条数
	 *	fundAppSno	NUMBER(6)	基金交易流水号，使用柜面系统跟踪号，第11域，每天不重复，6位
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String responseHeader(String response) throws UnsupportedEncodingException{
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(response) && response.length() >=80){
        	sb.append("<div class=\"col-sm-3\">报文长度：" + response.substring(0, 6) + "</div>");
            sb.append("<div class=\"col-sm-3\">交易柜员号：" + response.substring(6, 14) + "</div>");
            sb.append("<div class=\"col-sm-3\">setSeqno：" + response.substring(14, 24) + "</div>");
            sb.append("<div class=\"col-sm-3\">业务代码：" + response.substring(24, 30) + "</div>");
            sb.append("<div class=\"col-sm-3\">setDate：" + response.substring(30, 38) + "</div>");
            sb.append("<div class=\"col-sm-3\">returnCode：" + response.substring(38, 42) + "</div>");
            response = response.substring(42, response.length());
            String returnDesc = subStr(response, 80);
            sb.append("<div class=\"col-sm-3\">returnDesc：" + returnDesc + "</div>");
            response = response.substring(returnDesc.length(), response.length());
            sb.append("<div class=\"col-sm-3\">总条数：" + response.substring(0, 6) + "</div>");
            sb.append("<div class=\"col-sm-3\">实际返回条数：" + response.substring(6, 12) + "</div>");
            sb.append("<div class=\"col-sm-9\">基金交易流水号：" + response.substring(12, 18) + "</div>");
        }else{
			throw new BusinessException("返回报文长度异常");
        }
        return sb.toString();
	}
	
	private static String addZero(String str,int length){
		StringBuilder sb = new StringBuilder();
		if(null == str){
			str = "";
		}
		length = length - str.length();
		if(length > 0){
			for(int i=0;i<length;i++){
				sb.append("0");
			}
		}
		return sb.toString() + str;
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
	
	public static String StartRows(String prows,String srows){
		int s = (Integer.valueOf(srows)-1) * Integer.valueOf(prows) + 1; 
		return String.valueOf(s);
	}

	public static List<TradeResponseModel> getResult(String interfaceFlag,int pageNum,String response,String fieldCnt) throws Exception{
		List<TradeResponseModel> list = new ArrayList<>();
		response = response.substring(42, response.length());
        String returnDesc = subStr(response, 80);
        response = response.substring(returnDesc.length(), response.length());
        response = response.substring(18, response.length());
        if(null != response && response.length() > 0){
			list = getResultItem(response,pageNum,Integer.valueOf(fieldCnt));
        }
        return list;
	}
	
	public static List<TradeResponseModel> getResultItem(String response,int pageNum,int total){
		List<TradeResponseModel> list = new ArrayList<>();
        Map<String,Object> map = null;
		String[] res = StringUtils.split(response, "|");
		int cnt = 0;
		int end = res.length/total;
		for(int i = 1;i <= end;i++){
			map = new HashMap<>();
			for(int j=1;j<=total;j++){
				map.put("field" + j, res[cnt]);
				cnt++;
			}
			list.add(JsonUtils.getBean(JsonUtils.toJSONNoFeatures(map), TradeResponseModel.class));
		}
		return list;
	}
	
	public static String subStr(String data,int start,int end) throws Exception{
		if(!StringUtils.isNotBlank(data)){
			return "";
		}
		return new String(ArrayUtils.subarray(data.getBytes("GBK"), start,end),"GBK");
	}
	
}
