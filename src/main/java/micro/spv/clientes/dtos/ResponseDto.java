package micro.spv.clientes.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T extends Serializable> implements Serializable {
    Short type;
    String messages;
    T data;
}
