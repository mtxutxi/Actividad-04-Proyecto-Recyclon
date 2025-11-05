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
@Table(name = "linea_pedido") //Nombre de la tabla en la BBDD
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LineaPedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Para que coincida el id de version incluso despues de agregar más campos a la clase
	
	
	@Id //Para marcar "id_linea_pedido" como PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para indicar que el id lo genera la BBDD
	@Column(
			name = "id_linea_pedido" //Nombre de la columna en la BBDD
	)
	@EqualsAndHashCode.Include
	private Integer idLineaPedido;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido") //Apuntamos al id de Pedido (ForeignKey)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_producto") //Apuntamos al id de Producto (ForeignKey)
	private Producto producto;
	
	private Integer cantidad;
}
