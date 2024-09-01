package com.hospital.patient.dto;

public class AuthenticationResponse {

    private String jwt;
    private String message;
    public AuthenticationResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message=message;
    }

    public String getJwt() {
        return jwt;
    }
    public void setJwt(String jwt) {
        this.message = message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }


}
