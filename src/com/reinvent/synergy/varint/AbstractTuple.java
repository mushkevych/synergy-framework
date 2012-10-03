package com.reinvent.synergy.varint;

/**
 * @author Bohdan Mushkevych
 * Description:
 */
public class AbstractTuple {
    protected TupleType type;

    public void setType(TupleType type) {
        this.type = type;
    }

    public TupleType getType() {
        return type;
    }
}
