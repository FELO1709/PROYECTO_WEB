/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.EntidadProducto;
import LogicaNegocio.BLProductos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrearModificarProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            BLProductos Logica = new BLProductos();
            EntidadProducto producto = new EntidadProducto();
            int resultado;

            if (request.getParameter("txtCodCategoria") != null && !request.getParameter("txtCodCategoria").equals("")
                    && request.getParameter("txtCodMarca") != null && !request.getParameter("txtCodMarca").equals("")) {
                producto.setCodProducto(Integer.parseInt(request.getParameter("txtCodigo")));
                producto.setCodCategoria(Integer.parseInt(request.getParameter("txtCodCategoria")));
                producto.setCodMarca(Integer.parseInt(request.getParameter("txtCodMarca")));
                producto.setNombre(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));
                producto.setDescripcion(new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8"));
                producto.setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                producto.setPrecioCompra(Double.parseDouble(request.getParameter("txtPrecioCompra")));
                producto.setPrecioVenta(Double.parseDouble(request.getParameter("txtPrecioVenta")));
                producto.setImpuesto(Double.parseDouble(request.getParameter("txtImpuesto")));    
                producto.setBorrado(Integer.parseInt(request.getParameter("txtBorrado")));  

                if (producto.getCodProducto() > 0) {
                    resultado = Logica.Modificar(producto);
                } else {
                    resultado = Logica.Insertar(producto);
                }
                response.sendRedirect("frmListarProductos.jsp");
            } else {
                response.sendRedirect("FrmProductos.jsp?txtCodigo=" + request.getParameter("txtCodigo"));
            }
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
