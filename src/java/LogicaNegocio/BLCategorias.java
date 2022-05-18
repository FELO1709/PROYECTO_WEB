package LogicaNegocio;

import AccesoDatos.DACategorias;
import Entidades.EntidadCategoria;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLCategorias {
    //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }    
    
    public BLCategorias(){
        
    }
    
    public int Insertar(EntidadCategoria categoria) throws Exception{
        int id=0;
        DACategorias daCategoria;
        try {
            daCategoria=new DACategorias();
            id=daCategoria.Insertar(categoria);
            _mensaje=daCategoria.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    public int Modificar(EntidadCategoria categoria) throws Exception{
        int resultado=0;
        DACategorias daCategoria;
        try {
            daCategoria=new DACategorias();
            resultado=daCategoria.Modificar(categoria);
            _mensaje=daCategoria.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Eliminar(EntidadCategoria categoria) throws Exception{
        int resultado=0;
        DACategorias daCategoria;
        try {
            daCategoria=new DACategorias();
            resultado=daCategoria.Eliminar(categoria);
            _mensaje=daCategoria.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public List<EntidadCategoria> ListarCategorias(String condicion) throws Exception{
        List<EntidadCategoria> categorias = new ArrayList();
        DACategorias daCategoria;
        try {
            daCategoria = new DACategorias();
            categorias = daCategoria.ListarCategorias(condicion);
        } catch (Exception ex) {
            throw ex;
        }
        return categorias;
    }    
    
    public EntidadCategoria ObtenerRegistro(String condicion) throws Exception{
        EntidadCategoria resultado;
        DACategorias daCategoria;
        try {
            daCategoria = new DACategorias();
            resultado=daCategoria.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje="Categoría Recuperada exitosamente";
            }else{
               _mensaje="La categoría no existe"; 
            }
                
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
