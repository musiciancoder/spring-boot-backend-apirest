package info.ad80.spring.boot.backend.apirest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.ad80.spring.boot.backend.apirest.models.dao.IUsuarioDao;
import info.ad80.spring.boot.backend.apirest.models.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	@Override
	@Transactional(readOnly = true)
	//TODO DONDE ESTA OCUPANDO ESTE METODO?
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario==null) {
			logger.error("Error en el login: no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + username + "' en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()  //esto significa que los roles son pasados en forma secuencial (síncrono)
				.map(role -> new SimpleGrantedAuthority(role.getNombre())) //pasa el nombre del rol a SimpleGrantedAuthority
				.peek(authority -> logger.info("Role: " + authority.getAuthority())) //para que muestre en consola cada rol
				.collect(Collectors.toList());//convierte todos los SimpleGrantedAuthority a una lista.
	
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities); //user hereda de userdetails
	
	
	
	}
	

	

		
	

}
