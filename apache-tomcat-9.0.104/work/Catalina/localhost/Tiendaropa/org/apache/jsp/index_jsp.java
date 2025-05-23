/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.104
 * Generated at: 2025-05-09 23:41:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>Tienda de Ropa</title>\r\n");
      out.write("    <style>\r\n");
      out.write("        body {\r\n");
      out.write("            font-family: Arial, sans-serif;\r\n");
      out.write("            margin: 0;\r\n");
      out.write("            padding: 0;\r\n");
      out.write("            background-color: #f4f4f4;\r\n");
      out.write("        }\r\n");
      out.write("        header {\r\n");
      out.write("            background-color: #333;\r\n");
      out.write("            color: white;\r\n");
      out.write("            padding: 10px 20px;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("        }\r\n");
      out.write("        .container {\r\n");
      out.write("            width: 80%;\r\n");
      out.write("            margin: 20px auto;\r\n");
      out.write("            background-color: white;\r\n");
      out.write("            padding: 20px;\r\n");
      out.write("            box-shadow: 0 0 10px rgba(0,0,0,0.1);\r\n");
      out.write("        }\r\n");
      out.write("        .product-grid {\r\n");
      out.write("            display: grid;\r\n");
      out.write("            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));\r\n");
      out.write("            gap: 20px;\r\n");
      out.write("            margin-top: 20px;\r\n");
      out.write("        }\r\n");
      out.write("        .product-card {\r\n");
      out.write("            border: 1px solid #ddd;\r\n");
      out.write("            padding: 15px;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("        }\r\n");
      out.write("        .product-card img {\r\n");
      out.write("            max-width: 100%;\r\n");
      out.write("            height: auto;\r\n");
      out.write("        }\r\n");
      out.write("        nav ul {\r\n");
      out.write("            list-style-type: none;\r\n");
      out.write("            padding: 0;\r\n");
      out.write("            display: flex;\r\n");
      out.write("            justify-content: center;\r\n");
      out.write("            background-color: #444;\r\n");
      out.write("        }\r\n");
      out.write("        nav ul li {\r\n");
      out.write("            padding: 10px 20px;\r\n");
      out.write("        }\r\n");
      out.write("        nav ul li a {\r\n");
      out.write("            color: white;\r\n");
      out.write("            text-decoration: none;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <header>\r\n");
      out.write("        <h1>Tienda de Ropa</h1>\r\n");
      out.write("        <nav>\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li><a href=\"index.jsp\">Inicio</a></li>\r\n");
      out.write("                <li><a href=\"productos\">Productos</a></li>\r\n");
      out.write("                <li><a href=\"categorias\">Categorías</a></li>\r\n");
      out.write("                <li><a href=\"login\">Iniciar Sesión</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </nav>\r\n");
      out.write("    </header>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <h2>Bienvenido a nuestra tienda de ropa</h2>\r\n");
      out.write("        <p>Explora nuestra colección de moda y encuentra las últimas tendencias.</p>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"product-grid\">\r\n");
      out.write("            <!-- Aquí mostraremos los productos destacados -->\r\n");
      out.write("            <div class=\"product-card\">\r\n");
      out.write("                <img src=\"https://via.placeholder.com/150\" alt=\"Producto 1\">\r\n");
      out.write("                <h3>Camisa Casual</h3>\r\n");
      out.write("                <p>29.99 €</p>\r\n");
      out.write("                <button>Ver detalles</button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"product-card\">\r\n");
      out.write("                <img src=\"https://via.placeholder.com/150\" alt=\"Producto 2\">\r\n");
      out.write("                <h3>Pantalón Vaquero</h3>\r\n");
      out.write("                <p>39.99 €</p>\r\n");
      out.write("                <button>Ver detalles</button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"product-card\">\r\n");
      out.write("                <img src=\"https://via.placeholder.com/150\" alt=\"Producto 3\">\r\n");
      out.write("                <h3>Vestido Elegante</h3>\r\n");
      out.write("                <p>49.99 €</p>\r\n");
      out.write("                <button>Ver detalles</button>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"product-card\">\r\n");
      out.write("                <img src=\"https://via.placeholder.com/150\" alt=\"Producto 4\">\r\n");
      out.write("                <h3>Chaqueta de Invierno</h3>\r\n");
      out.write("                <p>59.99 €</p>\r\n");
      out.write("                <button>Ver detalles</button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
