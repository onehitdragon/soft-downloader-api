package com.example.softdownloaderapi.configure;

public class DataBaseConfig{
    private String connectionStr;

    public String getConnectionStr() {
        return connectionStr;
    }

    public void setConnectionStr(String connectionStr) {
        this.connectionStr = connectionStr;
    }

    public DataBaseConfig() {
        this.connectionStr = "";
    }
}
