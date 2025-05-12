package com.tiendaropa.servlet;

import com.tiendaropa.dao.ProductoDao;
import com.tiendaropa.model.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/producto-detalle")
public class ProductoDetalleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/productos");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            ProductoDao productoDao = new ProductoDao();
            Producto producto = productoDao.findById(id);

            if (producto == null) {
                // Producto no encontrado
                response.sendRedirect(request.getContextPath() + "/productos");
                return;
            }

            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/producto-detalle.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/productos");
        }
    }
}