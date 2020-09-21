package com.glf.module.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.glf.sys.model.JdbcModel;
import com.glf.utils.StringUtils;

public class TaData {
	
	public static Map<String,Map<String,Integer>> taList = null;
	
	public static Map<String,Map<String,Object>> taCodeList = null;
	

	public static void putTaCode(String fundId,String key,String val){
		if(null == taCodeList){
			taCodeList = new HashMap<>();
		}
		Map<String,Object> map = null;
		if(taCodeList.containsKey(fundId)){
			map = taCodeList.get(fundId);
			map.put(key, val);
			taCodeList.put(fundId, map);
		}else{
			map = new HashMap<>();
			map.put(key, val);
			taCodeList.put(fundId, map);
		}
	}

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
	
	public static Map<String,Integer> getTaList(String taCode){
		if(null == taList){
			taList = new LinkedHashMap<>();
		}
		if(!taList.containsKey(taCode)){
			putTa(taCode);
		}
		return taList.get(taCode);
	}
	
	public static void putTa(String taCode){
		switch(taCode){
			case "01":
				putTa01();
				break;
			case "02":
				putTa02();
				break;
			case "03":
				putTa03();
				break;
			case "04":
				putTa04();
				break;
			case "05":
				putTa05();
				break;
			case "06":
				putTa06();
				break;
			case "07":
				putTa07();
				break;
			case "08":
				putTa08();
				break;
			case "09":
				putTa09();
				break;
			case "10":
				putTa10();
				break;
			case "11":
				putTa11();
				break;
			case "12":
				putTa12();
				break;
			case "13":
				putTa13();
				break;
			case "21":
				putTa21();
				break;
			case "24":
				putTa24();
				break;
			case "25":
				putTa25();
				break;
			case "26":
				putTa26();
				break;
			case "C1":
				putTaC1();
				break;
			case "C2":
				putTaC2();
				break;
			case "C3":
				putTaC3();
				break;
			case "C4":
				putTaC4();
				break;
			case "C5":
				putTaC5();
				break;
			case "C6":
				putTaC6();
				break;
			case "R1":
				putTaR1();
				break;
			case "R2":
				putTaR2();
				break;
		}
	}
	
	private static void putTa01(){
		Map<String,Integer> map = new LinkedHashMap<>();
		map.put("1",120);
		map.put("2",30);
		map.put("3",1);
		map.put("4",20);
		map.put("5",24);
		map.put("6",1);
		map.put("7",30);
		map.put("8",120);
		map.put("9",8);
		map.put("10",6);
		map.put("11",1);
		map.put("12",6);
		map.put("13",30);
		map.put("14",1);
		map.put("15",20);
		map.put("16",17);
		map.put("17",9);
		map.put("18",3);
		map.put("19",28);
		map.put("20",60);
		map.put("21",9);
		map.put("22",8);
		map.put("23",19);
		map.put("24",4);
		map.put("25",3);
		map.put("26",40);
		map.put("27",24);
		map.put("28",3);
		map.put("29",22);
		map.put("30",8);
		map.put("31",24);
		map.put("32",9);
		map.put("33",22);
		map.put("34",12);
		map.put("35",8);
		map.put("36",1);
		map.put("37",10);
		map.put("38",10);
		map.put("39",12);
		map.put("40",22);
		map.put("41",8);
		map.put("42",1);
		map.put("43",1);
		map.put("44",1);
		map.put("45",8);
		map.put("46",1);
		map.put("47",17);
		map.put("48",60);
		map.put("49",28);
		map.put("50",9);
		map.put("51",8);
		map.put("52",3);
		map.put("53",9);
		map.put("54",12);
		map.put("55",40);
		map.put("56",8);
		map.put("57",8);
		map.put("58",8);
		map.put("59",1);
		map.put("60",2);
		map.put("61",80);
		map.put("62",80);
		map.put("63",1);
		map.put("64",2);
		map.put("65",16);
		map.put("66",1);
		map.put("67",1);
		map.put("68",20);
		map.put("69",20);
		map.put("70",4);
		map.put("71",2);
		map.put("72",16);
		map.put("73",2);
		map.put("74",6);
		map.put("75",6);
		map.put("76",6);
		map.put("77",40);
		map.put("78",1);
		map.put("79",1);
		map.put("80",1);
		map.put("81",8);
		map.put("82",20);
		map.put("83",24);
		map.put("84",60);
		taList.put("01", map);
	}


	private static void putTa02(){
		Map<String,Integer> map = new LinkedHashMap<>();
		map.put("1",24);
		map.put("2",8);
		map.put("3",4);
		map.put("4",17);
		map.put("5",9);
		map.put("6",3);
		map.put("7",12);
		map.put("8",1);
		map.put("9",20);
		map.put("10",8);
		map.put("11",6);
		map.put("12",9);
		map.put("13",1);
		map.put("14",1);
		map.put("15",30);
		map.put("16",120);
		map.put("17",1);
		map.put("18",12);
		map.put("19",8);
		map.put("20",4);
		map.put("21",17);
		map.put("22",9);
		map.put("23",60);
		map.put("24",12);
		map.put("25",1);
		map.put("26",8);
		map.put("27",60);
		taList.put("02", map);
	}
	private static void putTa03(){
		Map<String,Integer> map = new LinkedHashMap<>();
		map.put("1",24);
		map.put("2",6);
		map.put("3",1);
		map.put("4",8);
		map.put("5",6);
		map.put("6",17);
		map.put("7",9);
		map.put("8",16);
		map.put("9",16);
		map.put("10",3);
		map.put("11",12);
		map.put("12",5);
		map.put("13",19);
		map.put("14",4);
		map.put("15",3);
		map.put("16",9);
		map.put("17",24);
		map.put("18",8);
		map.put("19",1);
		map.put("20",2);
		map.put("21",5);
		map.put("22",8);
		map.put("23",20);
		map.put("24",8);
		map.put("25",20);
		map.put("26",5);
		map.put("27",8);
		map.put("28",9);
		map.put("29",10);
		map.put("30",9);
		map.put("31",17);
		map.put("32",4);
		map.put("33",16);
		map.put("34",60);
		map.put("35",6);
		map.put("36",16);
		map.put("37",1);
		map.put("38",8);
		map.put("39",1);
		map.put("40",8);
		map.put("41",1);
		map.put("42",1);
		map.put("43",8);
		map.put("44",5);
		map.put("45",5);
		map.put("46",1);
		map.put("47",12);
		map.put("48",2);
		map.put("49",9);
		map.put("50",12);
		map.put("51",1);
		map.put("52",20);
		map.put("53",8);
		map.put("54",8);
		map.put("55",2);
		map.put("56",12);
		map.put("57",3);
		map.put("58",1);
		map.put("59",1);
		map.put("60",1);
		map.put("61",40);
		map.put("62",5);
		map.put("63",1);
		map.put("64",16);
		map.put("65",2);
		map.put("66",2);
		map.put("67",5);
		map.put("68",6);
		map.put("69",8);
		map.put("70",8);
		map.put("71",1);
		map.put("72",1);
		map.put("73",9);
		map.put("74",16);
		taList.put("03", map);
	}
	private static void putTa04(){
		Map<String,Integer> map = new LinkedHashMap<>();
		map.put("1",24);
		map.put("2",8);
		map.put("3",3);
		map.put("4",16);
		map.put("5",16);
		map.put("6",6);
		map.put("7",1);
		map.put("8",8);
		map.put("9",6);
		map.put("10",4);
		map.put("11",17);
		map.put("12",9);
		map.put("13",16);
		map.put("14",16);
		map.put("15",3);
		map.put("16",12);
		map.put("17",20);
		map.put("18",1);
		map.put("19",5);
		map.put("20",19);
		map.put("21",4);
		map.put("22",8);
		map.put("23",10);
		map.put("24",10);
		map.put("25",7);
		map.put("26",9);
		map.put("27",24);
		map.put("28",8);
		map.put("29",10);
		map.put("30",1);
		map.put("31",8);
		map.put("32",16);
		map.put("33",2);
		map.put("34",9);
		map.put("35",16);
		map.put("36",20);
		map.put("37",60);
		map.put("38",8);
		map.put("39",9);
		map.put("40",9);
		map.put("41",17);
		map.put("42",4);
		map.put("43",1);
		map.put("44",1);
		map.put("45",16);
		map.put("46",10);
		map.put("47",16);
		map.put("48",16);
		map.put("49",7);
		map.put("50",8);
		map.put("51",1);
		map.put("52",16);
		map.put("53",7);
		map.put("54",7);
		map.put("55",16);
		map.put("56",10);
		map.put("57",16);
		map.put("58",8);
		map.put("59",10);
		map.put("60",1);
		map.put("61",1);
		map.put("62",1);
		map.put("63",1);
		map.put("64",1);
		map.put("65",8);
		map.put("66",1);
		map.put("67",6);
		map.put("68",10);
		map.put("69",5);
		map.put("70",5);
		map.put("71",1);
		map.put("72",12);
		map.put("73",2);
		map.put("74",9);
		map.put("75",12);
		map.put("76",1);
		map.put("77",20);
		map.put("78",8);
		map.put("79",8);
		map.put("80",2);
		map.put("81",12);
		map.put("82",3);
		map.put("83",1);
		map.put("84",1);
		map.put("85",8);
		map.put("86",1);
		map.put("87",40);
		map.put("88",5);
		map.put("89",1);
		map.put("90",16);
		map.put("91",2);
		map.put("92",2);
		map.put("93",5);
		map.put("94",6);
		map.put("95",16);
		map.put("96",8);
		map.put("97",7);
		map.put("98",16);
		map.put("99",16);
		map.put("100",16);
		map.put("101",16);
		map.put("102",1);
		map.put("103",20);
		map.put("104",16);
		map.put("105",1);
		map.put("106",16);
		map.put("107",16);
		map.put("108",16);
		map.put("109",8);
		map.put("110",16);
		map.put("111",16);
		map.put("112",60);
		map.put("113",1);
		map.put("114",16);
		map.put("115",1);
		map.put("116",8);
		map.put("117",16);
		map.put("118",16);
		map.put("119",16);
		map.put("120",8);
		map.put("121",8);
		taList.put("04", map);
	}
	private static void putTa05(){
		Map<String,Integer> map = new LinkedHashMap<>();
		map.put("1",16);
		map.put("2",16);
		map.put("3",8);
		map.put("4",6);
		map.put("5",17);
		map.put("6",9);
		map.put("7",12);
		map.put("8",16);
		map.put("9",9);
		map.put("10",20);
		map.put("11",16);
		map.put("12",1);
		map.put("13",1);
		map.put("14",1);
		map.put("15",8);
		map.put("16",16);
		map.put("17",1);
		map.put("18",16);
		map.put("19",1);
		map.put("20",1);
		taList.put("05", map);
	}
	private static void putTa06(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("06", map);
	}
	private static void putTa07(){
		Map<String,Integer> map = new LinkedHashMap<>();
		map.put("1",40);
		map.put("2",16);
		map.put("3",6);
		map.put("4",1);
		map.put("5",7);
		map.put("6",8);
		map.put("7",1);
		map.put("8",7);
		map.put("9",1);
		map.put("10",1);
		map.put("11",1);
		map.put("12",16);
		map.put("13",3);
		map.put("14",1);
		map.put("15",1);
		map.put("16",16);
		map.put("17",16);
		map.put("18",16);
		map.put("19",16);
		map.put("20",3);
		map.put("21",16);
		map.put("22",8);
		map.put("23",16);
		map.put("24",16);
		map.put("25",8);
		map.put("26",8);
		map.put("27",3);
		map.put("28",16);
		map.put("29",16);
		map.put("30",16);
		map.put("31",16);
		map.put("32",2);
		map.put("33",3);
		map.put("34",7);
		map.put("35",7);
		map.put("36",8);
		map.put("37",8);
		map.put("38",8);
		map.put("39",16);
		map.put("40",16);
		map.put("41",16);
		map.put("42",16);
		map.put("43",16);
		map.put("44",16);
		map.put("45",16);
		map.put("46",16);
		map.put("47",16);
		map.put("48",16);
		map.put("49",16);
		map.put("50",16);
		map.put("51",16);
		map.put("52",16);
		map.put("53",1);
		map.put("54",1);
		map.put("55",1);
		map.put("56",1);
		map.put("57",8);
		map.put("58",7);
		map.put("59",8);
		map.put("60",8);
		map.put("61",1);
		map.put("62",8);
		map.put("63",1);
		map.put("64",7);
		map.put("65",8);
		map.put("66",1);
		map.put("67",16);
		map.put("68",16);
		map.put("69",16);
		map.put("70",16);
		map.put("71",16);
		map.put("72",16);
		map.put("73",16);
		map.put("74",16);
		map.put("75",1);
		map.put("76",16);
		map.put("77",1);
		map.put("78",2);
		map.put("79",30);
		map.put("80",40);
		map.put("81",40);
		map.put("82",30);
		map.put("83",40);
		taList.put("07", map);
	}
	private static void putTa08(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("02", map);
	}
	private static void putTa09(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("09", map);
	}
	private static void putTa10(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("10", map);
	}
	private static void putTa11(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("11", map);
	}
	private static void putTa12(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("12", map);
	}
	private static void putTa13(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("13", map);
	}
	private static void putTa21(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("21", map);
	}
	private static void putTa24(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("24", map);
	}
	private static void putTa25(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("25", map);
	}
	private static void putTa26(){
		Map<String,Integer> map = new LinkedHashMap<>();
		map.put("1",16);
		map.put("2",16);
		map.put("3",8);
		map.put("4",6);
		map.put("5",17);
		map.put("6",9);
		map.put("7",12);
		map.put("8",16);
		map.put("9",9);
		map.put("10",20);
		map.put("11",16);
		map.put("12",1);
		map.put("13",1);
		map.put("14",1);
		map.put("15",8);
		map.put("16",16);
		map.put("17",1);
		map.put("18",16);
		map.put("19",1);
		map.put("20",1);
		map.put("21",8);
		taList.put("26", map);
	}
	private static void putTaC1(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("C1", map);
	}
	private static void putTaC2(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("C2", map);
	}
	private static void putTaC3(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("C3", map);
	}
	private static void putTaC4(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("C4", map);
	}
	private static void putTaC5(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("C5", map);
	}
	private static void putTaC6(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("C6", map);
	}
	private static void putTaR1(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("R1", map);
	}
	private static void putTaR2(){
		Map<String,Integer> map = new LinkedHashMap<>();
		taList.put("R2", map);
	}

}
