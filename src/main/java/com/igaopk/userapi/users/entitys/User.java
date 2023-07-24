package com.igaopk.userapi.users.entitys;

import com.igaopk.userapi.users.dtos.CreateUserDTO;
import com.igaopk.userapi.users.dtos.UserDTO;
import com.igaopk.userapi.users.mappers.CellPhoneMapper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "TB_USERS", schema = "public")
@Entity(name = "user")
@Data
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @Column(name = "user_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "user_password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CellPhone> cellPhones = new ArrayList<>();

    public User() {

    }

    public User(String fullName, String userName, String password, List<CellPhone> cellPhones) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.cellPhones = cellPhones;
    }

    public User(CreateUserDTO createUserDTO) {
        this.fullName = createUserDTO.fullName();
        this.userName = createUserDTO.userName();
        this.password = createUserDTO.password();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void update(UserDTO userDTO) {
        if (!userDTO.fullName().isBlank()) {
            this.fullName = userDTO.fullName();
        }

        if (!userDTO.password().isBlank() || userDTO.password().length() < 4) {
            this.password = userDTO.password();
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
