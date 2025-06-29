package com.jlgdev.ceres.models.dataTransferObject.SQL;

import com.jlgdev.ceres.validation.IdenticalPasswords;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@IdenticalPasswords
public class UserEntityCreateDTO implements IWithConfirmPassword {

    @NotBlank(message = "Veuillez renseigner un email valide")
    @Size(max=40, message = "L'email doit faire 40 caractères maximum.")
    @Email(message = "L'email est invalide.")
    private String email;
    
    @NotBlank(message = "Veuillez renseigner un mot de passe")
    @Size(min = 1, message = "Le mot de passe doit contenir au moins 14 caractères")
    private String password;

    @NotBlank(message = "Veuillez confirmer votre mot de passe")
    private String confirmPassword;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
