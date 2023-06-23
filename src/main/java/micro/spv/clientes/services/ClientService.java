package micro.spv.clientes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import micro.spv.clientes.dtos.ClientDto;
import micro.spv.clientes.dtos.PhoneDto;
import micro.spv.clientes.entities.ClientEntity;
import micro.spv.clientes.entities.PhoneEntity;
import micro.spv.clientes.enumerators.ClientCodeSaveEnum;
import micro.spv.clientes.enumerators.PhoneClientCodeSaveEnum;
import micro.spv.clientes.mappers.ClientMapper;
import micro.spv.clientes.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired 
    ClientMapper clientMapper;

    public ClientDto getClientById(int idClient){
        ClientDto client = new ClientDto();
        List<PhoneDto> listPhoneClient = new ArrayList<>();

        client =clientMapper.todDto(clientRepository.findClientById(idClient));

        for(PhoneEntity item : clientRepository.findPhoneByIdentityClient(idClient))
            listPhoneClient.add(clientMapper.toDto(item));

        client.setPhones(listPhoneClient);
        return client;
    }

    public List<ClientDto> getClientByFullName(String firstName, String secondName, String firstLastName, String secondLastName){
        List<ClientDto> clientList = new ArrayList<>();
       
        for(ClientEntity item: clientRepository.findClientByFullName(firstName, secondName, firstLastName, secondLastName)){
            List<PhoneDto> listPhoneClient = new ArrayList<>();
            var itemClients = clientMapper.todDto(item);

            for(PhoneEntity itemPhone: clientRepository.findPhoneByIdentityClient(item.getIdentityclient()))
                listPhoneClient.add(clientMapper.toDto(itemPhone));

            itemClients.setPhones(listPhoneClient);
            clientList.add(itemClients);
        }

        return clientList;
    }

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

    @Transactional
    public PhoneClientCodeSaveEnum savePhoneNumber(PhoneDto dto){
        var phoneEntity = clientMapper.toEntity(dto);
        var phoneList = clientRepository.findPhoneByIdentityClient(dto.getIdClient());

        if(!phoneList.stream().filter(item -> item.getPhoneNumber().equals(dto.getPhoneNumber())).toList().isEmpty()){
            return PhoneClientCodeSaveEnum.PHONE_NUMBER_EXISTS;
        }
        clientRepository.saveEntity(phoneEntity);

        return PhoneClientCodeSaveEnum.PHONE_SAVED;
    }

    @Transactional
    public ClientCodeSaveEnum updateClient(ClientDto dto){
        var clientEntity = clientMapper.toEntity(dto);

        if(clientRepository.findClientById(clientEntity.getIdentityclient()) != null) {
            clientRepository.updateEntity(clientEntity);
            return ClientCodeSaveEnum.CLIENT_UPDATED;
        }

        return ClientCodeSaveEnum.CLIENT_DONT_EXISTS;
    }

    @Transactional
    public PhoneClientCodeSaveEnum updatePhoneClient(PhoneDto dto){
        var phoneEntity = clientMapper.toEntity(dto);

        if(clientRepository.findPhoneByIdentityPhone(dto.getIdPhone()) != null){
            clientRepository.updateEntity(phoneEntity);
            return PhoneClientCodeSaveEnum.PHONE_NUMBER_UPDATED;
        }


        return PhoneClientCodeSaveEnum.PHONE_NUMBER_DONT_EXISTS;
    }
    
}
