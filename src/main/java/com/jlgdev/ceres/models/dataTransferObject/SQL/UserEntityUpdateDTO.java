package com.jlgdev.ceres.models.dataTransferObject.SQL;

import java.util.Arrays;
import java.util.Set;

import com.jlgdev.ceres.models.entities.Role;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


public class UserEntityUpdateDTO {


    @Size(max=40, message = "L'email doit faire 40 caractères maximum.")
    @Email(message = "L'email est invalide.")
    private String email;

    private Set<Role> roles;

    private String[] history;

    private Set<String> favorite;

    @Valid
    private UserPreferenceUpdateDTO preferences;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String[] getHistory() {
        return history;
    }

    public void setHistory(String[] history) {
        this.history = history;
    }

    public Set<String> getFavorite() {
        return favorite;
    }

    public void setFavorite(Set<String> favorite) {
        this.favorite = favorite;
    }

    public UserPreferenceUpdateDTO getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferenceUpdateDTO preferences) {
        this.preferences = preferences;
    }

    public UserEntityUpdateDTO() {
    }

    @Override
    public String toString() {
        return "UserEntityUpdateDTO [email=" + email + ", roles=" + roles + ", history=" + Arrays.toString(history)
                + ", favorite=" + favorite + ", preferences=" + preferences + "]";
    }


}
