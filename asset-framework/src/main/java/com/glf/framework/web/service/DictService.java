package com.glf.framework.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glf.sys.model.SysDictData;
import com.glf.sys.service.ISysDictDataService;

/**
 * html调用 thymeleaf 实现字典读取
 */
@Service("dict")
public class DictService{
	
    @Autowired
    private ISysDictDataService dictDataService;
    /**
     * 根据字典类型查询字典数据信息
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<SysDictData> getType(String dictType){
    	System.out.println("DictService.getType()");
        return dictDataService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue){
    	System.out.println("DictService.getLabel()");
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
