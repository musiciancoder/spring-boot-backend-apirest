package info.ad80.spring.boot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import info.ad80.spring.boot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	//select u from Usuario where u.username=?
	public Usuario findByUsername (String username); //Consulta personalizada usando un nombre de método

//	@Query("select u from Usuario where u.username=?1")
	//public Usuario findByUsername2 (String username); //Consulta personalizada usando query ( lo mismo que lo anterior, pero es  otra forma)

}
