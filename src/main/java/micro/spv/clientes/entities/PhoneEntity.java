package micro.spv.clientes.entities;

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
@Table(name = "cat_phones")
@Entity
@Data
public class PhoneEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "identity_phone")
    int identityPhone;

    @Column(name = "identity_client")
    int identityClient;

    @Column(name = "number_phone")
    String phoneNumber;

    @Column(name = "description")
    String description;
}
