package com.tw.management.resources.persistence.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tw.management.resources.persistence.base.BaseEntity;
import com.tw.management.resources.persistence.user_rights.UserRightsEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity extends BaseEntity implements UserDetails {

    @Column(unique = true)
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotNull
    private Boolean role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRightsEntity> userRights;

    public UserEntity() { }

    public UserEntity(@NotEmpty String username, @NotEmpty String password, @NotNull Boolean role, List<UserRightsEntity> userRights) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userRights = userRights;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public List<UserRightsEntity> getUserRights() {
        return userRights;
    }

    public void setUserRights(List<UserRightsEntity> userRights) {
        this.userRights = userRights;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("authorised"));
        return authorities;
    }
}
