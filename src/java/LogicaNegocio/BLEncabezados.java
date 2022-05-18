package LogicaNegocio;

import AccesoDatos.*;
import Entidades.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLEncabezados {
//Atributos

    private String _mensaje;

    public String getMensaje() {
        return _mensaje;
    }

    public BLEncabezados() {
    }

    public int Insertar(EntidadEncabezadoF factura, EntidadDetalle detalle) throws Exception {
        int Resultado = 0;
        // Llamar a la capa de acceso a datos:
        try {
            DAEncabezados DA = new DAEncabezados();
            Resultado = DA.Insertar(factura, detalle);
            _mensaje = DA.getMensaje();
        } catch (Exception e) {
            Resultado = -1;
            throw e;
        }
        return Resultado;
    }
    
    public int ModificarEstado(EntidadEncabezadoF factura) throws Exception {
        int Resultado = 0;

        try {
            DAEncabezados DA = new DAEncabezados();
            Resultado = DA.ModificarEstado(factura);

        } catch (Exception ex) {
            throw ex;
        }
        return Resultado;
    }

    public int ModificarEncabezado(EntidadEncabezadoF factura) throws Exception {
        int Resultado = 0;

        try {
            DAEncabezados DA = new DAEncabezados();
            Resultado = DA.ModificarEncabezado(factura);

        } catch (Exception ex) {
            throw ex;
        }
        return Resultado;
    }

    
    public int ModificarCliente(EntidadEncabezadoF factura) throws Exception {
        int idfactura = 0;

        try {
            DAEncabezados DA = new DAEncabezados();
            idfactura = DA.ModificarCliente(factura);
            _mensaje = DA.getMensaje();
        } catch (Exception ex) {
            throw ex;
        }
        return idfactura;
    }

    public List<EntidadEncabezadoF> ListarRegistros(String condicion) throws Exception {
        List<EntidadEncabezadoF> Datos;
        try {
            DAEncabezados DA = new DAEncabezados();
            Datos = DA.ListarEncabezados(condicion);
        } catch (Exception ex) {
            Datos = null;
            throw ex;
        }
        return Datos;
    }
    
    public List<EntidadEncabezadoF> ListarRegistrosCredito() throws Exception {
        List<EntidadEncabezadoF> Datos;
        try {
            DAEncabezados DA = new DAEncabezados();
            Datos = DA.ListarEncabezadosCredito();
        } catch (Exception ex) {
            Datos = null;
            throw ex;
        }
        return Datos;
    }

    public EntidadEncabezadoF ObtenerRegistro(String condicion) throws Exception {
        EntidadEncabezadoF factura = null;

        try {
            DAEncabezados DA = new DAEncabezados();
            factura = DA.ObtenerRegistro(condicion);

        } catch (Exception ex) {
            throw ex;
        }

        return factura;
    }
}
