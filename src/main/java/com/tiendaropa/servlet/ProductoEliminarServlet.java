package com.tiendaropa.servlet;

import com.tiendaropa.dao.ProductoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/eliminar-producto")
public class ProductoEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar si el usuario tiene permisos (debe ser admin)
        HttpSession session = request.getSession();
        String rol = (String) session.getAttribute("usuarioRol");

        if (rol == null || !rol.equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/productos");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            ProductoDao productoDao = new ProductoDao();

            // Eliminar el producto
            boolean eliminado = productoDao.delete(id);

            if (eliminado) {
                request.getSession().setAttribute("mensaje", "Producto eliminado correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar el producto");
            }

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "ID de producto inválido");
        }

        response.sendRedirect(request.getContextPath() + "/productos");
    }
}