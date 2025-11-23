<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="https://kit.fontawesome.com/f21170cf61.js" crossorigin="anonymous"></script>
    <title>Productos</title>
    <style>
        :root {
			--color-fondo: #EDE8DC;
			--verde-oscuro:#93C572 ;
			--verde-claro:rgba(147, 197, 114, 0.9);;
		}

		body{
		    background-color: var(--color-fondo);
		    margin:0 5px;
		    padding: 0;
		}
        
        .navbar{background-color: var(--verde-oscuro);}
    </style>
</head>
<body>
    <!-- BARRA DE NAVEGACION -->
    <%@ include file="navbar.jsp" %>

    <!-- CONTENIDO PRINCIPAL -->
    <div class="container-fluid px-4 mt-5 pt-3">
        
        <!-- Mensajes -->
        <c:if test="${not empty mensaje.message}">
            <div class="alert ${mensaje.role} alert-dismissible fade show mt-3" role="alert">
                <i class="bi bi-info-circle"></i> ${mensaje.message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <!-- Título y Buscador -->
        <div class="row mt-4 mb-4">
            <div class="col-md-6">
                <h1 class="text-recyclon">
                    <i class="bi bi-shop"></i> Catálogo de Productos
                </h1>
            </div>
            <div class="col-md-6">
                <form action="productos" method="get" class="input-group"><!-- Action manda los datos al servlet de productos. Con el get los parametros van en la URL -->
                    <input type="hidden" name="accion" value="buscarproducto">
                    <input type="text" name="producto" class="form-control" 
                           placeholder="Buscar productos...">
                    <button type="submit" class="btn btn-recyclon">
                        <i class="bi bi-search"></i> Buscar
                    </button>
                </form>
            </div>
        </div>

        <!-- PRODUCTOS POR CATEGORIA -->
		<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4"> <!-- Cuantas columnas por fila según el tamaño de la pantalla -->
    	<c:forEach var="producto" items="${productosporcategoria}">
        <div class="col">
            <div class="card shadow-sm position-relative border-0">
                <!-- Badge de Stock -->
                <span class="rounded-bottom-0 badge ${producto.stock > 0 ? 'bg-success' : 'bg-danger'}"> <%-- Si hay stock(verde) si no(rojo) --%>
                    <c:choose> <%-- if-else --%>
                        <c:when test="${producto.stock > 0}"> <%--Si hay mas de 0 preoductos --%>
                            Stock: ${producto.stock} <%--mostramos el numero de productos que hay --%>
                        </c:when>
                        <c:otherwise> <%-- Si no, mostramos agotado --%>
                            Agotado
                        </c:otherwise>
                    </c:choose>
                </span>

                <!-- Imagen usamos choose que equivale al if-else. Lo vamos a usar para que en caso de no haber imagen, muestre el tipico recuadro gris -->
                <c:choose>
                    <c:when test="${not empty producto.imagen}"> <%-- Esto es el if --%>
                        <img src="imagenes/${producto.imagen}" class="card-img-top rounded-top-0 " alt="${producto.nombre}">
                    </c:when>
                    <c:otherwise><%--Esto es el else --%>
                        <div class="card-img-top bg-secondary d-flex align-items-center justify-content-center rounded-top-0 " style="height: 200px;">
                            <i class="bi bi-image text-white" style="font-size: 3rem;"></i>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="card-body">
                    <h5 class="card-title text-truncate" title="${producto.nombre}">${producto.nombre}</h5>
                    <p class="card-text text-muted small" style="height: 60px; overflow: hidden;">${producto.descripcion}</p>
                    <span class="h4 mb-0 text-success fw-bold">
                        <fmt:formatNumber value="${producto.precio}" type="currency" currencySymbol="€"/>
                    </span>

                    <div class="d-grid gap-2 mt-2">
                        <a href="productos?accion=getproducto&id=${producto.idProducto}" class="btn btn-outline-success btn-sm"> <!-- Aqui va al producto del id -->
                            <i class="bi bi-eye"></i> Ver Detalles
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
        <!-- Si no hay productos -->
        
        <c:if test="${empty productosporcategoria}">
            <div class="alert alert-info text-center mt-5">
                <i class="bi bi-info-circle" style="font-size: 3rem;"></i>
                <h4 class="mt-3">No hay productos disponibles</h4>
                <p>Vuelve más tarde o añade productos nuevos(Necesitas ser Admin para eso)</p>
                <c:if test="${sessionScope.usuario != null && sessionScope.usuario.isAdmin}">
                    <a href="formularioproducto.jsp" class="btn btn-recyclon mt-2">
                        <i class="bi bi-plus-circle"></i> Añadir Primer Producto
                    </a>
                </c:if>
            </div>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>