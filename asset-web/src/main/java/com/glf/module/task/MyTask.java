package com.glf.module.task;

import org.springframework.stereotype.Component;

@Component("myTask")
public class MyTask{

    public void backUpStock() throws InterruptedException{
        System.out.println("######备份持仓数据-start");
        Thread.sleep(1000*3);
        System.out.println("######备份持仓数据-end");
    }
}
