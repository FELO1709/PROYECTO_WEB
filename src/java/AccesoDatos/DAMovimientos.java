package AccesoDatos;

import Entidades.EntidadMovimiento;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DAMovimientos {
       //ATRUBUTOS
    private String _mensaje;
    
    //PROPIEDADES
    public String getMensaje(){
        return _mensaje;
    }
    //CONSTRUCTORES
    public DAMovimientos(){
        _mensaje="";
    }
    
//    //Listar registros 
    public List<EntidadMovimiento> ListarMovimientos(String condicion) throws Exception{
        ResultSet rs = null;
        EntidadMovimiento movimiento;
        List<EntidadMovimiento> lista = new ArrayList<>();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement st = _cnn.createStatement();
            String sentencia;
            sentencia = "SELECT COD_MOVIMIENTO,NUM_FACTURA,FECHA,CANTIDAD_ACT,CANTIDAD_MOV,CANTIDAD_INV FROM MOVIMIENTOS";
            if(!condicion.equals("")){
                sentencia = String.format("%s WHERE %s", sentencia,condicion);
            }
            rs = st.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de movimientos
            while (rs.next()){
                movimiento = new EntidadMovimiento(rs.getInt(1),
                                                   rs.getInt(2),
                                                   rs.getDate(3),
                                                   rs.getInt(4),
                                                   rs.getInt(5),
                                                   rs.getInt(6)

                                        );
                lista.add(movimiento);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }
        return lista;
    }//Fin listar Movimientos
}
