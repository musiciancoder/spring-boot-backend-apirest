package info.ad80.spring.boot.backend.apirest.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.ad80.spring.boot.backend.apirest.models.entity.Cliente;
import info.ad80.spring.boot.backend.apirest.services.IClienteService;


@CrossOrigin(origins= {"http://localhost:4200"}) //CORS, para conectar con Angular
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List <Cliente> index() {
		return clienteService.findAll();
		
	}

}
