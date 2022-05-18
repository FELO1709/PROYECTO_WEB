package AccesoDatos;

import Entidades.EntidadPago;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DAPagos {
           //ATRUBUTOS
    private String _mensaje;
    
    //PROPIEDADES
    public String getMensaje(){
        return _mensaje;
    }
    //CONSTRUCTORES
    public DAPagos(){
        _mensaje="";
    }
    
    //Listar registros 
    public List<EntidadPago> ListarPagos(String condicion) throws Exception{
        ResultSet rs = null;
        EntidadPago pago;
        List<EntidadPago> lista = new ArrayList<>();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement st = _cnn.createStatement();
            String sentencia;
            sentencia = "SELECT COD_PAGO,NUM_FACTURA,FECHA_PAGO,TOTAL_PAGO,IMPUESTO FROM PAGOS";
            if(!condicion.equals("")){
                sentencia = String.format("%s WHERE %s", sentencia,condicion);
            }
            rs = st.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de pagos
            while (rs.next()){
                pago = new EntidadPago(rs.getInt(1),
                                       rs.getInt(2),
                                       rs.getDate(3),
                                       rs.getDouble(4),
                                       rs.getDouble(5)
                                        );
                lista.add(pago);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }
        return lista;
    }//Fin listar Pagos
}
