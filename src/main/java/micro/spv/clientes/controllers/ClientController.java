package micro.spv.clientes.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import micro.spv.clientes.dtos.ClientDto;
import micro.spv.clientes.dtos.ResponseDto;
import micro.spv.clientes.services.ClientService;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    ClientService clientService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
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

    @GetMapping
    @ResponseBody
    public ResponseDto<ArrayList<ClientDto>> getAllClient(){
        try {
            ArrayList<ClientDto> clientList = new ArrayList<>();
            clientList.addAll(clientService.getAllClient());
            return new ResponseDto<>((short) 0, "Oks",clientList);
        } catch (Exception ex) {
            logger.error("Ups ocurrio una exepcion", ex);
        }
        return new ResponseDto<>((short) -1, "Ups Ocurrio un problema", null);
    }
}
