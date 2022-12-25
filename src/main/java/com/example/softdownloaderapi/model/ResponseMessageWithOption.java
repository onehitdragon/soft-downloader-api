package com.example.softdownloaderapi.model;

public class ResponseMessageWithOption<T> extends ResponseMessage{
    private T option;

    public T getOption() {
        return option;
    }

    public void setOption(T option) {
        this.option = option;
    }

    public ResponseMessageWithOption(String status, String message, T option){
        super(status, message);
        this.option = option;
    }
}
