package micro.spv.clientes.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto implements Serializable {
    int idClient;
    String firstName;
    String secondName;
    String firstLastName;
    String secondLastName;
    String birthDate;
    List<PhoneDto> phones;
}
