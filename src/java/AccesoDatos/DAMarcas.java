package AccesoDatos;

import Entidades.EntidadMarca;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DAMarcas {
    //ATRUBUTOS
    private String _mensaje;
    
    //PROPIEDADES
    public String getMensaje(){
        return _mensaje;
    }
    //CONSTRUCTORES
    public DAMarcas(){
        _mensaje="";
    }
      
    //Insertar una marca
    public int Insertar(EntidadMarca marca) throws SQLException, Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{call SP_GUARDAR_MARCA(?,?,?)}");
            
            CS.setInt(1,marca.getCodMarca());
            CS.setString(2,marca.getNombreMarca());
            CS.setString(3,getMensaje());
            
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(3, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(3);

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }
    
    //Modificar una marca
    public int Modificar(EntidadMarca marca) throws SQLException, Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{call SP_MODIFICAR_MARCA(?,?,?)}");
            CS.setInt(1,marca.getCodMarca());
            CS.setString(2,marca.getNombreMarca());
            CS.setString(3,getMensaje());
            
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(3, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(3);

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;

    }
    
    //método para eliminar una marca
    public int Eliminar(EntidadMarca marca) throws Exception {
        int resultado = -1;
        CallableStatement cs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            cs = _conexion.prepareCall("{call SP_ELIMINAR_MARCA (?,?)}"); ///CAMBIAR
            //Registrar parametros de entrada
            cs.setInt(1, marca.getCodMarca());
            cs.setString(2, _mensaje);
            //Registrar parámetros de salida
            cs.registerOutParameter(2, Types.VARCHAR);
            //Ejecutar
            resultado = cs.executeUpdate();
            //Leer parámetros de salida
            _mensaje = cs.getString(2);
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }
    //Fin de eliminar 
    
    //Listar Registros en una Lista
    public List<EntidadMarca> ListarMarcas(String condicion) throws SQLException, Exception{
        ResultSet rs = null;
        EntidadMarca marca;
        List<EntidadMarca> lista = new ArrayList();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT COD_MARCA, NOMBRE_MARCA FROM MARCAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de marcas
            while(rs.next()){
                marca = new EntidadMarca(rs.getInt("COD_MARCA"),
                                           rs.getString("NOMBRE_MARCA")
                );
                lista.add(marca);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
             if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }     
        return lista;
    }//Fin listar Marcas
    
     //Obtener una marca
    public EntidadMarca ObtenerRegistro(String condicion) throws Exception {
        ResultSet rs = null;
        EntidadMarca marca = new EntidadMarca();
        String sentencia;
        Connection _conexion = null;
        try {
            //abrir la conexion 
            _conexion = getConnection();
            Statement st = _conexion.createStatement();
            sentencia = "SELECT COD_MARCA, NOMBRE_MARCA FROM MARCAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = st.executeQuery(sentencia);
            //se hace con un if por si devuelve más de un valor
            if(rs.next()){
                marca.setCodMarca(rs.getInt(1));
                marca.setNombreMarca(rs.getString(2));
                marca.setExiste(true);
            }else{
                marca.setExiste(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return marca;
    }//Fin obtener una marca
}
