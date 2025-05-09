<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${producto.nombre} - Tienda de Ropa</title>
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
        .product-detail {
            display: flex;
            margin-top: 20px;
        }
        .product-image {
            flex: 1;
            padding-right: 20px;
        }
        .product-image img {
            max-width: 100%;
            height: auto;
        }
        .product-info {
            flex: 2;
        }
        .product-price {
            font-size: 24px;
            color: #333;
            margin: 10px 0;
        }
        .product-actions {
            margin-top: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            margin-right: 10px;
            text-decoration: none;
            color: white;
            border-radius: 3px;
        }
        .btn-primary {
            background-color: #428bca;
        }
        .btn-success {
            background-color: #5cb85c;
        }
        .btn-danger {
            background-color: #d9534f;
        }
        .btn-back {
            background-color: #f0ad4e;
        }
        .product-meta {
            margin-top: 20px;
            color: #777;
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

        <div class="product-detail">
            <div class="product-image">
                <c:choose>
                    <c:when test="${not empty producto.imagen}">
                        <img src="${producto.imagen}" alt="${producto.nombre}">
                    </c:when>
                    <c:otherwise>
                        <img src="https://via.placeholder.com/300" alt="Sin imagen">
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="product-info">
                <h1>${producto.nombre}</h1>
                <div class="product-price">${producto.precio} €</div>

                <p>
                    <c:if test="${producto.stock > 0}">
                        <span style="color: green;">En stock: ${producto.stock} unidades</span>
                    </c:if>
                    <c:if test="${producto.stock <= 0}">
                        <span style="color: red;">Agotado</span>
                    </c:if>
                </p>

                <c:if test="${producto.categoria != null}">
                    <p><strong>Categoría:</strong> ${producto.categoria.nombre}</p>
                </c:if>

                <c:if test="${producto.marca != null}">
                    <p><strong>Marca:</strong> ${producto.marca.nombre}</p>
                </c:if>

                <div class="product-actions">
                    <c:if test="${sessionScope.usuario != null && producto.stock > 0}">
                        <a href="realizar-compra?id=${producto.id}" class="btn btn-primary">Comprar</a>
                    </c:if>

                    <c:if test="${sessionScope.usuarioRol == 'admin'}">
                        <a href="producto-form?id=${producto.id}" class="btn btn-success">Editar</a>
                        <a href="eliminar-producto?id=${producto.id}" class="btn btn-danger"
                           onclick="return confirm('¿Estás seguro de eliminar este producto?')">Eliminar</a>
                    </c:if>
                </div>

                <div class="product-meta">
                    <p>Fecha de alta: ${producto.fechaAlta}</p>
                    <p>Referencia: PRD-${producto.id}</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>