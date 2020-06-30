package info.ad80.spring.boot.backend.apirest.auth;

/*import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
//PARA DAR PERMISO A LOS RECURSOS
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "api/clientes", "api/clientes/page/**", "api/upload/img/**" ).permitAll()  //Esto es publico
		.antMatchers(HttpMethod.GET, "api/clientes/{id}").hasAnyRole("USER","ADMIN") //Esto es privado (al igual que los que vienen en las lineas mas abajo) 
		.antMatchers(HttpMethod.POST, "api/clientes/upload").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST, "api/clientes").hasRole("ADMIN")
		.antMatchers("api/clientes/**").hasRole("ADMIN") //para todos los demas (put, delete, etc)
		.anyRequest().authenticated();
		
	}
	
	

}

*/

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**").permitAll()
		/*	.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		.antMatchers("/api/clientes/**").hasRole("ADMIN") */
		.anyRequest().authenticated();
	}

	
}

