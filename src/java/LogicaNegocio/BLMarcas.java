package LogicaNegocio;

import AccesoDatos.DAMarcas;
import Entidades.EntidadMarca;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLMarcas {
    //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }

    public BLMarcas() {
    }
    
     public int Insertar(EntidadMarca marca) throws Exception{
        int id=0;
        DAMarcas daMarca;
        try {
            daMarca=new DAMarcas();
            id=daMarca.Insertar(marca);
            _mensaje=daMarca.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    public int Modificar(EntidadMarca marca) throws Exception{
        int resultado=0;
        DAMarcas daMarca;
        try {
            daMarca=new DAMarcas();
            resultado=daMarca.Modificar(marca);
            _mensaje=daMarca.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Eliminar(EntidadMarca marca) throws Exception{
        int resultado=0;
        DAMarcas daMarca;
        try {
            daMarca=new DAMarcas();
            resultado=daMarca.Eliminar(marca);
            _mensaje=daMarca.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public List<EntidadMarca> ListarMarcas(String condicion) throws Exception{
        List<EntidadMarca> marcas = new ArrayList();
        DAMarcas daMarca;
        try {
            daMarca = new DAMarcas();
            marcas = daMarca.ListarMarcas(condicion);
        } catch (Exception ex) {
            throw ex;
        }
        return marcas;
    }    
    
    public EntidadMarca ObtenerRegistro(String condicion) throws Exception{
        EntidadMarca resultado;
        DAMarcas daMarca;
        try {
            daMarca = new DAMarcas();
            resultado=daMarca.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje="Marca Recuperada exitosamente";
            }else{
               _mensaje="La marca no existe"; 
            }
                
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
   
}
