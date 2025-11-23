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
    <nav class="navbar navbar-expand-lg fixed-top "> 
        <div class="container-fluid">
            <a class="navbar-brand text-light  ms-3" href="#"> 
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-leaf" viewBox="0 0 16 16">
  					<path d="M1.4 1.7c.216.289.65.84 1.725 1.274 1.093.44 2.884.774 5.834.528l.37-.023c1.823-.06 3.117.598 3.956 1.579C14.16 6.082 14.5 7.41 14.5 8.5c0 .58-.032 1.285-.229 1.997q.198.248.382.54c.756 1.2 1.19 2.563 1.348 3.966a1 1 0 0 1-1.98.198c-.13-.97-.397-1.913-.868-2.77C12.173 13.386 10.565 14 8 14c-1.854 0-3.32-.544-4.45-1.435-1.125-.887-1.89-2.095-2.391-3.383C.16 6.62.16 3.646.509 1.902L.73.806zm-.05 1.39c-.146 1.609-.008 3.809.74 5.728.457 1.17 1.13 2.213 2.079 2.961.942.744 2.185 1.22 3.83 1.221 2.588 0 3.91-.66 4.609-1.445-1.789-2.46-4.121-1.213-6.342-2.68-.74-.488-1.735-1.323-1.844-2.308-.023-.214.237-.274.38-.112 1.4 1.6 3.573 1.757 5.59 2.045 1.227.215 2.21.526 3.033 1.158.058-.39.075-.782.075-1.158 0-.91-.288-1.988-.975-2.792-.626-.732-1.622-1.281-3.167-1.229l-.316.02c-3.05.253-5.01-.08-6.291-.598a5.3 5.3 0 0 1-1.4-.811"/>
				</svg> 
                <span>Recyclon </span>
            </a> 
            <button class="navbar-toggler bg-light bg-opacity-75" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ms-auto mb-2 mb-lg-0"> <!--ms-auto para desplazar-->
                  <li class="nav-item">
                      <a class="nav-link text-light"  href="index.jsp">Inicio</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="controladorrecyclon">Usuario</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light fw-bold" aria-current="page" href="productos">Productos</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link text-light" href="pedidos">Pedidos</a>
                  </li>
              </ul>  
              
          </div>
        </div>
    </nav>
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
            <div class="card shadow-sm position-relative">
                <!-- Badge de Stock -->
                <span class="badge ${producto.stock > 0 ? 'bg-success' : 'bg-danger'} rounded-bottom-0"> <%-- Si hay stock(verde) si no(rojo) --%>
                    <c:choose> <%-- if-else --%>
                        <c:when test="${producto.stock > 0}"> <%--Si hay mas de 0 preoductos --%>
                            Stock: ${producto.stock} <<%--mostramos el numero de productos que hay --%>
                        </c:when>
                        <c:otherwise> <%-- Si no, mostramos agotado --%>
                            Agotado
                        </c:otherwise>
                    </c:choose>
                </span>

                <!-- Imagen usamos choose que equivale al if-else. Lo vamos a usar para que en caso de no haber imagen, muestre el tipico recuadro gris -->
                <c:choose>
                    <c:when test="${not empty producto.imagen}"> <%-- Esto es el if --%>
                        <img src="imagenes/${producto.imagen}" class="card-img-top" alt="${producto.nombre}">
                    </c:when>
                    <c:otherwise><%--Esto es el else --%>
                        <div class="card-img-top bg-secondary d-flex align-items-center justify-content-center  rounded-top-0" style="height: 200px;">
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