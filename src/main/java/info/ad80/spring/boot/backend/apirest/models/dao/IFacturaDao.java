package info.ad80.spring.boot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import info.ad80.spring.boot.backend.apirest.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
