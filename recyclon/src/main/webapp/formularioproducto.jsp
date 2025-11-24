<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <title>${empty producto ? 'Nuevo' : 'Modificar'} Producto</title>
    <style>
        :root {
            --color-fondo: #EDE8DC;
            --verde-oscuro:#93C572;
            --verde-claro:rgba(147, 197, 114, 0.9);
        }

        body {
            background-color: var(--color-fondo);
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
            color: white;
        }

        .card-header-recyclon {
            background: linear-gradient(135deg, var(--verde-oscuro) 0%, var(--verde-claro) 100%);
            color: white;
        }
    </style>
</head>
<body>
    <!-- NAVBAR (IGUAL QUE LOS OTROS) -->
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
                  <c:if test="${empty sessionScope.usuario.isAdmin or not sessionScope.usuario.isAdmin}">
	                  <li class="nav-item">
	                      <a class="nav-link active text-light" href="productos?accion=vercarrito">Cesta <i class="bi bi-bag-fill"></i></a>
	                  </li>
                  </c:if>
              </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header card-header-recyclon">
                        <h3 class="mb-0">
                            <i class="bi bi-${empty producto ? 'plus' : 'pencil'}-circle"></i>
                            ${empty producto ? 'Nuevo Producto' : 'Modificar Producto'}
                        </h3>
                    </div>
                    
                    <div class="card-body">
                        <c:if test="${not empty mensaje.message}">
                            <div class="alert ${mensaje.role} alert-dismissible fade show">
                                ${mensaje.message}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            </div>
                        </c:if>

                        <form action="productos" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="accion" value="${empty producto ? 'productonuevo' : 'productoformulario'}">
                            <c:if test="${not empty producto}">
                                <input type="hidden" name="id" value="${producto.idProducto}">
                            </c:if>

                            <div class="mb-3">
                                <label for="nombre" class="form-label"><i class="bi bi-tag"></i> Nombre <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="nombre" name="nombre" 
                                       value="${producto.nombre}" placeholder="Ej: iPhone 13 Pro" required>
                            </div>

                            <div class="mb-3">
                                <label for="descripcion" class="form-label"><i class="bi bi-text-paragraph"></i> Descripción</label>
                                 <textarea class="form-control" id="descripcion" name="descripcion" 
                                          rows="4" placeholder="Describe el producto...">${producto.descripcion}</textarea>
                            </div>

                            <div class="mb-3">
                                <label for="categoria" class="form-label"><i class="bi bi-list-ul"></i> Categoría <span class="text-danger">*</span></label>
                                <select class="form-select" id="categoria" name="categoria" required>
                                    <option value="">Selecciona una categoría</option>
                                    <c:forEach var="cat" items="${categorias}">
                                        <option value="${cat.idCategoria}" <c:if test="${producto.categoria.idCategoria == cat.idCategoria}">selected</c:if>>
                                            ${cat.tipo}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="precio" class="form-label"><i class="bi bi-currency-euro"></i> Precio <span class="text-danger">*</span></label>
                                        <div class="input-group">
                                            <input type="number" class="form-control" id="precio" name="precio" 
                                                   step="0.01" min="0.01" value="${producto.precio}" 
                                                   placeholder="0.00" required>
                                            <span class="input-group-text">€</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="stock" class="form-label"><i class="bi bi-box-seam"></i> Stock <span class="text-danger">*</span></label>
                                        <input type="number" class="form-control" id="stock" name="stock" 
                                               min="0" value="${producto.stock}" placeholder="0" required>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="imagen" class="form-label"><i class="bi bi-image"></i> Imagen del Producto</label>
                                
                                <c:if test="${not empty producto.imagen}">
                                    <div class="mb-2">
                                        <img src="imagenes/${producto.imagen}" alt="Imagen actual" 
                                             class="img-thumbnail" style="max-height: 150px;">
                                        <p class="text-muted small mt-1">Imagen actual</p>
                                    </div>
                                </c:if>
                                
                                <input type="file" class="form-control" id="imagen" name="imagen" accept="image/*">
                                <div class="form-text">Formatos: JPG, PNG, GIF (máx. 5MB)</div>
                                <div id="preview-container" class="mt-2"></div>
                            </div>

                            <hr>

                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a href="productos" class="btn btn-secondary">
                                    <i class="bi bi-x-circle"></i> Cancelar
                                </a>
                                <button type="submit" class="btn btn-recyclon">
                                    <i class="bi bi-save"></i> 
                                    ${empty producto ? 'Crear Producto' : 'Guardar Cambios'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        // Vista previa de imagen
        document.getElementById('imagen').addEventListener('change', function(e) {
            const container = document.getElementById('preview-container');
            container.innerHTML = '';
            
            if (e.target.files && e.target.files[0]) {
                const reader = new FileReader();
                reader.onload = function(event) {
                    const preview = document.createElement('img');
                    preview.src = event.target.result;
                    preview.className = 'img-thumbnail';
                    preview.style.maxHeight = '150px';
                    
                    const label = document.createElement('p');
                    label.className = 'text-muted small mt-1';
                    label.textContent = 'Nueva imagen';
                    
                    container.appendChild(preview);
                    container.appendChild(label);
                };
                reader.readAsDataURL(e.target.files[0]);
            }
        });
    </script>
</body>
</html>