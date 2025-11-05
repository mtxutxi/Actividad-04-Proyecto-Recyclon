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
@Table(name = "categoria") //Nombre de la tabla en la BBDD
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Para que coincida el id de version incluso despues de agregar más campos a la clase
	
	
	@Id //Para marcar "id_categoria" como PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para indicar que el id lo genera la BBDD
	@Column(
			name = "id_categoria" //Nombre de la columna en la BBDD
	)
	@EqualsAndHashCode.Include
	private Integer idCategoria;
	
	@Column(
		columnDefinition = "varchar(100)" //Para asignar limite de 100 caracteres
	)
	private String tipo;
	
	@OneToMany(mappedBy = "categoria") //Relacion 1 a N, mappedBy -> apuntamos a la relacion de Producto
	private List<Producto> productos; //Para guardar todos los productos de cada categoria

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", tipo=" + tipo + "]";
	}
}
