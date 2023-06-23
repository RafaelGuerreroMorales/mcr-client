package micro.spv.clientes.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import micro.spv.clientes.dtos.ClientDto;
import micro.spv.clientes.dtos.PhoneDto;
import micro.spv.clientes.dtos.ResponseDto;
import micro.spv.clientes.services.ClientService;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping
    @ResponseBody
    public ResponseDto<ClientDto> getClientByIdClient(@RequestParam("id") int clientid){
        try {
            return new ResponseDto<>((short) 0, "Oks",clientService.getClientById(clientid));
        } catch (Exception ex) {
            logger.error("Ups ocurrio una exepcion", ex);
        }
        return new ResponseDto<>((short) -1, "Ups Ocurrio un problema", null);
    }

    @GetMapping("/fullname")
    @ResponseBody
    public ResponseDto<ArrayList<ClientDto>> getClientByFullName(@RequestParam("firstname") String firstName,
        @RequestParam("secondname") String secondName, @RequestParam("firstlastname") String fisrtLastName,
        @RequestParam("secondlastname") String secondLastName){
        try {
            var arrayClient = new ArrayList<ClientDto>();
            var clients = clientService.getClientByFullName(firstName.toUpperCase(), secondName.toUpperCase(), 
                fisrtLastName.toUpperCase(), secondLastName.toUpperCase());
            arrayClient.addAll(clients);
            return new ResponseDto<>((short) 0, "Oks",arrayClient);
        } catch (Exception e) {
            return new ResponseDto<>((short) -1, "Ups Ocurrio un problema", null);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseDto<String> addClient(@RequestBody ClientDto dto){
        try {
           return new ResponseDto<>((short) 0, "Oks", clientService.saveClient(dto).toString());
        } catch (Exception ex) {
            logger.error("Ups ocurrio una exepcion", ex);
        }
        return new ResponseDto<>((short) -1, "Ups Ocurrio un problema", null);
    }

    @PostMapping("/phone")
    @ResponseBody
    public ResponseDto<String> addPhoneClient(@RequestBody PhoneDto dto){
        try {
            return new ResponseDto<>((short)0, "Oks", clientService.savePhoneNumber(dto).toString());
        } catch (Exception e) {
            return new ResponseDto<>((short) -1, "Ups Ocurrio un problema", null);
        }
    }

    @PutMapping
    @ResponseBody
    public ResponseDto<String> updateClient(@RequestBody ClientDto dto){
         try {
           return new ResponseDto<>((short) 0, "Oks", clientService.updateClient(dto).toString());
        } catch (Exception ex) {
            logger.error("Ups ocurrio una exepcion", ex);
        }
        return new ResponseDto<>((short) -1, "Ups Ocurrio un problema", null);
    }

    @PutMapping("/phone")
    @ResponseBody
    public ResponseDto<String> updatePhone(@RequestBody PhoneDto dto){
         try {
            return new ResponseDto<>((short)0, "Oks", clientService.updatePhoneClient(dto).toString());
        } catch (Exception e) {
            return new ResponseDto<>((short) -1, "Ups Ocurrio un problema", null);
        }
    }
}
