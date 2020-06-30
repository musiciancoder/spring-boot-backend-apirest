package info.ad80.spring.boot.backend.apirest.services;

import info.ad80.spring.boot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername (String username); //Consulta personalizada usando un nombre de método
	
}
