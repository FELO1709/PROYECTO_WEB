package LogicaNegocio;

import AccesoDatos.DAProveedores;
import Entidades.EntidadProveedor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BLProveedores {
    //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }

    public BLProveedores() {
    }
    
    public int Insertar(EntidadProveedor proveedor) throws Exception{
        int id=0;
        DAProveedores daProveedor;
        try {
            daProveedor=new DAProveedores();
            id=daProveedor.Insertar(proveedor);
            _mensaje=daProveedor.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    public int Modificar(EntidadProveedor proveedor) throws Exception{
        int resultado=0;
        DAProveedores daProveedor;
        try {
            daProveedor=new DAProveedores();
            resultado=daProveedor.Modificar(proveedor);
            _mensaje=daProveedor.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Eliminar(EntidadProveedor proveedor) throws Exception{
        int resultado=0;
        DAProveedores daProveedor;
        try {
            daProveedor=new DAProveedores();
            resultado=daProveedor.Eliminar(proveedor);
            _mensaje=daProveedor.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    
    public List<EntidadProveedor> ListarProveedores(String condicion) throws Exception{
        List<EntidadProveedor> proveedores = new ArrayList();
        DAProveedores daProveedor;
        try {
            daProveedor = new DAProveedores();
            proveedores = daProveedor.ListarProveedores(condicion);
        } catch (Exception ex) {
            throw ex;
        }
        return proveedores;
    }
    
     public EntidadProveedor ObtenerRegistro(String condicion) throws Exception{
        EntidadProveedor resultado;
        DAProveedores daProveedor;
        try {
            daProveedor=new DAProveedores();
            resultado=daProveedor.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje="Proveedor Recuperado exitosamente";
            }else{
               _mensaje="El proveedor no existe"; 
            }
                
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
