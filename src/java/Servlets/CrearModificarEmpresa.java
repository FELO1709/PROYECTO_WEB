/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.EntidadEmpresa;
import LogicaNegocio.BLEmpresas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luism
 */
public class CrearModificarEmpresa extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            BLEmpresas Logica = new BLEmpresas();
            EntidadEmpresa empresa = new EntidadEmpresa();
            int resultado;

            empresa.setCodEmpresa(Integer.parseInt(request.getParameter("txtCodigo")));
            empresa.setCedulaJuridica(new String(request.getParameter("txtCedula").getBytes("ISO-8859-1"), "UTF-8"));
            empresa.setNombreEmpresa(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));
            empresa.setTelefono(request.getParameter("txtTelefono"));
            empresa.setCorreo(new String(request.getParameter("txtCorreo").getBytes("ISO-8859-1"), "UTF-8"));
            empresa.setDireccion(new String(request.getParameter("txtDireccion").getBytes("ISO-8859-1"), "UTF-8"));

            if (empresa.getCodEmpresa()> 0) {
                resultado = Logica.Modificar(empresa);
            } else {
                resultado = Logica.Insertar(empresa);
            }
            response.sendRedirect("frmListarEmpresas.jsp");

        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
