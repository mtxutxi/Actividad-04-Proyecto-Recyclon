package org.zabalburu.recyclon.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity //Marcar como entidad JPA -> Aplicar Serializable
@Table(name = "producto") //Nombre de la tabla en la BBDD
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Para que coincida el id de version incluso despues de agregar más campos a la clase
	
	
	@Id //Para marcar "id_producto" como PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para indicar que el id lo genera la BBDD
	@Column(
			name = "id_producto" //Nombre de la columna en la BBDD
	)
	@EqualsAndHashCode.Include
	private Integer idProducto;
	
	@Column(
			columnDefinition = "varchar(100)" //Para asignar limite de 100 caracteres
		)
	private String nombre;
	
	private String descripcion;
	
	private Double precio;
	
	private Integer stock;
	
	@ManyToOne //N a 1
	@JoinColumn(name = "id_categoria") //Apuntamos al id de Categoria (ForeignKey)
	private Categoria categoria;	
}
