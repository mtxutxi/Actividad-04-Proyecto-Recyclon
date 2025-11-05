package org.zabalburu.recyclon.modelo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity //Marcar como entidad JPA -> Aplicar Serializable
@Table(name = "usuario") //Nombre de la tabla en la BBDD
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Para que coincida el id de version incluso despues de agregar más campos a la clase
	
	
	@Id //Para marcar "id_cliente" como PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para indicar que el id lo genera la BBDD
	@Column(
			name = "id_usuario" //Nombre de la columna en la BBDD
	)
	@EqualsAndHashCode.Include
	private Integer idUsuario;
	
	@Column(
			columnDefinition = "varchar(100)" //Para asignar limite de 100 caracteres
		)
	private String nombre;
	
	private String apellidos;
	
	@Column(
		name = "is_admin"
	)
	private Boolean isAdmin;
	
	@OneToMany(mappedBy = "usuario") //Relacion 1 a N, mappedBy -> apuntamos a la relacion de Pedido
	private List<Pedido> pedidos;

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
}
