package com.capcal.userservice.exception;

public class ErrorInfo {
    public final String statuscode;
    public final String ex;

    public ErrorInfo(String statuscode, Exception ex) {
        this.statuscode=statuscode;
        this.ex = ex.getLocalizedMessage();
    }
}
