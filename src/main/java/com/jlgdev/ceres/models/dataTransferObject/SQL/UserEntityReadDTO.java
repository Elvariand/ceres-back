package com.jlgdev.ceres.models.dataTransferObject.SQL;

import java.util.List;
import java.util.Set;

import com.jlgdev.ceres.models.entities.Role;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserEntityReadDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private String email;

    private Set<Role> roles;

    private List<String> history;

    private Set<String> favorite;

    private UserPreferenceReadDTO preferences;

    public Long  getId() {
        return id;
    }

    public void setId(Long  id) {
        this.id = id;
    }

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

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

    public Set<String> getFavorite() {
        return favorite;
    }

    public void setFavorite(Set<String> favorite) {
        this.favorite = favorite;
    }

    public UserPreferenceReadDTO getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferenceReadDTO preferences) {
        this.preferences = preferences;
    }

    public UserEntityReadDTO() {
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", email=" + email + ", roles=" + roles
                + ", history=" + history + ", favorite=" + favorite + "]";
    }

}
