package info.ad80.spring.boot.backend.apirest.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
//PARA DAR PERMISO A LOS RECURSOS
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable() //desabilitamos, porque usamos token
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//deshabilitamos, porque es el lado del cliente (angular) el que maneja las sesiones a través del token
	}
	
	

}
