package com.glf.module.common;

import java.io.UnsupportedEncodingException;

public class Test {
	

    public static void main(String[] args) throws Exception{
    	for(int i = 0;i<7;i++){
    		subStrs(i);
    	}
    }

    public static void subStrs(int i){
    	if(i > 3){
        	System.out.println("a:" + i);
        	System.exit(1);
    	}
    	System.out.println(i);
    }
    public static String subStr(String str, int subSLength)    
            throws UnsupportedEncodingException{   
        if (str == null)    
            return "";    
        else{   
            int tempSubLength = subSLength;//截取字节数  
            String subStr = str.substring(0, str.length()<subSLength ? str.length() : subSLength);//截取的子串    
            int subStrByetsL = subStr.getBytes("GBK").length;//截取子串的字节长度   
            //int subStrByetsL = subStr.getBytes().length;//截取子串的字节长度   
            // 说明截取的字符串中包含有汉字    
            while (subStrByetsL > tempSubLength){    
                int subSLengthTemp = --subSLength;  
                subStr = str.substring(0, subSLengthTemp>str.length() ? str.length() : subSLengthTemp);    
                subStrByetsL = subStr.getBytes("GBK").length;  
                //subStrByetsL = subStr.getBytes().length;  
            }    
            return subStr;   
        }  
    }  

}
