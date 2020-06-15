package info.ad80.spring.boot.backend.apirest.services;

import java.util.List;

import info.ad80.spring.boot.backend.apirest.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save (Cliente cliente);
	
	public void delete (Long id);
	
	
		
	
}

	