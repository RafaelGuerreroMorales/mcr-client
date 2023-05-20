package micro.spv.clientes.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import micro.spv.clientes.dtos.ClientDto;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class ClientMapperTest {
    @Autowired
    ClientMapper clientMapper;

    @Test
    void dtoToEntityTest(){
        var dto = new ClientDto(0,"Rafael","Alonso", "Guerrero", "Morales", "1994/06/13");
        var entity = clientMapper.toEntity(dto);

        assertEquals("Rafael",entity.getFirstname());
        assertEquals("Alonso",entity.getSecondName());
        assertEquals("Guerrero",entity.getFirstLastName());
        assertEquals("Morales",entity.getSecondLastName());
        assertEquals(LocalDate.of(1994, 06, 13),entity.getDateOfBirth());

        dto = new ClientDto(0,"Rafael",null , "Guerrero", null, null);
        entity = clientMapper.toEntity(dto);
        
        assertEquals("Rafael",entity.getFirstname());
        assertEquals("",entity.getSecondName());
        assertEquals("Guerrero",entity.getFirstLastName());
        assertEquals("",entity.getSecondLastName());
        assertEquals(LocalDate.of(1316, 01, 01),entity.getDateOfBirth());
    }
    
}
