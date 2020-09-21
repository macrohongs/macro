package com.glf.quartz.util;

import org.quartz.JobExecutionContext;
import com.glf.quartz.model.SysJob;

public class QuartzJobExecution extends AbstractQuartzJob{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception{
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
