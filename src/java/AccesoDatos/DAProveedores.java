package AccesoDatos;

import Entidades.EntidadProveedor;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DAProveedores {
    //ATRUBUTOS

    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    //CONSTRUCTORES
    public DAProveedores() {
        _mensaje = "";
    }

    //método para insertar un proveedor
    public int Insertar(EntidadProveedor proveedor) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{CALL SP_GUARDAR_PROVEEDOR(?,?,?,?,?,?,?,?,?,?,?)}");
            //Parámetros de entrada
            CS.setInt(1, proveedor.getCodProveedor());
            CS.setInt(2, proveedor.getCodEmpresa());
            CS.setString(3, proveedor.getCedula());
            CS.setString(4, proveedor.getNombre());
            CS.setString(5, proveedor.getApellido1());
            CS.setString(6, proveedor.getApellido2());
            CS.setString(7, proveedor.getTelefono());
            CS.setString(8, proveedor.getCorreo());
            CS.setString(9, proveedor.getDireccion());
            CS.setString(10, proveedor.getEstado());
            CS.setString(11, _mensaje);
            //Parámetros de salida
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(11, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(11);

        } catch (Exception e) {
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }//Fin de insertar 

    //método para modificar un proveedor
    public int Modificar(EntidadProveedor proveedor) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _conexion = null;
        try {
            _conexion = getConnection();
            CS = _conexion.prepareCall("{CALL SP_MODIFICAR_PROVEEDOR(?,?,?,?,?,?,?,?,?,?,?)}");
            //Parámetros de entrada
            CS.setInt(1, proveedor.getCodProveedor());
            CS.setInt(2, proveedor.getCodEmpresa());
            CS.setString(3, proveedor.getCedula());
            CS.setString(4, proveedor.getNombre());
            CS.setString(5, proveedor.getApellido1());
            CS.setString(6, proveedor.getApellido2());
            CS.setString(7, proveedor.getTelefono());
            CS.setString(8, proveedor.getCorreo());
            CS.setString(9, proveedor.getDireccion());
            CS.setString(10, proveedor.getEstado());
            CS.setString(11, _mensaje);
            //Parámetros de salida
            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(11, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(11);
        } catch (Exception e) {
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }//Fin de modificar 

    //método para eliminar un proveedor
    public int Eliminar(EntidadProveedor proveedor) throws Exception {
        int resultado = -1;
        CallableStatement cs = null;
        Connection _conexion = null;

        try {
            _conexion = getConnection();
            cs = _conexion.prepareCall("{call SP_ELIMINAR_PROVEEDOR (?,?)}"); ///CAMBIAR
            //Registrar parametros de entrada
            cs.setInt(1, proveedor.getCodProveedor());
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
    public List<EntidadProveedor> ListarProveedores(String condicion) throws SQLException, Exception {
        ResultSet rs = null;
        EntidadProveedor proveedor;
        List<EntidadProveedor> lista = new ArrayList();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT P.COD_PROVEEDOR,P.COD_EMPRESA,NOMBRE_EMPRESA,CEDULA,NOMBRE,APELLIDO1,APELLIDO2, "
                    + "P.TELEFONO,P.CORREO,P.DIRECCION, "
                    + "ESTADO FROM PROVEEDORES P INNER JOIN EMPRESAS E ON E.COD_EMPRESA = P.COD_EMPRESA";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de proveedores
            while (rs.next()) {
                proveedor = new EntidadProveedor(rs.getInt("COD_PROVEEDOR"),
                        rs.getInt("COD_EMPRESA"),
                        rs.getString("NOMBRE_EMPRESA"),
                        rs.getString("CEDULA"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO1"),
                        rs.getString("APELLIDO2"),
                        rs.getString("TELEFONO"),
                        rs.getString("CORREO"),
                        rs.getString("DIRECCION"),
                        rs.getString("ESTADO")
                );
                lista.add(proveedor);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return lista;
    }//Fin listar Proveedores

    //Obtener un proveedor
    public EntidadProveedor ObtenerRegistro(String condicion) throws Exception {
        ResultSet rs = null;
        EntidadProveedor proveedor = new EntidadProveedor();
        String sentencia;
        Connection _conexion = null;
        try {
            //abrir la conexion 
            _conexion = getConnection();
            Statement st = _conexion.createStatement();
            sentencia = "SELECT P.COD_PROVEEDOR,P.COD_EMPRESA,NOMBRE_EMPRESA,CEDULA,NOMBRE,APELLIDO1,APELLIDO2, "
                    + "P.TELEFONO,P.CORREO,P.DIRECCION, "
                    + "ESTADO FROM PROVEEDORES P INNER JOIN EMPRESAS E ON E.COD_EMPRESA = P.COD_EMPRESA";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = st.executeQuery(sentencia);
            //se hace con un if por si devuelve más de un valor
            if (rs.next()) {
                proveedor.setCodProveedor(rs.getInt("COD_PROVEEDOR"));
                proveedor.setCodEmpresa(rs.getInt("COD_EMPRESA"));
                proveedor.setNombreEmpresa(rs.getString("NOMBRE_EMPRESA"));
                proveedor.setCedula(rs.getString("CEDULA"));
                proveedor.setNombre(rs.getString("NOMBRE"));
                proveedor.setApellido1(rs.getString("APELLIDO1"));
                proveedor.setApellido2(rs.getString("APELLIDO2"));
                proveedor.setTelefono(rs.getString("TELEFONO"));
                proveedor.setCorreo(rs.getString("CORREO"));
                proveedor.setDireccion(rs.getString("DIRECCION"));
                proveedor.setEstado(rs.getString("ESTADO"));
                proveedor.setExiste(true);
            } else {
                proveedor.setExiste(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return proveedor;
    }//Fin obtener un proveedor
}
