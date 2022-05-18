package AccesoDatos;

import Entidades.*;
import java.util.*;
import java.sql.*;


//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DAEncabezados {
     //ATRUBUTOS
    private String _mensaje;
    
    //PROPIEDADES
    public String getMensaje(){
        return _mensaje;
    }
    //CONSTRUCTORES
    public DAEncabezados(){
        _mensaje="";
    }
    
    public int Insertar(EntidadEncabezadoF EntidadFactura, EntidadDetalle EntidadDetalle) throws SQLException, ClassNotFoundException{
        CallableStatement CS; // para llamar a un procedimiento almacenado
        int resultado = 0;
        int idFactura = 0;
        Connection _Conexion = null;
        try{
            _Conexion = ClaseConexion.getConnection();
            _Conexion.setAutoCommit(false);
            CS = _Conexion.prepareCall("{call SP_GUARDAR_FACTURA(?,?,?,?,?,?,?,?)}");
            CS.setInt(1, EntidadFactura.getNumFactura());
            CS.setInt(2, EntidadFactura.getCodVendedor());
            CS.setDate(3, EntidadFactura.getFechaFactura());
            CS.setInt(4, EntidadFactura.getCodCliente());
            CS.setString(5, EntidadFactura.getEstado());
            CS.setDouble(6, EntidadFactura.getTotal());
            CS.setString(7, EntidadFactura.getTipoFactura());
            CS.setString(8, _mensaje);
            
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(8, Types.VARCHAR);
            
            resultado = CS.executeUpdate();
            idFactura = CS.getInt(1);
            
            CS = _Conexion.prepareCall("{call SP_GUARDAR_DETALLE(?,?,?,?,?)}");
            CS.setInt(1, idFactura);
            CS.setInt(2, EntidadDetalle.getCodProducto());
            CS.setInt(3, EntidadDetalle.getCantidadProd());
            CS.setDouble(4,(double) EntidadDetalle.getPrecio());
            CS.setString(5, _mensaje);
            
            CS.registerOutParameter(5, Types.VARCHAR);
            resultado = CS.executeUpdate();
            
            _mensaje = CS.getString(5);
            _Conexion.commit();
        }catch(ClassNotFoundException | SQLException ex){
            _Conexion.rollback();
            throw ex;
        }finally{
            if(_Conexion != null){
                ClaseConexion.close(_Conexion);
            }
        }
        return idFactura;
    }
    
     public int ModificarEncabezado(EntidadEncabezadoF EntidadFactura) throws SQLException, ClassNotFoundException{
        CallableStatement CS; // para llamar a un procedimiento almacenado
        int resultado = 0;
        int idFactura = 0;
        Connection _Conexion = null;
        try{
            _Conexion = ClaseConexion.getConnection();
            _Conexion.setAutoCommit(false);
            CS = _Conexion.prepareCall("{call SP_MODIFICAR_FACTURA(?,?,?)}");
            CS.setInt(1, EntidadFactura.getNumFactura());
            CS.setDouble(2, EntidadFactura.getTotal());
            CS.setString(3, _mensaje);
            
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(3, Types.VARCHAR);
            
            resultado = CS.executeUpdate();         
            
            _mensaje = CS.getString(3);
            _Conexion.commit();
        }catch(ClassNotFoundException | SQLException ex){
            _Conexion.rollback();
            throw ex;
        }finally{
            if(_Conexion != null){
                ClaseConexion.close(_Conexion);
            }
        }
        return idFactura;
    }
    
     public int ModificarEstado(EntidadEncabezadoF EntidadFactura) throws Exception {
        int resultado = 0;
        Connection _Conexion = null;

        try {
            _Conexion = ClaseConexion.getConnection();
            PreparedStatement PS = _Conexion.prepareStatement("UPDATE ENCABEZADO_FACTURA SET ESTADO=? WHERE NUM_FACTURA = ?");

            PS.setString(1, EntidadFactura.getEstado());
            PS.setInt(2, EntidadFactura.getNumFactura());
                   
            resultado = PS.executeUpdate();
        } catch (Exception ex) {
            resultado = -1;
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return resultado;
    }//Modificar
    
       public int ModificarCliente(EntidadEncabezadoF EntidadFactura) throws Exception {
        int idfactura = 0;
        Connection _Conexion = null;

        try {
            _Conexion = ClaseConexion.getConnection();
            PreparedStatement PS = _Conexion.prepareStatement("UPDATE ENCABEZADO_FACTURA SET COD_CLIENTE=? WHERE NUM_FACTURA = ?");

            PS.setInt(1, EntidadFactura.getNumFactura());
            PS.setInt(2, EntidadFactura.getCodCliente());
            
            PS.executeUpdate();
            idfactura = EntidadFactura.getNumFactura();
        } catch (Exception ex) {
            //resultado = -1;
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return idfactura;
    }//Modificar
    
        //Listar registros 
    public List<EntidadEncabezadoF> ListarEncabezados(String condicion) throws Exception{
        ResultSet rs = null;
        EntidadEncabezadoF factura;
        List<EntidadEncabezadoF> lista = new ArrayList<>();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement st = _cnn.createStatement();
            String sentencia;
            sentencia = "SELECT NUM_FACTURA,F.COD_VENDEDOR,V.NOMBRE AS NOMBRE_VENDEDOR,FECHA,F.COD_CLIENTE,"
                    + "C.CEDULA AS CED_CLIENTE,C.NOMBRE AS NOMBRE_CLIENTE,F.ESTADO,TOTAL,TIPOFACTURA FROM CLIENTES C "
                    + "INNER JOIN ENCABEZADO_FACTURA F ON F.COD_CLIENTE = C.COD_CLIENTE "
                    + "INNER JOIN VENDEDORES V ON F.COD_VENDEDOR = V.COD_VENDEDOR";
            if(!condicion.equals("")){
                sentencia = String.format("%s WHERE %s", sentencia,condicion);
            }
            rs = st.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de clientes
            while (rs.next()){
                factura = new EntidadEncabezadoF(rs.getInt("NUM_FACTURA"),
                                                 rs.getInt("COD_VENDEDOR"),
                                                 rs.getString("NOMBRE_VENDEDOR"),
                                                 rs.getDate("FECHA"),
                                                 rs.getInt("COD_CLIENTE"),
                                                 rs.getString("CED_CLIENTE"),
                                                 rs.getString("NOMBRE_CLIENTE"),
                                                 rs.getString("ESTADO"),
                                                 rs.getDouble("TOTAL"),
                                                 rs.getString("TIPOFACTURA")
                                        );
                lista.add(factura);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }
        return lista;
    }//Fin listar Encabezados
    
    
     public List<EntidadEncabezadoF> ListarEncabezadosCredito() throws Exception{
        ResultSet rs = null;
        EntidadEncabezadoF factura;
        List<EntidadEncabezadoF> lista = new ArrayList<>();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement st = _cnn.createStatement();
            String sentencia;
            sentencia = "SELECT NUM_FACTURA,F.COD_VENDEDOR,V.NOMBRE AS NOMBRE_VENDEDOR,FECHA,F.COD_CLIENTE,"
                    + "C.CEDULA AS CED_CLIENTE,C.NOMBRE AS NOMBRE_CLIENTE,F.ESTADO,TOTAL,TIPOFACTURA FROM CLIENTES C "
                    + "INNER JOIN ENCABEZADO_FACTURA F ON F.COD_CLIENTE = C.COD_CLIENTE "
                    + "INNER JOIN VENDEDORES V ON F.COD_VENDEDOR = V.COD_VENDEDOR WHERE TIPOFACTURA = 'CREDITO'";
            rs = st.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de clientes
            while (rs.next()){
                factura = new EntidadEncabezadoF(rs.getInt("NUM_FACTURA"),
                                                 rs.getInt("COD_VENDEDOR"),
                                                 rs.getString("NOMBRE_VENDEDOR"),
                                                 rs.getDate("FECHA"),
                                                 rs.getInt("COD_CLIENTE"),
                                                 rs.getString("CED_CLIENTE"),
                                                 rs.getString("NOMBRE_CLIENTE"),
                                                 rs.getString("ESTADO"),
                                                 rs.getDouble("TOTAL"),
                                                 rs.getString("TIPOFACTURA")
                                        );
                lista.add(factura);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }
        return lista;
    }//Fin listar Encabezados
    
    public EntidadEncabezadoF ObtenerRegistro(String Condicion) throws Exception {
        ResultSet RsDatos = null;
        EntidadEncabezadoF Entidad = new EntidadEncabezadoF();
        String Sentencia;
        Connection _conexion = null;
        Sentencia = "SELECT NUM_FACTURA,F.COD_VENDEDOR,V.NOMBRE AS NOMBRE_VENDEDOR,FECHA,F.COD_CLIENTE,"
                + "C.CEDULA AS CED_CLIENTE,C.NOMBRE AS NOMBRE_CLIENTE,F.ESTADO,TOTAL,TIPOFACTURA FROM CLIENTES C "
                    + "INNER JOIN ENCABEZADO_FACTURA F ON F.COD_CLIENTE = C.COD_CLIENTE "
                    + "INNER JOIN VENDEDORES V ON F.COD_VENDEDOR = V.COD_VENDEDOR";
         if (!Condicion.equals("")) {
             Sentencia = String.format("%s WHERE %s", Sentencia, Condicion);
         }
        try {
            _conexion = ClaseConexion.getConnection();
            Statement ST = _conexion.createStatement();
            RsDatos = ST.executeQuery(Sentencia);
            if (RsDatos.next()) {
                Entidad.setNumFactura(RsDatos.getInt("NUM_FACTURA"));
                Entidad.setCodVendedor(RsDatos.getInt("COD_VENDEDOR"));
                Entidad.setNombreVendedor(RsDatos.getString("NOMBRE_VENDEDOR"));
                Entidad.setFechaFactura(RsDatos.getDate("FECHA"));
                Entidad.setCodCliente(RsDatos.getInt("COD_CLIENTE"));
                Entidad.setCedulaCliente(RsDatos.getString("CED_CLIENTE"));
                Entidad.setNombreCliente(RsDatos.getString("NOMBRE_CLIENTE"));
                Entidad.setEstado(RsDatos.getString("ESTADO"));
                Entidad.setTotal(RsDatos.getDouble("TOTAL"));
                Entidad.setTipoFactura(RsDatos.getString("TIPOFACTURA"));
                Entidad.setExiste(true);
            } else {
                Entidad.setExiste(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return Entidad;
    }
    
}
