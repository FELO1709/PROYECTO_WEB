package AccesoDatos;

import Entidades.EntidadEmpresa;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;


public class DAEmpresas {
         //ATRUBUTOS
    private String _mensaje;
    
    //PROPIEDADES
    public String getMensaje(){
        return _mensaje;
    }
    //CONSTRUCTORES
    public DAEmpresas(){
        _mensaje="";
    }
    
    //Insertar una empresa
    public int Insertar(EntidadEmpresa empresa) throws SQLException, Exception {
        CallableStatement CS = null;
        int resultado = -1;       
        Connection _conexion = null;
        
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{call SP_GUARDAR_EMPRESA(?,?,?,?,?,?,?)}");
            
            CS.setInt(1,empresa.getCodEmpresa());
            CS.setString(2,empresa.getCedulaJuridica());
            CS.setString(3,empresa.getNombreEmpresa());
            CS.setString(4,empresa.getTelefono());
            CS.setString(5,empresa.getCorreo());
            CS.setString(6,empresa.getDireccion());
            CS.setString(7,_mensaje);
            
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(7, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(7);

        } catch (Exception ex) {
            _mensaje = "Error inesperado, intente más tarde";
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }
    
    //Modificar una empresa
    public int Modificar(EntidadEmpresa empresa) throws SQLException, Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{call SP_MODIFICAR_EMPRESA(?,?,?,?,?,?,?)}");
            CS.setInt(1,empresa.getCodEmpresa());
            CS.setString(2,empresa.getCedulaJuridica());
            CS.setString(3,empresa.getNombreEmpresa());
            CS.setString(4,empresa.getTelefono());
            CS.setString(5,empresa.getCorreo());
            CS.setString(6,empresa.getDireccion());
            CS.setString(7,getMensaje());
            
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(7, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(7);

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;

    }
    
    //método para eliminar una empresa
    public int Eliminar(EntidadEmpresa empresa) throws Exception {
        int resultado = -1;
        CallableStatement cs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            cs = _conexion.prepareCall("{call SP_ELIMINAR_EMPRESA (?,?)}");
            //Registrar parametros de entrada
            cs.setInt(1, empresa.getCodEmpresa());
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
    public List<EntidadEmpresa> ListarEmpresas(String condicion) throws SQLException, Exception{
        ResultSet rs = null;
        EntidadEmpresa empresa;
        List<EntidadEmpresa> lista = new ArrayList();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT COD_EMPRESA,CEDULA_J,NOMBRE_EMPRESA,"
                                    + "TELEFONO,CORREO,DIRECCION FROM EMPRESAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de empresas
            while(rs.next()){
                empresa = new EntidadEmpresa(rs.getInt("COD_EMPRESA"),
                                             rs.getString("CEDULA_J"),
                                             rs.getString("NOMBRE_EMPRESA"),
                                             rs.getString("TELEFONO"),
                                             rs.getString("CORREO"),                                        
                                             rs.getString("DIRECCION")
                );
                lista.add(empresa);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
              if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }     
        return lista;
    }//Fin listar Empresas

     //Obtener una empresa
    public EntidadEmpresa ObtenerRegistro(String condicion) throws Exception {
        ResultSet rs = null;
        EntidadEmpresa empresa = new EntidadEmpresa();
        String sentencia;
        Connection _conexion = null;
        try {
            //abrir la conexion 
            _conexion = getConnection();
            Statement st = _conexion.createStatement();
            sentencia = "SELECT COD_EMPRESA,CEDULA_J,NOMBRE_EMPRESA,"
                                    + "TELEFONO,CORREO,DIRECCION FROM EMPRESAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = st.executeQuery(sentencia);
            //se hace con un if por si devuelve más de un valor
            if(rs.next()){
                empresa.setCodEmpresa(rs.getInt(1));
                empresa.setCedulaJuridica(rs.getString(2));
                empresa.setNombreEmpresa(rs.getString(3));
                empresa.setTelefono(rs.getString(4));
                empresa.setCorreo(rs.getString(5));
                empresa.setDireccion(rs.getString(6));
                empresa.setExiste(true);
            }else{
                empresa.setExiste(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return empresa;
    }//Fin obtener una empresa
    
}
