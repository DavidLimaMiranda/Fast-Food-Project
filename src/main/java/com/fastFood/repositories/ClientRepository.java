package com.fastFood.repositories;

import com.fastFood.clientes.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    Optional<Client> findClientById(Long id);

    Optional<Client> findClientByEmail(String email);

    UserDetails findUserByEmail(String login);
}
