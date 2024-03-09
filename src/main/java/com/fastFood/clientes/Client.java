package com.fastFood.clientes;

import com.fastFood.dtos.ClientDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Entity(name = "clientes")
@Table(name = "Clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(name = "passwords")
    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private TypeClient typeAccount;

    public Client(ClientDTO clientDTO) {

        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.balance = clientDTO.balance();
        this.typeAccount = clientDTO.typeAccount();
        this.email = clientDTO.email();
        this.password = clientDTO.password();
    }

    public Client(ClientDTO clientDTO, String encryptedPassword) {
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.balance = clientDTO.balance();
        this.typeAccount = clientDTO.typeAccount();
        this.email = clientDTO.email();
        this.password = encryptedPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.typeAccount == TypeClient.COMPANY)
            return List.of(new SimpleGrantedAuthority("ROLE_COMPANY"));

        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public String getUsername() {
        return email;
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
