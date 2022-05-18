
package LogicaNegocio;

import AccesoDatos.DAProductos;
import Entidades.EntidadProducto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLProductos {

  //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }

    public BLProductos() {
    }
    
    
     public int Insertar(EntidadProducto producto) throws Exception{
        int id=0;
         DAProductos adProducto;
        try {
            adProducto=new DAProductos();
            id=adProducto.Insertar(producto);
            _mensaje=adProducto.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    public int Modificar(EntidadProducto producto) throws Exception{
        int resultado=0;
        DAProductos adProducto;
        try {
            adProducto=new DAProductos();
            resultado=adProducto.Modificar(producto);
            _mensaje=adProducto.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Eliminar(EntidadProducto producto) throws Exception{
        int resultado=0;
        DAProductos adProducto;
        try {
            adProducto=new DAProductos();
            resultado=adProducto.Eliminar(producto);
            _mensaje=adProducto.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    
    public List<EntidadProducto> ListarProductos(String condicion) throws Exception{
        List<EntidadProducto> productos = new ArrayList();
        DAProductos daProducto;
        try {
            daProducto = new DAProductos();
            productos = daProducto.ListarProductos(condicion);
        } catch (Exception ex) {
            throw ex;
        }
        return productos;
    }
    
     public EntidadProducto ObtenerRegistro(String condicion) throws Exception{
        EntidadProducto resultado;
        DAProductos daProducto;
        try {
            daProducto = new DAProductos();
            resultado=daProducto.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje="Producto Recuperado exitosamente";
            }else{
               _mensaje="El producto no existe"; 
            }
                
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
