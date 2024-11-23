package com.example.ProgAV2;

public class Respuesta {

    private String message;
    private Object data;

    public Respuesta(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
