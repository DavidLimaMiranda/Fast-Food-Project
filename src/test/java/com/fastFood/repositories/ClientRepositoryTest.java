package com.fastFood.repositories;
import com.fastFood.clientes.Client;
import com.fastFood.clientes.TypeClient;
import com.fastFood.dtos.ClientDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Caso de sucesso na criação do cliente")
    void findClientByEmailCaseSucesse() {

        var data = new ClientDTO(
                "Company",
                "fast-food",
                new BigDecimal(1000),
                "company20@email.com",
                TypeClient.COMPANY);
        this.createUser(data);

        Optional<Client> result = this.clientRepository.findClientByEmail(data.email());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Caso de fracasso na criação do cliente")
    void findClientByEmailCaseFail() {

        Optional<Client> result = this.clientRepository.findClientByEmail("Email Inválido@gmail.com");

        assertThat(result.isEmpty()).isTrue();
    }

    private Client createUser(ClientDTO client) {
        var newClient = new Client(client);

        this.entityManager.persist(newClient);
        return newClient;
    }

}
