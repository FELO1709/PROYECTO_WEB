package AccesoDatos;

import Entidades.EntidadDetalle;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DADetalles {
    //ATRUBUTOS
    private String _mensaje;
    
    
    //PROPIEDADES
    public String getMensaje(){
        return _mensaje;
    }
    //CONSTRUCTORES
    public DADetalles(){
        _mensaje="";
    }

        public int Eliminar(EntidadDetalle detalle) throws Exception {
        CallableStatement CS = null; // para llamar a un procedimiento almacenado
        int resultado = 0;
        Connection _Conexion = null;
        try{
            _Conexion = ClaseConexion.getConnection();
            CS = _Conexion.prepareCall("{call SP_ELIMINAR_DETALLE(?,?,?)}");
            CS.setInt(1, detalle.getNumFactura());
            CS.setInt(2, detalle.getCodProducto());
            CS.setString(3, _mensaje);
            resultado = CS.executeUpdate();
            
        }catch(Exception ex){
            resultado = -1;
            throw ex;
        }finally{
             if(_Conexion != null){
                ClaseConexion.close(_Conexion);
            }
        }
        return resultado;
    }
    
    //Listar registros 
    public List<EntidadDetalle> ListarDetalles(String condicion) throws Exception{
        ResultSet rs = null;
        EntidadDetalle detalle;
        List<EntidadDetalle> lista = new ArrayList<>();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement st = _cnn.createStatement();
            String sentencia;
            sentencia = "SELECT F.NUM_FACTURA,F.COD_PRODUCTO,NOMBRE,CANTIDAD,PRECIO FROM DETALLES_FACTURA F"
                    + " INNER JOIN PRODUCTOS P ON P.COD_PRODUCTO = F.COD_PRODUCTO";
            if(!condicion.equals("")){
                sentencia = String.format("%s WHERE %s", sentencia,condicion);
            }
            rs = st.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de detalles
            while (rs.next()){
                detalle = new EntidadDetalle(rs.getInt(1),
                                             rs.getInt(2),
                                             rs.getString(3),
                                             rs.getInt(4),
                                             rs.getDouble(5)
                                        );
                lista.add(detalle);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }
        return lista;
    }//Fin listar Detalles
}
