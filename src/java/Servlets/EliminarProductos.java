package Servlets;

import Entidades.EntidadProducto;
import LogicaNegocio.BLProductos;
import java.io.*;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EliminarProductos")
public class EliminarProductos extends HttpServlet {

    //Sobreescribir el método doGET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //indica el tipo de salida a generar HTML dentro del Servlet
        response.setContentType("text/html;charset=UTF-8");
        //para poder escribir en el HTML
        PrintWriter out = response.getWriter();
        try {
            BLProductos logica = new BLProductos();
            EntidadProducto producto = new EntidadProducto();
            //parametro que se envió por medio de Query String 
            String id = request.getParameter("idEliminar");
            int codigo = Integer.parseInt(id);
            producto.setCodProducto(codigo);
            int resultado = logica.Eliminar(producto);
            String mensaje = logica.getMensaje(); //Mensaje del SP
            mensaje = URLEncoder.encode(mensaje, "UTF-8");
            //redireccionar a la página jsp
            response.sendRedirect("frmListarProductos.jsp?meProd="+ mensaje + "&resultado=" + resultado);
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }
}
