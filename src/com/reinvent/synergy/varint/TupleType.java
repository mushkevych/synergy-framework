package com.reinvent.synergy.varint;

/**
 * @author Bohdan Mushkevych
 * Description: presents all types of the Tuples used in Synergy
 */
public enum TupleType {
    TYPE_TWO_INTEGERS(2),
    TYPE_THREE_INTEGERS(3),
    TYPE_FOUR_INTEGERS(4);

    private int length;

    TupleType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
