<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Productos - Tienda de Ropa</title>
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
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            background-color: #333;
            color: white;
            border: none;
            cursor: pointer;
        }
        .search-form {
            margin-bottom: 30px;
            padding: 15px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
        }
        .results {
            margin-top: 20px;
        }
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: center;
        }
        .product-card img {
            max-width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .view-btn {
            display: inline-block;
            padding: 5px 10px;
            background-color: #428bca;
            color: white;
            text-decoration: none;
            border-radius: 3px;
            margin-top: 10px;
        }
        .no-results {
            padding: 20px;
            background-color: #f2f2f2;
            text-align: center;
            margin-top: 20px;
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
        <h2>Buscar Productos</h2>

        <div class="search-form">
            <form action="buscar-productos" method="get">
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" value="${nombre}">
                </div>

                <div class="form-group">
                    <label for="categoriaId">Categoría</label>
                    <select id="categoriaId" name="categoriaId">
                        <option value="">-- Todas las categorías --</option>
                        <c:forEach items="${categorias}" var="categoria">
                            <option value="${categoria.id}" <c:if test="${categoriaId == categoria.id}">selected</c:if>>
                                ${categoria.nombre}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="marcaId">Marca</label>
                    <select id="marcaId" name="marcaId">
                        <option value="">-- Todas las marcas --</option>
                        <c:forEach items="${marcas}" var="marca">
                            <option value="${marca.id}" <c:if test="${marcaId == marca.id}">selected</c:if>>
                                ${marca.nombre}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="precioMin">Precio mínimo (€)</label>
                    <input type="number" id="precioMin" name="precioMin" min="0" step="0.01" value="${precioMin}">
                </div>

                <div class="form-group">
                    <label for="precioMax">Precio máximo (€)</label>
                    <input type="number" id="precioMax" name="precioMax" min="0" step="0.01" value="${precioMax}">
                </div>

                <input type="hidden" name="buscar" value="true">
                <button type="submit" class="btn">Buscar</button>
            </form>
        </div>

        <c:if test="${resultados != null}">
            <div class="results">
                <h3>Resultados de la búsqueda</h3>

                <c:choose>
                    <c:when test="${empty resultados}">
                        <div class="no-results">
                            No se encontraron productos que coincidan con los criterios de búsqueda.
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="product-grid">
                            <c:forEach items="${resultados}" var="producto">
                                <div class="product-card">
                                    <c:choose>
                                        <c:when test="${not empty producto.imagen}">
                                            <img src="${producto.imagen}" alt="${producto.nombre}">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="https://via.placeholder.com/150" alt="Sin imagen">
                                        </c:otherwise>
                                    </c:choose>
                                    <h3>${producto.nombre}</h3>
                                    <p>${producto.precio} €</p>
                                    <a href="producto-detalle?id=${producto.id}" class="view-btn">Ver detalles</a>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
    </div>
</body>
</html>