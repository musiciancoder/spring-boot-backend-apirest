package info.ad80.spring.boot.backend.apirest.controlers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import info.ad80.spring.boot.backend.apirest.models.entity.Cliente;
import info.ad80.spring.boot.backend.apirest.services.IClienteService;

@CrossOrigin(origins = { "http://localhost:4200" }) // CORS, para conectar con Angular
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

	@GetMapping("/clientes")
	public List<Cliente> index() {

		return clienteService.findAll();

	}

	// PAGINABLE EL JSON QUE ENTREGA EN REST ES CONTENT Y LUEGO EL ARRAY DE
	// CLIENTES, POR LO QUE SE DEBE MODIFICAR EL FRONTEND PARA INCLUIR CONTENT
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);

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
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) { // RequestBody pide un
																									// cliente en el
																									// cuerpo de la
		// peticion
		// frontend, que viene en formato json

		Cliente clienteNew = null;

		Map<String, Object> response = new HashMap<>(); // clave, valor

		if (result.hasErrors()) {

			/*
			 * List<String>errors = new ArrayList<>(); for (FieldError err:
			 * result.getFieldErrors()) { errors.add("El campo '" + err.getField() + "' " +
			 * err.getDefaultMessage()); }
			 */

			// ESTO ES LO MISMO QUE EL CODIGO ANTERIORMENTE COMENTADO
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())

					.collect(Collectors.toList());

			response.put("errors", errors); // en metodo create y update en form.component.ts esto es llamado como
											// err.error.errors
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la BBDD"); // llamado en el frontend en metod
																					// create() en la clase de servicio
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); // llamado
																												// en el
																												// frontend
																												// en
																												// metod
																												// create()
																												// en la
																												// clase
																												// de
																												// servicio
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	// Editar cliente
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

		Cliente clienteActual = clienteService.findById(id);

		Cliente clienteUpdated = null;
		
		Map<String, Object> response = new HashMap<>(); // clave, valor

		if (result.hasErrors()) {

			/*
			 * List<String>errors = new ArrayList<>(); for (FieldError err:
			 * result.getFieldErrors()) { errors.add("El campo '" + err.getField() + "' " +
			 * err.getDefaultMessage()); }
			 */

			// ESTO ES LO MISMO QUE EL CODIGO ANTERIORMENTE COMENTADO
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())

					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (clienteActual == null) {
			response.put("mensaje",
					"Error: no se pudo editar el cliente ID: ".concat(id.toString().concat(" no existe en la BBDD")));
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
			
			Cliente cliente = clienteService.findById(id);
			String nombreFotoAnterior = cliente.getFoto();
			
			if (nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists()&&archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
					
				}
				
			}

			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El cliente ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	//SUBIR IMAGEN
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Cliente cliente = clienteService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString()  + archivo.getOriginalFilename().replace(" ", "_"); // nombreArchivo es el nombre del archivo en la app,
																	// con getOriginalFileName() obtenemos el nombre que
																	// le da el usuario. UUID.randomUUID().toString() son caracteres al azar
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath(); // rutaArchivo es la ruta// donde se guarda en la
																								// aplicacion

			log.info(rutaArchivo.toString()); //para informar explicitamente la ruta en el log de Spring (en la consola cuando parte la aplicacion)
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				e.printStackTrace();
				response.put("mensaje", "Error al subir la imagen del cliente " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = cliente.getFoto();
			
			if (nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if (archivoFotoAnterior.exists()&&archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
					
				}	
			}

			cliente.setFoto(nombreArchivo);

			clienteService.save(cliente);

			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	//PARA DESCARGAR IMAGEN EN EL NAVEGADOR (se prueba con http://localhost:8080/api/uploads/img/nombreCodificadoDeLaFoto
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString()); //para informar explicitamente la ruta
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists()&& !recurso.isReadable()) {
			 rutaArchivo = Paths.get("src/main/resources/static/images").resolve("anonimo.png").toAbsolutePath();

				try {
					recurso = new UrlResource(rutaArchivo.toUri());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				
				}
			 
			log.error("Error; no se pudo cargar la imagen " + nombreFoto);
		}
				
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		
		return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
		
	}
	
}

