package AccesoDatos;

import Entidades.EntidadProducto;
import java.sql.*;  //importar las clase de jdbc
import java.util.*; //para manejo de listas

//para utilizar los métodos de otra clase como propios
import static AccesoDatos.ClaseConexion.getConnection;

public class DAProductos {

    //ATRUBUTOS
    private String _mensaje;

    //PROPIEDADES
    public String getMensaje() {
        return _mensaje;
    }

    //CONSTRUCTORES
    public DAProductos() {
        _mensaje = "";
    }

    //"Metodos"
    public int Insertar(EntidadProducto EntidadProducto) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _Conexion = null;

        try {
            _Conexion = ClaseConexion.getConnection();
            CS = _Conexion.prepareCall("{call SP_GUARDAR_PRODUCTOS(?,?,?,?,?,?,?,?,?,?,?)}");

            CS.setInt(1, EntidadProducto.getCodProducto());
            CS.setInt(2, EntidadProducto.getCodCategoria());
            CS.setInt(3, EntidadProducto.getCodMarca());
            CS.setString(4, EntidadProducto.getNombre());
            CS.setString(5, EntidadProducto.getDescripcion());
            CS.setInt(6, EntidadProducto.getExistencia());
            CS.setDouble(7, EntidadProducto.getPrecioCompra());
            CS.setDouble(8, EntidadProducto.getPrecioVenta());
            CS.setDouble(9, EntidadProducto.getImpuesto());
            CS.setInt(10, EntidadProducto.getBorrado());
            CS.setString(11, _mensaje);

            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(11, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(11);

        } catch (Exception ex) {
            resultado = -1;
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return resultado;
    }//fin de insertar

    public int Modificar(EntidadProducto EntidadProducto) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _Conexion = null;
        try {
            _Conexion = ClaseConexion.getConnection();
            CS = _Conexion.prepareCall("{call SP_MODIFICAR_PRODUCTOS(?,?,?,?,?,?,?,?,?,?,?)}");

            CS.setInt(1, EntidadProducto.getCodProducto());
            CS.setInt(2, EntidadProducto.getCodCategoria());
            CS.setInt(3, EntidadProducto.getCodMarca());
            CS.setString(4, EntidadProducto.getNombre());
            CS.setString(5, EntidadProducto.getDescripcion());
            CS.setInt(6, EntidadProducto.getExistencia());
            CS.setDouble(7, EntidadProducto.getPrecioCompra());
            CS.setDouble(8, EntidadProducto.getPrecioVenta());
            CS.setDouble(9, EntidadProducto.getImpuesto());
            CS.setInt(10, EntidadProducto.getBorrado());
            CS.setString(11, _mensaje);

            CS.registerOutParameter(1, Types.INTEGER);
            CS.registerOutParameter(11, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer los parámetros de salida
            _mensaje = CS.getString(11);

        } catch (Exception ex) {
            resultado = -1;
            throw ex;

        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return resultado;
    }//fin de modificar

    public int Eliminar(EntidadProducto EntidadProducto) throws Exception {
        int resultado = -1;
        CallableStatement CS = null;
        Connection _Conexion = null;
        try {
            _Conexion = ClaseConexion.getConnection();
            CS = _Conexion.prepareCall("{call SP_ELIMINAR_PRODUCTO(?,?)}");

            CS.setInt(1, EntidadProducto.getCodProducto());
            CS.setString(2, _mensaje);
            CS.registerOutParameter(2, Types.VARCHAR);
            //Registrar parámetros de salida
            CS.registerOutParameter(2, Types.VARCHAR);
            //Ejecutar
            resultado = CS.executeUpdate();
            //Leer parámetros de salida
            _mensaje = CS.getString(2);

        } catch (Exception ex) {
            resultado = -1;
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return resultado;
    }//fin de eliminar

    //Listar Registros en una Lista
    public List<EntidadProducto> ListarProductos(String condicion) throws SQLException, Exception {
        ResultSet rs = null;
        EntidadProducto producto;
        List<EntidadProducto> lista = new ArrayList();
        Connection _cnn = null;
        try {
            //abrir la conexion 
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT P.COD_PRODUCTO,P.COD_CATEGORIA,C.NOMBRE_CATEGORIA,P.COD_MARCA,"
                    + "M.NOMBRE_MARCA,NOMBRE,DESCRIPCION,EXISTENCIA,PRECIO_COMPRA,PRECIO_VENTA,IMPUESTO,"
                    + "BORRADO FROM PRODUCTOS P "
                    + "INNER JOIN CATEGORIAS C ON C.COD_CATEGORIA = P.COD_CATEGORIA "
                    + "INNER JOIN MARCAS M ON M.COD_MARCA = P.COD_MARCA";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            //recorrer el rs para cargar la lista de productos
            while (rs.next()) {
                producto = new EntidadProducto(rs.getInt("COD_PRODUCTO"),
                        rs.getInt("COD_CATEGORIA"),
                        rs.getString("NOMBRE_CATEGORIA"),
                        rs.getInt("COD_MARCA"),
                        rs.getString("NOMBRE_MARCA"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"),
                        rs.getInt("EXISTENCIA"),
                        rs.getDouble("PRECIO_COMPRA"),
                        rs.getDouble("PRECIO_VENTA"),
                        rs.getDouble("IMPUESTO"),
                        rs.getInt("BORRADO")
                );
                lista.add(producto);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return lista;
    }//Fin listar Productos

    public EntidadProducto ObtenerRegistro(String condicion) throws SQLException, Exception {
        ResultSet RS = null;
        EntidadProducto producto = new EntidadProducto();
        String vlc_Sentencia;
        Connection _Conexion = null;
        vlc_Sentencia = "SELECT P.COD_PRODUCTO,P.COD_CATEGORIA,C.NOMBRE_CATEGORIA,P.COD_MARCA,"
                + "M.NOMBRE_MARCA,NOMBRE,DESCRIPCION,EXISTENCIA,PRECIO_COMPRA,PRECIO_VENTA,IMPUESTO,"
                + "BORRADO FROM PRODUCTOS P "
                + "INNER JOIN CATEGORIAS C ON C.COD_CATEGORIA = P.COD_CATEGORIA "
                + "INNER JOIN MARCAS M ON M.COD_MARCA = P.COD_MARCA";

        if (!condicion.equals("")) {
            vlc_Sentencia = String.format("%s WHERE %s", vlc_Sentencia, condicion);
        }

        try {
            _Conexion = ClaseConexion.getConnection();
            Statement ST = _Conexion.createStatement();
            RS = ST.executeQuery(vlc_Sentencia);
            if (RS.next()) {
                producto.setCodProducto(RS.getInt(1));
                producto.setCodCategoria(RS.getInt(2));
                producto.setNombreCategoria(RS.getString(3));
                producto.setCodMarca(RS.getInt(4));
                producto.setNombreMarca(RS.getString(5));
                producto.setNombre(RS.getString(6));
                producto.setDescripcion(RS.getString(7));
                producto.setExistencia(RS.getInt(8));
                producto.setPrecioCompra(RS.getDouble(9));
                producto.setPrecioVenta(RS.getDouble(10));
                producto.setImpuesto(RS.getDouble(11));
                producto.setBorrado(RS.getInt(12));

                producto.setExiste(true);

            } else {
                producto.setExiste(false);

            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return producto;
    }
}
