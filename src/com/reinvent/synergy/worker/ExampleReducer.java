package com.reinvent.synergy.worker;

import com.reinvent.surus.mapping.EntityService;
import com.reinvent.surus.model.Bucket;
import com.reinvent.surus.model.ExampleComplex;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author Bohdan Mushkevych
 * Description: Examplary vertical reducer
 */
public class ExampleReducer extends AbstractReducer {
    private static Logger log = Logger.getLogger(ExampleReducer.class);
    private EntityService<ExampleComplex> esExampleComplex = new EntityService<ExampleComplex>(ExampleComplex.class);
    private EntityService<Bucket> esBucket = new EntityService<Bucket>(Bucket.class);

    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<Result> values, Context context) throws IOException, InterruptedException {
        Bucket targetDocument = new Bucket();
        targetDocument.key = key.get();

        for (Result singleResult : values) {
            ExampleComplex sourceDocument = esExampleComplex.parseResult(singleResult);
            targetDocument.keyword.putAll(sourceDocument.keyword);
            targetDocument.keywordIndex.putAll(sourceDocument.keywordIndex);
            targetDocument.os.putAll(sourceDocument.os);
        }

        context.write(key, esBucket.insert(targetDocument));
    }
}
