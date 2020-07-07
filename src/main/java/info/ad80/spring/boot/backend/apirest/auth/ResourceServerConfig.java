package info.ad80.spring.boot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

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
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes", "/api/clientes/page/**", "/api/uploads/img/**", "images/**").permitAll()
		.antMatchers("/api/clientes/{id}").permitAll()
		.antMatchers("/api/facturas/**").permitAll()
		/*	.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		.antMatchers("/api/clientes/**").hasRole("ADMIN") */
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		;
	}
	
	
	//CORS
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
		
	}
	
	
	//Lo registramos
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
	FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
	bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	return bean;

	}
	
}

