package com.reinvent.synergy.worker;

import com.reinvent.surus.mapping.EntityService;
import com.reinvent.surus.model.ExampleComplex;
import com.reinvent.surus.model.Constants;
import com.reinvent.surus.primarykey.HPrimaryKey;
import com.reinvent.surus.primarykey.IntegerPrimaryKey;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author Bohdan Mushkevych
 * Description: Examplary vertical mapper
 */
public class ExampleMapper extends AbstractMapper {
    private static Logger log = Logger.getLogger(ExampleMapper.class);
    private HPrimaryKey<ExampleComplex> pkExampleComplex =
            new HPrimaryKey<ExampleComplex>(ExampleComplex.class, new EntityService<ExampleComplex>(ExampleComplex.class));
    private IntegerPrimaryKey pkInteger = new IntegerPrimaryKey();

    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        Integer timperiod = (Integer) pkExampleComplex.getCastedComponentValue(key.get(), Constants.TIMEPERIOD);
        String domainName = (String) pkExampleComplex.getCastedComponentValue(key.get(), Constants.DOMAIN_NAME);

        ImmutableBytesWritable convertedKey = pkInteger.generateKey(timperiod);
        context.write(convertedKey, value);
    }
}
