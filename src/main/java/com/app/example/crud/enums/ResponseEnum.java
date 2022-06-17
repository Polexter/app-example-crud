package com.app.example.crud.enums;

public enum ResponseEnum {

    SUCCESS("200", "Success");

    private String code;
    private String mensaje;

    ResponseEnum(String code, String mensaje) {
        this.code = code;
        this.mensaje = mensaje;
    }

    public String getCode() {
        return code;
    }

    public String getMensaje() {
        return mensaje;
    }
}
