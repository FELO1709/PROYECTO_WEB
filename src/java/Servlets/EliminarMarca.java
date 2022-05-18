/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets;

import Entidades.EntidadMarca;
import LogicaNegocio.BLMarcas;
import java.io.*;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EliminarMarca")
public class EliminarMarca extends HttpServlet {
    
//Sobreescribir el método doGET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //indica el tipo de salida a generar HTML dentro del Servlet
        response.setContentType("text/html;charset=UTF-8");
        //para poder escribir en el HTML
        PrintWriter out = response.getWriter();
        try {
            BLMarcas logica = new BLMarcas();
            EntidadMarca marca = new EntidadMarca();
            //parametro que se envió por medio de Query String 
            String id = request.getParameter("idEliminar");
            int codigo = Integer.parseInt(id);
            marca.setCodMarca(codigo);
            int resultado = logica.Eliminar(marca);
            String mensaje = logica.getMensaje(); //Mensaje del SP
            mensaje = URLEncoder.encode(mensaje, "UTF-8");
            //redireccionar a la página jsp
            response.sendRedirect("frmListarMarcas.jsp?meEmp="+ mensaje + "&resultado=" + resultado);
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }
}

