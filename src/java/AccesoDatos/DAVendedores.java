package AccesoDatos;

import Entidades.EntidadVendedor;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DAVendedores {

    //ATRUBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    //CONSTRUCTORES
    public DAVendedores() {
        _mensaje = "";
    }

    //método para insertar un vendedor
    public int Insertar(EntidadVendedor vendedor) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{CALL SP_GUARDAR_VENDEDOR(?,?,?,?,?,?,?,?,?,?)}");
            //Parámetros de entrada
            CS.setInt(1, vendedor.getCodVendedor());
            CS.setString(2, vendedor.getCedula());
            CS.setString(3, vendedor.getNombre());
            CS.setString(4, vendedor.getApellido1());
            CS.setString(5, vendedor.getApellido2());
            CS.setString(6, vendedor.getTelefono());
            CS.setString(7, vendedor.getCorreo());
            CS.setString(8, vendedor.getDireccion());
            CS.setString(9, vendedor.getEstado());
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

    //método para modificar un vendedor
    public int Modificar(EntidadVendedor vendedor) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{CALL SP_MODIFICAR_VENDEDOR(?,?,?,?,?,?,?,?,?,?)}");
            //Parámetros de entrada
            CS.setInt(1, vendedor.getCodVendedor());
            CS.setString(2, vendedor.getCedula());
            CS.setString(3, vendedor.getNombre());
            CS.setString(4, vendedor.getApellido1());
            CS.setString(5, vendedor.getApellido2());
            CS.setString(6, vendedor.getTelefono());
            CS.setString(7, vendedor.getCorreo());
            CS.setString(8, vendedor.getDireccion());
            CS.setString(9, vendedor.getEstado());
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

    //método para eliminar un vendedor
    public int Eliminar(EntidadVendedor vendedor) throws Exception {
        int resultado = -1;
        CallableStatement cs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            cs = _conexion.prepareCall("{call SP_ELIMINAR_VENDEDOR (?,?)}");
            //Registrar parametros de entrada
            cs.setInt(1, vendedor.getCodVendedor());
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
    public List<EntidadVendedor> ListarVendedores(String condicion) throws SQLException, Exception {
        ResultSet rs = null;
        EntidadVendedor vendedor;
        List<EntidadVendedor> lista = new ArrayList();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT COD_VENDEDOR,CEDULA,NOMBRE,APELLIDO1,APELLIDO2,"
                    + "TELEFONO,CORREO,DIRECCION,ESTADO FROM VENDEDORES";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de vendedores
            while (rs.next()) {
                vendedor = new EntidadVendedor(rs.getInt("COD_VENDEDOR"),
                        rs.getString("CEDULA"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO1"),
                        rs.getString("APELLIDO2"),
                        rs.getString("TELEFONO"),
                        rs.getString("CORREO"),
                        rs.getString("DIRECCION"),
                        rs.getString("ESTADO")
                );
                lista.add(vendedor);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    //Obtener un vendedor
    public EntidadVendedor ObtenerRegistro(String condicion) throws Exception {
        ResultSet rs = null;
        EntidadVendedor vendedor = new EntidadVendedor();
        String sentencia;
        Connection _conexion = null;
        try {
            //abrir la conexion 
            _conexion = getConnection();
            Statement st = _conexion.createStatement();
            sentencia = "SELECT COD_VENDEDOR,CEDULA,NOMBRE,APELLIDO1,APELLIDO2, TELEFONO,CORREO, DIRECCION,ESTADO FROM VENDEDORES";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = st.executeQuery(sentencia);
            //se hace con un if por si devuelve más de un valor
            if (rs.next()) {
                vendedor.setCodVendedor(rs.getInt(1));
                vendedor.setCedula(rs.getString(2));
                vendedor.setNombre(rs.getString(3));
                vendedor.setApellido1(rs.getString(4));
                vendedor.setApellido2(rs.getString(5));
                vendedor.setTelefono(rs.getString(6));
                vendedor.setCorreo(rs.getString(7));
                vendedor.setDireccion(rs.getString(8));
                vendedor.setEstado(rs.getString(9));
                vendedor.setExiste(true);
            } else {
                vendedor.setExiste(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return vendedor;
    }//Fin obtener un vendedor
}
