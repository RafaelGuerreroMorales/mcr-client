package micro.spv.clientes.mappers;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import micro.spv.clientes.dtos.ClientDto;
import micro.spv.clientes.entities.ClientEntity;

@Component
public class ClientMapper {
    static final String REGEX_LETTERS = "^[a-zA-Z].*[A-z]$";
    static final String REGEX_DATE = "^[\\d]{4}.[\\d]{2}.[\\d]{2}$";

    public ClientDto todDto(ClientEntity entity){
        return new ClientDto(entity.getIdentityclient(), entity.getFirstname(),
            entity.getSecondName(), entity.getFirstLastName(), entity.getSecondLastName(),
            entity.getDateOfBirth().toString());
    }

    public ClientEntity toEntity(ClientDto dto){
        final String ilegalArgument = "IllegalArgument:";
        var patter = Pattern.compile(REGEX_LETTERS);

        this.replaceNullVariable(dto);

        if(dto.getFirstName().equals("") )
            throw new NullPointerException(dto.getFirstLastName().equals("") ?
                "ClientDto.firstName = NULL && ClientDto.firstLastName = NULL" : "ClientDto.firstName = NULL");
        else if (dto.getFirstLastName().equals(""))
            throw new NullPointerException("ClientDto.firstLastName = NULL");

        String illegalString = ilegalArgument;
        illegalString += !dto.getFirstName().equals("")  && !patter.matcher(dto.getFirstName()).matches() ? 
            " ClientDto.firstName = " + dto.getFirstName() : "";
        illegalString += !dto.getFirstLastName().equals("")  && !patter.matcher(dto.getFirstLastName()).matches() ? 
        " ClientDto.secondName = " + dto.getFirstLastName() : "";
        illegalString += !dto.getSecondName().equals("")  && !patter.matcher(dto.getSecondName()).matches() ? 
        " ClientDto.firstLastName = " + dto.getSecondName() : "";
        illegalString += !dto.getSecondLastName().equals("")  && !patter.matcher(dto.getSecondLastName()).matches() ? 
        " ClientDto.secondLastName = " + dto.getSecondLastName() : "";
        
        patter = Pattern.compile(REGEX_DATE);
        illegalString += !dto.getBirthDate().equals("") && !patter.matcher(dto.getBirthDate()).matches() ? 
        " ClientDto.birthDate = " + dto.getBirthDate() : "";

        if(!illegalString.equals(ilegalArgument))
            throw new IllegalArgumentException(illegalString);
        
        return new ClientEntity(0,dto.getFirstName(), dto.getSecondName(), dto.getFirstLastName(),
            dto.getSecondLastName(), this.getLocaDate(dto.getBirthDate()));
    }

    private void replaceNullVariable(ClientDto dto){
        dto.setFirstName(dto.getFirstName() == null ? "" : dto.getFirstName());
        dto.setSecondName(dto.getSecondName() == null ? "" : dto.getSecondName());
        dto.setFirstLastName(dto.getFirstLastName() == null ? "" : dto.getFirstLastName());
        dto.setSecondLastName(dto.getSecondLastName() == null ? "" : dto.getSecondLastName());
        dto.setBirthDate(dto.getBirthDate() == null ? "" : dto.getBirthDate());
    }

    private LocalDate getLocaDate(String stringDate){
        if(stringDate.equals("") && !Pattern.compile(REGEX_DATE).matcher(stringDate).matches()) {
            return LocalDate.of(1316, 01, 01);
        } else {
            var splitDate = stringDate.split("/");
            return LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[2]));
        }

    }
}