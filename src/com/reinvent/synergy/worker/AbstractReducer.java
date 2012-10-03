package com.reinvent.synergy.worker;

import com.reinvent.synergy.system.JobRunner;
import com.reinvent.synergy.system.ProcessContext;
import com.reinvent.surus.system.TimeQualifier;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author Bohdan Mushkevych
 * Description: Abstract Reducer for all Synergy reducers
 */
public class AbstractReducer extends TableReducer<ImmutableBytesWritable, Result, ImmutableBytesWritable> {
    protected int timePeriod;
    protected String processName;


    @Override
    protected void setup(Reducer.Context context) throws IOException, InterruptedException {
        super.setup(context);
        timePeriod = Integer.parseInt(context.getConfiguration().get(JobRunner.TIME_PERIOD));
        processName = context.getConfiguration().get(JobRunner.PROCESS_NAME);
    }

    /**
     * @return TimeQualifier defined during start-up of this process
     * @see TimeQualifier
     */
    public TimeQualifier getTimeQualifier() {
        return ProcessContext.get(processName).getQualifier();
    }
}
