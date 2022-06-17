package com.app.example.crud.enums;

public enum EliminadoEnum {

    ELIMINADO(0),
    NO_ELIMINADO(1);

    private Integer value;

    EliminadoEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
