package com.jlgdev.ceres.models.dataTransferObject.NoSQL;

public class AuthResponseDTO {

    private String accessToken;
    // private String tokenType ="Bearer ";

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
