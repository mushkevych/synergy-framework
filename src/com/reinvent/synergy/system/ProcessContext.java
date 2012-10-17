package com.reinvent.synergy.system;

import com.reinvent.surus.mapping.EntityService;
import com.reinvent.surus.model.Constants;
import com.reinvent.surus.model.ExampleComplex;
import com.reinvent.surus.primarykey.HPrimaryKey;
import com.reinvent.surus.system.TimeQualifier;
import com.reinvent.synergy.worker.*;
import org.apache.log4j.Logger;

import java.util.HashMap;

/**
 * @author Bohdan Mushkevych
 *         Description: Process Context holds definition of all Synergy Processes
 */
public class ProcessContext {
    private final static HashMap<String, SynergyProcess> CONTEXT = new HashMap<String, SynergyProcess>();
    private static Logger log = Logger.getLogger(ProcessContext.class);

    public static final String PROCESS_EXAMPLE = "ExampleWorker";

    static {
        CONTEXT.put(PROCESS_EXAMPLE, new SynergyProcess(PROCESS_EXAMPLE,
                Constants.TABLE_EXAMPLE,
                Constants.TABLE_EXAMPLE,
                null,
                TimeQualifier.DAILY,
                new HPrimaryKey<ExampleComplex>(ExampleComplex.class, new EntityService<ExampleComplex>(ExampleComplex.class)),
                new HPrimaryKey<ExampleComplex>(ExampleComplex.class, new EntityService<ExampleComplex>(ExampleComplex.class)),
                ExampleMapper.class,
                null,
                ExampleReducer.class));
    }

    public static SynergyProcess get(String processName) {
        if (!ProcessContext.CONTEXT.containsKey(processName)) {
            String msg = String.format("Process %s is unknown to synergy-hadoop", processName);
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        return ProcessContext.CONTEXT.get(processName);
    }
}
