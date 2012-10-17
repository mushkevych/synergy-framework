package com.reinvent.synergy.system;

import com.reinvent.surus.primarykey.AbstractPrimaryKey;
import com.reinvent.surus.system.ColumnSubset;
import com.reinvent.surus.system.TimeQualifier;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;

/**
 * @author Bohdan Mushkevych
 *         Description: defines common properties for SynergyProcess
 */
public class SynergyProcess {
    private String name;
    private String tableSource;
    private String tableTarget;
    private ColumnSubset subsetSource;
    private TimeQualifier qualifier;
    private AbstractPrimaryKey pkSource;
    private AbstractPrimaryKey pkTarget;
    private Class<? extends TableMapper> mapper;
    private Class combiner;
    private Class<? extends TableReducer> reducer;

    public SynergyProcess(String name,
                          String tableSource,
                          String tableTarget,
                          ColumnSubset subsetSource,
                          TimeQualifier qualifier,
                          AbstractPrimaryKey pkSource,
                          AbstractPrimaryKey pkTarget,
                          Class<? extends TableMapper> mapper,
                          Class combiner,
                          Class<? extends TableReducer> reducer) {
        this.name = name;
        this.tableSource = tableSource;
        this.tableTarget = tableTarget;
        this.subsetSource = subsetSource;
        this.qualifier = qualifier;
        this.pkSource = pkSource;
        this.pkTarget = pkTarget;
        this.mapper = mapper;
        this.combiner = combiner;
        this.reducer = reducer;
    }

    public String getName() {
        return name;
    }

    public String getTableSource() {
        return tableSource;
    }

    public String getTableTarget() {
        return tableTarget;
    }

    public ColumnSubset getSubsetSource() {
        return subsetSource;
    }

    public TimeQualifier getQualifier() {
        return qualifier;
    }

    public AbstractPrimaryKey getPkSource() {
        return pkSource;
    }

    public AbstractPrimaryKey getPkTarget() {
        return pkTarget;
    }

    public Class<? extends TableMapper> getMapperClass() {
        return mapper;
    }

    public Class getCombinerClass() {
        return combiner;
    }

    public Class<? extends TableReducer> getReducerClass() {
        return reducer;
    }
}

