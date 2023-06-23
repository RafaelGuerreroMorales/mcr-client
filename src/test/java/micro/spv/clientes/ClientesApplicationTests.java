package micro.spv.clientes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import jakarta.transaction.Transactional;
import micro.spv.clientes.services.ClientService;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ClientesApplicationTests {

	@Autowired
	ClientService clientService;
	
	@Test
	@Transactional
	void saveClientTest(){
		// var guardo = clientService.saveClient();
		// assertEquals(true, guardo);
	}

	

	

}
