package com.geunoo.mzsangsicbackend.domain.user.entity;

import com.gil.easyjwt.user.JwtUser;
import jakarta.persistence.*;
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

    @Lob
    private byte[] image;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    private String sub;

    @Builder
    public User(String email, String name, byte[] image, String sub) {
        this.email = email;
        this.name = name;
        this.image = image;
        this.sub = sub;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setProfileImage(byte[] profileUrl) {
        this.image = profileUrl;
    }
}
