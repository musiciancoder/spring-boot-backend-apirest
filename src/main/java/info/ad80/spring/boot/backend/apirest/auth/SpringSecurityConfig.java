package info.ad80.spring.boot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration //indica que es una clase de configuracion 
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usuarioService; //UserDetailsService es la interfaz que implementa la clase UsuarioService

	@Bean //para registrar en el contenedor de springs (similar a <spring></spring) el objeto que retorna este metodo, para ser ocupado por medio de @Autowired en otras clases
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}
	
	
	
}
