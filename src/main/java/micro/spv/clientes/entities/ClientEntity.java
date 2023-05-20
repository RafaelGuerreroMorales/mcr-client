package micro.spv.clientes.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cat_clients")
@Entity
@Data
public class ClientEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "identity_client")
    int identityclient;

    @Column(name = "first_name")
    String firstname;

    @Column(name = "second_name")
    String secondName;

    @Column(name = "first_last_name")
    String firstLastName;

    @Column(name = "second_last_name")
    String secondLastName;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;
}
