package org.zabalburu.recyclon.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	
	private Integer telefono;
	
	@Column(
			name = "f_nacimiento" //Nombre de la columna en la BBDD
	)
	@Temporal(TemporalType.DATE) //Para que solo guarde la fecha (En SQL Server también se guarda la hora)
	private Date fechaNacimiento;
	
	@Column(
			name = "dni" //Nombre de la columna en la BBDD
	)
	private String documentoIdentidad;
	
	private String email;
	
	@Column(
			name = "contrasena_hash"
	)
	private String contrasenaHash;
	
	@Column(
			name = "dir_envio" //Nombre de la columna en la BBDD
	)
	private String direccionEnvio;
	
	@Column(
			name = "dir_facturacion" //Nombre de la columna en la BBDD
	)
	private String direccionFacturacion;
	
	@Column(
		name = "is_admin"
	)
	private Boolean isAdmin;
	
	@OneToMany(mappedBy = "usuario") //Relacion 1 a N, mappedBy -> apuntamos a la relacion de Pedido
	private List<Pedido> pedidos;
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono="
				+ telefono + ", fechaNacimiento=" + fechaNacimiento + ", documentoIdentidad=" + documentoIdentidad
				+ ", email=" + email + ", direccionEnvio=" + direccionEnvio + ", direccionFacturacion="
				+ direccionFacturacion + ", isAdmin=" + isAdmin + "]";
	}
}
