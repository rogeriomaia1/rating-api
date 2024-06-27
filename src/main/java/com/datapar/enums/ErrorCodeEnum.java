package com.datapar.enums;


public enum ErrorCodeEnum {
    ER0001("Erro ao salvar a votação", "ER-0001"),
    ER0002("Lista de votação vazia", "ER-0002")
    ;
	
    private String message;
    private String code;

    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

   
}
