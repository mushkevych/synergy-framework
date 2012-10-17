package com.reinvent.synergy.worker;

import com.reinvent.surus.primarykey.AbstractPrimaryKey;
import com.reinvent.surus.system.TimeQualifier;
import com.reinvent.synergy.system.JobRunner;
import com.reinvent.synergy.system.ProcessContext;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;

import java.io.IOException;

/**
 * @author Bohdan Mushkevych
 *         Description: abstract mapper for all Synergy Mappers
 */
public class AbstractMapper extends TableMapper<ImmutableBytesWritable, Result> {
    protected int timePeriod;
    protected String processName;


    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        timePeriod = Integer.parseInt(context.getConfiguration().get(JobRunner.TIME_PERIOD));
        processName = context.getConfiguration().get(JobRunner.PROCESS_NAME);
    }

    public AbstractPrimaryKey getPrimaryKey() {
        return ProcessContext.get(processName).getPkSource();
    }

    public TimeQualifier getTimeQualifier() {
        return ProcessContext.get(processName).getQualifier();
    }
}