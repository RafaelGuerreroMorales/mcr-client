package micro.spv.clientes.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneDto implements Serializable{
    int idPhone;
    int idClient;
    String phoneNumber;
    String description;
}
