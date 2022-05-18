package Servlets;

import Entidades.*;
import LogicaNegocio.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Facturar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            BLEncabezados LogicaFactura = new BLEncabezados();
            EntidadEncabezadoF EntidadFactura = new EntidadEncabezadoF();
            EntidadDetalle EntidadDetalle = new EntidadDetalle();
            int resultado;
            String mensaje = "";

            if (request.getParameter("txtNombreCliente") != null
                    && !request.getParameter("txtNombreCliente").equals("")
                    && request.getParameter("txtNombreVendedor") != null && 
                    !request.getParameter("txtNombreVendedor").equals("")) {
                EntidadFactura.setNumFactura(Integer.parseInt(request.getParameter("txtnumFactura")));
                EntidadFactura.setCodVendedor(Integer.parseInt(request.getParameter("txtIdVendedor")));               
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaString = request.getParameter("txtFechaFactura");
                Date fecha = formato.parse(fechaString);
                java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                EntidadFactura.setFechaFactura(fechasql);
                EntidadFactura.setCodCliente(Integer.parseInt(request.getParameter("txtIdCliente")));
                EntidadFactura.setEstado("Pendiente");
                EntidadFactura.setTipoFactura(request.getParameter("txtTipoFactura"));

                if(!(request.getParameter("txtdescripcion").equals("")) &&
                   !(request.getParameter("txtcantidad").equals("")) &&
                   !(request.getParameter("txtprecio").equals(""))){
                    
                    EntidadDetalle.setNumFactura(-1);
                    EntidadDetalle.setCodProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
                    EntidadDetalle.setCantidadProd(Integer.parseInt(request.getParameter("txtcantidad")));
                    EntidadDetalle.setPrecio(Double.parseDouble(request.getParameter("txtprecio")));
                    
                    
                    resultado = LogicaFactura.Insertar(EntidadFactura, EntidadDetalle);
                    mensaje = LogicaFactura.getMensaje();
   
                }else{
                    resultado = LogicaFactura.ModificarCliente(EntidadFactura);
                }
                response.sendRedirect("Frm_Facturar.jsp?msgFac=" + mensaje + "&txtnumFactura=" + resultado);
            }else{
                response.sendRedirect("Frm_Facturar.jsp?txtnumFactura=" + request.getParameter("txtnumFactura"));
            }
            
            
        }catch(Exception e){
            out.print(e.getMessage());
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
