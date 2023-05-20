package micro.spv.clientes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import micro.spv.clientes.dtos.ClientDto;
import micro.spv.clientes.entities.ClientEntity;
import micro.spv.clientes.enumerators.ClientCodeSaveEnum;
import micro.spv.clientes.mappers.ClientMapper;
import micro.spv.clientes.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired 
    ClientMapper clientMapper;

    @Transactional
    public ClientCodeSaveEnum saveClient(ClientDto dto) {
        var clientEntity = clientMapper.toEntity(dto);
        var clientList = clientRepository.findClientByFullName(clientEntity.getFirstname(), clientEntity.getSecondName(),
                clientEntity.getFirstLastName(), clientEntity.getSecondLastName());

        if(!clientList.stream().filter(item -> item.getFirstname().equals(clientEntity.getFirstname()) &&
            item.getSecondName().equals(clientEntity.getSecondName()) &&
            item.getFirstLastName().equals(clientEntity.getFirstLastName()) &&
            item.getSecondLastName().equals(clientEntity.getSecondLastName())).toList().isEmpty())
            return ClientCodeSaveEnum.CLIENT_EXIST;
            
        clientRepository.saveEntity(clientEntity);
           
        return ClientCodeSaveEnum.CLIENT_CORRECT;
    }

    public List<ClientDto> getAllClient(){
        List<ClientDto> listClient = new ArrayList<>();

        for(ClientEntity item : clientRepository.findAllClients())
            listClient.add(clientMapper.todDto(item));

        return listClient;
    }
    
}
