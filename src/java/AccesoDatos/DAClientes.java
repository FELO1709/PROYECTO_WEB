package AccesoDatos;

import Entidades.EntidadCliente;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DAClientes {
   //ATRUBUTOS
    private String _mensaje;
    
    //PROPIEDADES
    public String getMensaje(){
        return _mensaje;
    }
    //CONSTRUCTORES
    public DAClientes(){
        _mensaje="";
    }
    
    //método para insertar un cliente
    public int Insertar(EntidadCliente cliente) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{CALL SP_GUARDAR_CLIENTE(?,?,?,?,?,?,?,?,?,?)}");
            //Parámetros de entrada
            CS.setInt(1, cliente.getCodCliente());
            CS.setString(2, cliente.getCedula());
            CS.setString(3, cliente.getNombre());
            CS.setString(4, cliente.getApellido1());
            CS.setString(5, cliente.getApellido2());
            CS.setString(6, cliente.getTelefono());
            CS.setString(7, cliente.getCorreo());
            CS.setString(8, cliente.getDireccion());
            CS.setString(9, cliente.getEstado());
            CS.setString(10, _mensaje);
            //Parámetros de salida
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(10, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(10);

        } catch (Exception e) {
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }//Fin de insertar 

    //método para modificar un cliente
    public int Modificar(EntidadCliente cliente) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{CALL SP_MODIFICAR_CLIENTE(?,?,?,?,?,?,?,?,?,?)}");
            //Parámetros de entrada
            CS.setInt(1, cliente.getCodCliente());
            CS.setString(2, cliente.getCedula());
            CS.setString(3, cliente.getNombre());
            CS.setString(4, cliente.getApellido1());
            CS.setString(5, cliente.getApellido2());
            CS.setString(6, cliente.getTelefono());
            CS.setString(7, cliente.getCorreo());
            CS.setString(8, cliente.getDireccion());
            CS.setString(9, cliente.getEstado());
            CS.setString(10, _mensaje);
            //Parámetros de salida
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(10, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(10);

        } catch (Exception e) {
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }//Fin de modificar 

    //método para eliminar un cliente
    public int Eliminar(EntidadCliente cliente) throws Exception {
        int resultado = -1;
        CallableStatement cs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            cs = _conexion.prepareCall("{call SP_ELIMINAR_CLIENTE (?,?)}");
            //Registrar parametros de entrada
            cs.setInt(1, cliente.getCodCliente());
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
    public List<EntidadCliente> ListarClientes(String condicion) throws SQLException, Exception{
        ResultSet rs = null;
        EntidadCliente cliente;
        List<EntidadCliente> lista = new ArrayList();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT COD_CLIENTE,CEDULA,NOMBRE,APELLIDO1,APELLIDO2,"
                                    + "TELEFONO,CORREO,DIRECCION,ESTADO FROM CLIENTES";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de clientes
            while(rs.next()){
                cliente = new EntidadCliente(rs.getInt("COD_CLIENTE"),
                                             rs.getString("CEDULA"),
                                             rs.getString("NOMBRE"),
                                             rs.getString("APELLIDO1"),
                                             rs.getString("APELLIDO2"),
                                             rs.getString("TELEFONO"),
                                             rs.getString("CORREO"),                                        
                                             rs.getString("DIRECCION"),
                                             rs.getString("ESTADO")
                );
                lista.add(cliente);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
             if(_cnn != null){
                ClaseConexion.close(_cnn);
            }
        }
        return lista;
    }//Fin listar Clientes
    
    //Obtener un cliente
    public EntidadCliente ObtenerRegistro(String condicion) throws Exception {
        ResultSet rs = null;
        EntidadCliente cliente = new EntidadCliente();
        String sentencia;
        Connection _conexion = null;
        try {
            //abrir la conexion 
            _conexion = getConnection();
            Statement st = _conexion.createStatement();
            sentencia = "SELECT COD_CLIENTE,CEDULA,NOMBRE,APELLIDO1,APELLIDO2, TELEFONO,CORREO, DIRECCION,ESTADO FROM CLIENTES";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = st.executeQuery(sentencia);
            //se hace con un if por si devuelve más de un valor
            if(rs.next()){
                cliente.setCodCliente(rs.getInt(1));
                cliente.setCedula(rs.getString(2));
                cliente.setNombre(rs.getString(3));
                cliente.setApellido1(rs.getString(4));
                cliente.setApellido2(rs.getString(5));
                cliente.setTelefono(rs.getString(6));
                cliente.setCorreo(rs.getString(7));
                cliente.setDireccion(rs.getString(8));
                cliente.setEstado(rs.getString(9));
                cliente.setExiste(true);
            }else{
                cliente.setExiste(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return cliente;
    }//Fin obtener un Cliente
    
   
}
