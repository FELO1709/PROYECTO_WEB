package AccesoDatos;

import Entidades.EntidadCategoria;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DACategorias {
   //ATRUBUTOS
    private String _mensaje;
    
    //PROPIEDADES
    public String getMensaje(){
        return _mensaje;
    }
    //CONSTRUCTORES
    public DACategorias(){
        _mensaje="";
    }
    
    //Insertar una categoría
    public int Insertar(EntidadCategoria categoria) throws SQLException, Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{call SP_GUARDAR_CATEGORIA(?,?,?)}");
            
            CS.setInt(1,categoria.getCodCategoria());
            CS.setString(2,categoria.getNombreCategoria());
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
    
    //Modificar una categoría
    public int Modificar(EntidadCategoria categoria) throws SQLException, Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{call SP_MODIFICAR_CATEGORIA(?,?,?)}");
            CS.setInt(1,categoria.getCodCategoria());
            CS.setString(2,categoria.getNombreCategoria());
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
    
    //método para eliminar una categoría
    public int Eliminar(EntidadCategoria categoria) throws Exception {
        int resultado = -1;
        CallableStatement cs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            cs = _conexion.prepareCall("{call SP_ELIMINAR_CATEGORIA (?,?)}"); ///CAMBIAR
            //Registrar parametros de entrada
            cs.setInt(1, categoria.getCodCategoria());
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
    public List<EntidadCategoria> ListarCategorias(String condicion) throws SQLException, Exception{
        ResultSet rs = null;
        EntidadCategoria categoria;
        List<EntidadCategoria> lista = new ArrayList();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT COD_CATEGORIA, NOMBRE_CATEGORIA FROM CATEGORIAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
             //recorrer el rs para cargar la lista de categorías
            while(rs.next()){
                categoria = new EntidadCategoria(rs.getInt("COD_CATEGORIA"),
                                                 rs.getString("NOMBRE_CATEGORIA")
                );
                lista.add(categoria);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        } finally {
            if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }     
        return lista;
    }//Fin listar Categorías
    
     //Obtener una categoría
    public EntidadCategoria ObtenerRegistro(String condicion) throws Exception {
        ResultSet rs = null;
        EntidadCategoria categoria = new EntidadCategoria();
        String sentencia;
        Connection _conexion = null;
        try {
            //abrir la conexion 
            _conexion = getConnection();
            Statement st = _conexion.createStatement();
            sentencia = "SELECT COD_CATEGORIA, NOMBRE_CATEGORIA FROM CATEGORIAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = st.executeQuery(sentencia);
            //se hace con un if por si devuelve más de un valor
            if(rs.next()){
                categoria.setCodCategoria(rs.getInt(1));
                categoria.setNombreCategoria(rs.getString(2));
                categoria.setExiste(true);
            }else{
                categoria.setExiste(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return categoria;
    }//Fin obtener un Cliente
}