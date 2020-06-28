package info.ad80.spring.boot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="roles")
public class Role implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=20) //@Column es para dar configuracion extra a la tabla
	private String name;

	/*
	 // SI QUISIERAMOS TENER LOS ROLES CON RESPECTO A USUARIOS, NO SE HIZO PORQUE EN ESTA APP NO ES NECESARIO, SOLO QUEREMOS OBTENER LOS ROLES DE CADA USUARIO, NO AL REVES
	@ManyToMany(mappedBy="roles")
	private List<Usuario> usuarios;
	*/
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
