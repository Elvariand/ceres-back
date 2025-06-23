package com.jlgdev.ceres.models;

// Ici on ne met que ce qui est nécessaire au login, dans cet exemple c'est comme le RegisterDTO mais en vrai il est plus succint
public class LoginDTO {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    

}
