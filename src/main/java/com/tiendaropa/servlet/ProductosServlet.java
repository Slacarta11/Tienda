package com.tiendaropa.servlet;

import com.tiendaropa.dao.ProductoDao;
import com.tiendaropa.model.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productos")
public class ProductosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductoDao productoDao = new ProductoDao();

        // Parámetros para paginación
        int pagina = 1;
        int productosPorPagina = 8;

        try {
            if (request.getParameter("pagina") != null) {
                pagina = Integer.parseInt(request.getParameter("pagina"));
            }
        } catch (NumberFormatException e) {
            pagina = 1;
        }

        // Calcular el offset
        int offset = (pagina - 1) * productosPorPagina;

        // Obtener productos con paginación
        List<Producto> productos = productoDao.findAllPaginated(productosPorPagina, offset);

        // Obtener el total de productos para calcular páginas
        int totalProductos = productoDao.countAll();
        int totalPaginas = (int) Math.ceil((double) totalProductos / productosPorPagina);

        // Establecer atributos para la vista
        request.setAttribute("productos", productos);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPaginas);

        // Enviar a la vista
        request.getRequestDispatcher("/productos.jsp").forward(request, response);
    }
}