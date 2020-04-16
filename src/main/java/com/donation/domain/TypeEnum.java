package com.donation.domain;

public enum TypeEnum {
    GENERAL(1),GOVERNMENT(2),LEGAL(3),INDIVIDUAL(4), UNKNOWN(5);
    private int value;

    TypeEnum(int i) {
        this.value=i;
    }

    public int getValue() {
        return value;
    }

}
