package info.ad80.spring.boot.backend.apirest.controlers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	//Mostrar cliente por Id
	@GetMapping("/clientes/{id}")
	public Cliente show (@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	//Crear un cliente
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED) //por defecto si no se pone nada es HttpStatus.OK, esto se pone para señalar que ha sido creado (no es obligatorio)
	public Cliente create(@RequestBody Cliente cliente ) { //RequestBody pide un cliente en el cuerpo de la peticion frontend, que viene en formato json 
		
		return clienteService.save(cliente);
		
	}

}
