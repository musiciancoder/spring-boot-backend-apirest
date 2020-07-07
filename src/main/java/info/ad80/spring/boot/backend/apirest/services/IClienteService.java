package info.ad80.spring.boot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import info.ad80.spring.boot.backend.apirest.models.entity.Cliente;
import info.ad80.spring.boot.backend.apirest.models.entity.Factura;
import info.ad80.spring.boot.backend.apirest.models.entity.Region;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente findById(Long id);
	
	public Cliente save (Cliente cliente);
	
	public void delete (Long id);
	
	public List<Region>findAllRegiones();
	
	public Factura findFacturaByid(Long id);
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(Long id);
	
	
	
	
		
	
}

	