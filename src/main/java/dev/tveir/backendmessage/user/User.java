package dev.tveir.backendmessage.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data // Generates getters for all fields and setters for all non-final fields. reduce boilerplate
@Builder // Build an object using designing patterns.
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user") // User is a reserved keyword for Postgresql
public class User implements UserDetails {
    @Id // Primary key
    @GeneratedValue() // Auto-increment (default value is Auto)
    private Integer id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String userImageUrl;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
