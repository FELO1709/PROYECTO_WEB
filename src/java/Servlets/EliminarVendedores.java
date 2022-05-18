package Servlets;

import Entidades.EntidadVendedor;
import LogicaNegocio.BLVendedores;
import java.io.*;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Anotación -> definir la ruta de acceso dentro de la aplicación
@WebServlet("/EliminarVendedores")
public class EliminarVendedores extends HttpServlet {
     //Sobreescribir el método doGET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //indica el tipo de salida a generar HTML dentro del Servlet
        response.setContentType("text/html;charset=UTF-8");
        //para poder escribir en el HTML
        PrintWriter out = response.getWriter();
        try {
            BLVendedores logica = new BLVendedores();
            EntidadVendedor vendedor = new EntidadVendedor();
            //parametro que se envió por medio de Query String 
            String id = request.getParameter("idEliminar");
            int codigo = Integer.parseInt(id);
            vendedor.setCodVendedor(codigo);
            int resultado = logica.Eliminar(vendedor);
            String mensaje = logica.getMensaje(); //Mensaje del SP
            mensaje = URLEncoder.encode(mensaje, "UTF-8");
            //redireccionar a la página jsp
            response.sendRedirect("frmListarVendedores.jsp?meVend="+ mensaje + "&resultado=" + resultado);
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }
}
