package Servlets;

import Entidades.EntidadEmpresa;
import LogicaNegocio.BLEmpresas;
import java.io.*;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Anotación -> definir la ruta de acceso dentro de la aplicación
@WebServlet("/EliminarEmpresa")
public class EliminarEmpresa extends HttpServlet {
    //Sobreescribir el método doGET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //indica el tipo de salida a generar HTML dentro del Servlet
        response.setContentType("text/html;charset=UTF-8");
        //para poder escribir en el HTML
        PrintWriter out = response.getWriter();
        try {
            BLEmpresas logica = new BLEmpresas();
            EntidadEmpresa empresa = new EntidadEmpresa();
            //parametro que se envió por medio de Query String 
            String id = request.getParameter("idEliminar");
            int codigo = Integer.parseInt(id);
            empresa.setCodEmpresa(codigo);
            int resultado = logica.Eliminar(empresa);
            String mensaje = logica.getMensaje(); //Mensaje del SP
            mensaje = URLEncoder.encode(mensaje, "UTF-8");
            //redireccionar a la página jsp
            response.sendRedirect("frmListarEmpresas.jsp?meEmp="+ mensaje + "&resultado=" + resultado);
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }
}
