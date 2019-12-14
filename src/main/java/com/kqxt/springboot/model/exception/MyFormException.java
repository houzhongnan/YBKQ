package com.kqxt.springboot.model.exception;

public class MyFormException extends RuntimeException{

    public MyFormException() {
        super();
    }

    public MyFormException(String message) {
        super(message);
    }

    public MyFormException(String message, Throwable cause) {
        super(message, cause);
    }


    public MyFormException(Throwable cause) {
        super(cause);
    }
}
