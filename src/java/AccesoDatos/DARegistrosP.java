package AccesoDatos;

import Entidades.EntidadRegistroP;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DARegistrosP {
    //ATRUBUTOS

    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    //CONSTRUCTORES
    public DARegistrosP() {
        _mensaje = "";
    }
    
    //Listar registros 
    public List<EntidadRegistroP> ListarRegistrosPagos() throws Exception{
        ResultSet rs = null;
        EntidadRegistroP registroP;
        List<EntidadRegistroP> lista = new ArrayList<>();
        Connection _conexion = null;
        try {
            //abrir la conexion 
            _conexion = ClaseConexion.getConnection();
            Statement st = _conexion.createStatement();
            String sentencia;
            sentencia = "SELECT COD_REGISTRO,COD_PAGO,FECHA,TOTAL,SALDO FROM REGISTRO_PAGOS_F";
            rs = st.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de registros de pago
            while (rs.next()){
                registroP = new EntidadRegistroP(rs.getInt(1),
                                                 rs.getInt(2),
                                                 rs.getDate(3),
                                                 rs.getDouble(4),
                                                 rs.getDouble(5)
                                        );
                lista.add(registroP);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(_conexion != null){
                ClaseConexion.close(_conexion);
            }
        }
        return lista;
    }//Fin listar Registros Pagos
}
