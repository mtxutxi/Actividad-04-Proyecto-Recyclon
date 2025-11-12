SELECT p.id_producto, p.nombre, p.descripcion, p.precio, p.stock, c.tipo
	FROM producto p, categoria c
	WHERE p.id_categoria = c.id_categoria;

SELECT * FROM usuario;