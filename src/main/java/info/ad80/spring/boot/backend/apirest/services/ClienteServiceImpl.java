package info.ad80.spring.boot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.ad80.spring.boot.backend.apirest.models.dao.IClienteDao;
import info.ad80.spring.boot.backend.apirest.models.dao.IFacturaDao;
import info.ad80.spring.boot.backend.apirest.models.entity.Cliente;
import info.ad80.spring.boot.backend.apirest.models.entity.Factura;
import info.ad80.spring.boot.backend.apirest.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IFacturaDao facturaDao;

	@Override
	@Transactional(readOnly= true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly= true)
	public Page<Cliente> findAll(Pageable pageable) {
		
		return clienteDao.findAll(pageable);
		
	}

	@Override
	@Transactional(readOnly= true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);//si encuentra el cliente que lo envie, sino que retorne null
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente) ;
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Region> findAllRegiones() {
		
		return clienteDao.findAllRegiones();
	}

	@Override
	@Transactional
	public Factura findFacturaByid(Long id) {
		
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
		
	}


	


}
