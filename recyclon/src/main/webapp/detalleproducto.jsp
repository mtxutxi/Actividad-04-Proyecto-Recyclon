<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <script src="https://kit.fontawesome.com/f21170cf61.js" crossorigin="anonymous"></script>
    <title>${producto.nombre}</title>
    <style>
        :root {
            --color-fondo: #EDE8DC;
            --verde-oscuro:#93C572;
            --verde-claro:rgba(147, 197, 114, 0.9);
        }

        body {
            background-color: var(--color-fondo);
            margin: 0;
            padding: 0;
            padding-top: 70px;
        }

        .navbar {
            background-color: var(--verde-oscuro);
        }

        .btn-recyclon {
            background-color: var(--verde-oscuro);
            border-color: var(--verde-oscuro);
            color: white;
        }

        .btn-recyclon:hover {
            background-color: #7fb05e;
            border-color: #7fb05e;
            color: white;
        }

        .producto-imagen {
            max-height: 500px;
            object-fit: contain;
            width: 100%;
        }
    </style>
</head>
<body>
    <!-- BARRA DE NAVEGACION  -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand text-light ms-3" href="index.jsp">
                <i class="bi bi-leaf"></i> <span>Recyclon </span>
            </a>
            <button class="navbar-toggler bg-light bg-opacity-75" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                  	  <a class="nav-link text-light" href="index.jsp">Inicio <i class="bi bi-house-door-fill"></i></a>
                  </li>
                  <li class="nav-item">
                  	  <a class="nav-link text-light" href="controladorrecyclon">Usuario <i class="bi bi-person-fill"></i></a>
                  </li>
                  <li class="nav-item">
                  	  <a class="nav-link active text-light fw-bold" aria-current="page" href="productos">Catálogo <i class="bi bi-book-fill"></i></a>
                  </li>
                  <li class="nav-item">
                  	  <a class="nav-link text-light" href="pedidos">Pedidos <i class="bi bi-box-seam-fill"></i></a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link active text-light" href="cesta">Cesta <i class="bi bi-bag-fill"></i></a>
                  </li>
              </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="productos">Productos</a></li>
                <li class="breadcrumb-item"><a href="productos?accion=getcategoriaproducto&id=${producto.categoria.idCategoria}">${producto.categoria.tipo}</a></li>
                <li class="breadcrumb-item active">${producto.nombre}</li>
            </ol>
        </nav>

        <div class="row">
            <!-- Imagen del Producto -->
            <div class="col-md-6 mb-4">
                <div class="card shadow">
                    <c:choose>
                        <c:when test="${not empty producto.imagen}">
                            <img src="imagenes/${producto.imagen}" class="card-img producto-imagen" alt="${producto.nombre}">
                        </c:when>
                        <c:otherwise>
                            <div class="bg-secondary d-flex align-items-center justify-content-center" style="height: 500px;">
                                <i class="bi bi-image text-white" style="font-size: 5rem;"></i>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <!-- Información del Producto -->
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-body">
                        <span class="badge bg-success mb-2">${producto.categoria.tipo}</span>
                        <h2 class="card-title">${producto.nombre}</h2>
                        
                        <h3 class="text-success my-4">
                            <fmt:formatNumber value="${producto.precio}" type="currency" currencySymbol="€"/>
                        </h3>

                        <div class="mb-4">
                            <h5><i class="bi bi-text-paragraph"></i> Descripción</h5>
                            <p class="text-muted">${producto.descripcion}</p>
                        </div>

                        <div class="mb-4">
                            <h5><i class="bi bi-box-seam"></i> Disponibilidad</h5>
                            <c:choose>
                                <c:when test="${producto.stock > 0}">
                                    <span class="badge bg-success fs-6">
                                        <i class="bi bi-check-circle"></i> En stock (${producto.stock} unidades)
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-danger fs-6">
                                        <i class="bi bi-x-circle"></i> Agotado
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <hr>

                        <!-- Botones -->
                        <div class="d-grid gap-2">
                            <c:if test="${producto.stock > 0}">
                                <button class="btn btn-recyclon btn-lg">
                                    <i class="bi bi-cart-plus"></i> Añadir al Carrito
                                </button>
                            </c:if>

                            <c:if test="${sessionScope.usuario != null && sessionScope.usuario.isAdmin}">
                                <a href="productos?accion=modificarproducto&id=${producto.idProducto}" class="btn btn-warning">
                                    <i class="bi bi-pencil"></i> Modificar Producto
                                </a>
                                <a href="productos?accion=eliminarproducto&id=${producto.idProducto}" 
                                   class="btn btn-danger"
                                   onclick="return confirm('¿Eliminar este producto?')">
                                    <i class="bi bi-trash"></i> Eliminar Producto
                                </a>
                            </c:if>

                            <a href="productos" class="btn btn-outline-secondary">
                                <i class="bi bi-arrow-left"></i> Volver al Catálogo
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>