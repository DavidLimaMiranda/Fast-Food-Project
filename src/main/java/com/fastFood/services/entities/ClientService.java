package com.fastFood.services.entities;

import com.fastFood.clientes.Client;
import com.fastFood.clientes.TypeClient;
import com.fastFood.dtos.ClientDTO;
import com.fastFood.food.Food;
import com.fastFood.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client findClientById(Long id) throws Exception {

        return this.repository.findClientById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
    }

    public void saveClient(Client client) {

        this.repository.save(client);
    }

    public Client createClient(ClientDTO client) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(client.password());

        var newClient = new Client(client, encryptedPassword);

        this.saveClient(newClient);

        return newClient;
    }

    public List<Client> getAllClients() {

        return this.repository.findAll();
    }
}
