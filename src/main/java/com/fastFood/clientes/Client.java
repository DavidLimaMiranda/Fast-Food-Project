package com.fastFood.clientes;

import com.fastFood.dtos.ClientDTO;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity(name = "clientes")
@Table(name = "Clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private TypeClient typeAccount;

    public Client(ClientDTO clientDTO) {

        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.balance = clientDTO.balance();
        this.typeAccount = clientDTO.typeAccount();
        this.email = clientDTO.email();
    }
}
