package info.ad80.spring.boot.backend.apirest.controlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import info.ad80.spring.boot.backend.apirest.models.entity.Cliente;
import info.ad80.spring.boot.backend.apirest.services.IClienteService;

@CrossOrigin(origins = { "http://localhost:4200" }) // CORS, para conectar con Angular
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<Cliente> index() {

		return clienteService.findAll();

	}

	// Mostrar cliente por Id
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Cliente cliente = null;

		Map<String, Object> response = new HashMap<>(); // clave, valor

		try {

			cliente = clienteService.findById(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la BBDD")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// Crear un cliente
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente) { // RequestBody pide un cliente en el cuerpo de la
																	// peticion
		// frontend, que viene en formato json

		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>(); // clave, valor

		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear cliente en la BBDD");  //esto lo recibe el frontend como json.mensaje
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); //esto lo recibe el frontend
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El cliente ha sido creado con éxito!");  //esto lo recibe el frontend como json.mensaje
		response.put("cliente", clienteNew);  //esto lo recibe el frontend como json.cliente
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Editar cliente
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {

		
		Cliente clienteActual = clienteService.findById(id);
		
		Cliente clienteUpdated= null;
		Map<String, Object> response = new HashMap<>(); // clave, valor
		
		if (clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar el cliente ID: ".concat(id.toString().concat(" no existe en la BBDD")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setEmail(cliente.getEmail());
		clienteActual.setCreateAt(cliente.getCreateAt());

		clienteUpdated = clienteService.save(clienteActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
		
		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		
		
	}

	// Borrar cliente
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>(); // clave, valor
		
		try {
		
			clienteService.delete(id);
	} catch (DataAccessException e) {
		response.put("mensaje", "Error al eliminar el cliente en la BBDD");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

	}
		response.put("mensaje", "El cliente ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
}
}
