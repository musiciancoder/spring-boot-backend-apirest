package info.ad80.spring.boot.backend.apirest.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.ad80.spring.boot.backend.apirest.models.dao.IClienteDao;
import info.ad80.spring.boot.backend.apirest.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly= true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
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
	


}
