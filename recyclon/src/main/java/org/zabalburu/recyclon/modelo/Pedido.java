package org.zabalburu.recyclon.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity //Marcar como entidad JPA -> Aplicar Serializable
@Table(name = "pedido") //Nombre de la tabla en la BBDD
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //Para que coincida el id de version incluso despues de agregar más campos a la clase
	
	
	@Id //Para marcar "id_pedido" como PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para indicar que el id lo genera la BBDD
	@Column(
			name = "id_pedido" //Nombre de la columna en la BBDD
	)
	@EqualsAndHashCode.Include
	private Integer idPedido;

	private Date fecha;
	
	private String estado;
	
	@ManyToOne //N a 1
	@JoinColumn(name = "id_usuario") //Apuntamos al id de Cliente (ForeignKey)
	private Usuario usuario;
	 
	@OneToMany(mappedBy = "pedido") //Relacion 1 a N, mappedBy -> apuntamos a la relacion de LineaPedido
	private List<LineaPedido> lineasPedido;
}
