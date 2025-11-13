SELECT lp.id_pedido, pr.nombre, lp.cantidad, u.nombre, u.apellidos
	FROM linea_pedido lp, pedido p, usuario u, producto pr
	WHERE lp.id_pedido = p.id_pedido AND p.id_usuario = u.id_usuario
	AND lp.id_producto = pr.id_producto;