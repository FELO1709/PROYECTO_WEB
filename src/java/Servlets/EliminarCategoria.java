/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Entidades.EntidadCategoria;
import LogicaNegocio.BLCategorias;
import java.io.*;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EliminarCategoria")
public class EliminarCategoria extends HttpServlet {
    
//Sobreescribir el método doGET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //indica el tipo de salida a generar HTML dentro del Servlet
        response.setContentType("text/html;charset=UTF-8");
        //para poder escribir en el HTML
        PrintWriter out = response.getWriter();
        try {
            BLCategorias logica = new BLCategorias();
            EntidadCategoria categoria = new EntidadCategoria();
            //parametro que se envió por medio de Query String 
            String id = request.getParameter("idEliminar");
            int codigo = Integer.parseInt(id);
            categoria.setCodCategoria(codigo);
            int resultado = logica.Eliminar(categoria);
            String mensaje = logica.getMensaje(); //Mensaje del SP
            mensaje = URLEncoder.encode(mensaje, "UTF-8");
            //redireccionar a la página jsp
            response.sendRedirect("frmListarCategorias.jsp?meEmp="+ mensaje + "&resultado=" + resultado);
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }
}
