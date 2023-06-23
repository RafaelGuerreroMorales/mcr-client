package micro.spv.clientes.mappers;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import micro.spv.clientes.dtos.ClientDto;
import micro.spv.clientes.dtos.PhoneDto;
import micro.spv.clientes.entities.ClientEntity;
import micro.spv.clientes.entities.PhoneEntity;

@Component
public class ClientMapper {
    static final String REGEX_LETTERS = "^[a-zA-Z].*[A-z]$";
    static final String REGEX_DATE = "^[\\d]{4}.[\\d]{2}.[\\d]{2}$";
    static final String REGEX_PHONE_NUMBER = "[0-9]{10}";
    static final String REGEX_NUMBERS_LETTERS = "[0-9 A-Za-z]*$";

    public ClientDto todDto(ClientEntity entity){
        return new ClientDto(entity.getIdentityclient(), entity.getFirstname(),
            entity.getSecondName(), entity.getFirstLastName(), entity.getSecondLastName(),
            entity.getDateOfBirth().toString(), null);
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
        
        return new ClientEntity(dto.getIdClient(),dto.getFirstName().toUpperCase(), dto.getSecondName().toUpperCase(),
            dto.getFirstLastName().toUpperCase(), dto.getSecondLastName().toUpperCase(), this.getLocaDate(dto.getBirthDate()));
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

    public PhoneDto toDto(PhoneEntity entity){
        return new PhoneDto(entity.getIdentityPhone(), entity.getIdentityClient(),
            entity.getPhoneNumber(), entity.getDescription());
    }

    public PhoneEntity toEntity(PhoneDto dto){
        final String ilegalArgument = "IllegalArgument:";
        var patter = Pattern.compile(REGEX_PHONE_NUMBER);

        this.replaceNullVariable(dto);

        if(dto.getIdClient() == 0)
            throw new IllegalArgumentException(dto.getPhoneNumber() == "" ?
                 "PhoneDto.idClient == Null && PhoneDto.phoneNumber == Null" : "PhoneDto.idClient == Null");
        else if(dto.getPhoneNumber() == "" )
            throw new IllegalArgumentException("PhoneDto.idClient == Null");
        
        String illegaString = ilegalArgument;
        illegaString += patter.matcher(dto.getPhoneNumber()).matches() ? "":
             "PhoneDto.phoneNumer = " + dto.getPhoneNumber();
        illegaString += dto.getDescription().equals("") ? "" :
            "PhoneDto.description" + dto.getDescription();
        
        if(!illegaString.equals(ilegalArgument))
            throw new IllegalArgumentException(illegaString);

        return new PhoneEntity(dto.getIdPhone(), dto.getIdClient(), dto.getPhoneNumber(),
            dto.getDescription().toUpperCase());
    }   

    private void replaceNullVariable(PhoneDto dto){
        dto.setPhoneNumber(dto.getPhoneNumber() == null ? "": dto.getPhoneNumber());
        dto.setDescription(dto.getDescription() == null ? "": dto.getDescription());
    }
}