<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${producto.id > 0}">Editar Producto</c:when>
            <c:otherwise>Nuevo Producto</c:otherwise>
        </c:choose> - Tienda de Ropa
    </title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #333;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        nav ul {
            list-style-type: none;
            padding: 0;
            display: flex;
            justify-content: center;
            background-color: #444;
        }
        nav ul li {
            padding: 10px 20px;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            margin-right: 10px;
            background-color: #333;
            color: white;
            border: none;
            cursor: pointer;
            text-decoration: none;
        }
        .btn-back {
            background-color: #f0ad4e;
        }
        .required {
            color: red;
        }
        .error-message {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Tienda de Ropa</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Inicio</a></li>
                <li><a href="productos">Productos</a></li>
                <li><a href="categorias">Categorías</a></li>
                <li><a href="marcas">Marcas</a></li>
                <li><a href="buscar-productos">Buscar</a></li>
                <c:choose>
                    <c:when test="${sessionScope.usuario != null}">
                        <li><a href="perfil">Mi Perfil</a></li>
                        <li><a href="logout">Cerrar Sesión</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="login">Iniciar Sesión</a></li>
                        <li><a href="registro">Registrarse</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </header>

    <div class="container">
        <a href="productos" class="btn btn-back">Volver a Productos</a>

        <h2>
            <c:choose>
                <c:when test="${producto.id > 0}">Editar Producto</c:when>
                <c:otherwise>Nuevo Producto</c:otherwise>
            </c:choose>
        </h2>

        <c:if test="${error != null}">
            <div class="error-message">
                ${error}
            </div>
        </c:if>

        <form action="producto-form" method="post" enctype="multipart/form-data">
            <c:if test="${producto.id > 0}">
                <input type="hidden" name="id" value="${producto.id}">
            </c:if>

            <div class="form-group">
                <label for="nombre">Nombre <span class="required">*</span></label>
                <input type="text" id="nombre" name="nombre" value="${producto.nombre}" required>
            </div>

            <div class="form-group">
                <label for="precio">Precio (€) <span class="required">*</span></label>
                <input type="number" id="precio" name="precio" value="${producto.precio}" min="0.01" step="0.01" required>
            </div>

            <div class="form-group">
                <label for="stock">Stock <span class="required">*</span></label>
                <input type="number" id="stock" name="stock" value="${producto.stock}" min="0" required>
            </div>

            <div class="form-group">
                <label for="categoriaId">Categoría</label>
                <select id="categoriaId" name="categoriaId">
                    <option value="">-- Selecciona una categoría --</option>
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.id}"
                            <c:if test="${producto.categoriaId == categoria.id}">selected</c:if>>
                            ${categoria.nombre}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="marcaId">Marca</label>
                <select id="marcaId" name="marcaId">
                    <option value="">-- Selecciona una marca --</option>
                    <c:forEach items="${marcas}" var="marca">
                        <option value="${marca.id}"
                            <c:if test="${producto.marcaId == marca.id}">selected</c:if>>
                            ${marca.nombre}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="imagen">Imagen</label>
                <c:if test="${not empty producto.imagen}">
                    <p>Imagen actual: ${producto.imagen}</p>
                </c:if>
                <input type="file" id="imagen" name="imagen" accept="image/*">
            </div>

            <button type="submit" class="btn">Guardar</button>
        </form>
    </div>
</body>
</html>