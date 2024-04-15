package com.geunoo.mzsangsicbackend.domain.user.entity;

import com.gil.easyjwt.user.JwtUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
public class User extends JwtUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String email;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    private String profileUrl;

    @Builder
    public User(String email, String name, String profileUrl) {
        this.email = email;
        this.name = name;
        this.profileUrl = profileUrl;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
