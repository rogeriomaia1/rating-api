package com.datapar.exception;

public class RatingException extends Exception{

	private static final long serialVersionUID = 1L;
	private String code;

    public RatingException(String message, String code) {
        super(message);
        this.code = code;
    }
}
