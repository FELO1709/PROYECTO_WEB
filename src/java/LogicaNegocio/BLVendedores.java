package LogicaNegocio;

import AccesoDatos.DAVendedores;
import Entidades.EntidadVendedor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLVendedores {
    //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }

    public BLVendedores() {
    }
    
     public int Insertar(EntidadVendedor vendedor) throws Exception{
        int id=0;
        DAVendedores daVendedor;
        try {
            daVendedor=new DAVendedores();
            id=daVendedor.Insertar(vendedor);
            _mensaje=daVendedor.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    public int Modificar(EntidadVendedor vendedor) throws Exception{
        int resultado=0;
        DAVendedores daVendedor;
        try {
            daVendedor=new DAVendedores();
            resultado=daVendedor.Modificar(vendedor);
            _mensaje=daVendedor.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Eliminar(EntidadVendedor vendedor) throws Exception{
        int resultado=0;
        DAVendedores daVendedor;
        try {
            daVendedor=new DAVendedores();
            resultado=daVendedor.Eliminar(vendedor);
            _mensaje=daVendedor.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public List<EntidadVendedor> ListarVendedores(String condicion) throws Exception{
        List<EntidadVendedor> vendedores = new ArrayList();
        DAVendedores daVendedor;
        try {
            daVendedor = new DAVendedores();
            vendedores = daVendedor.ListarVendedores(condicion);
        } catch (Exception ex) {
            throw ex;
        }
        return vendedores;
    }
    
    public EntidadVendedor ObtenerRegistro(String condicion) throws Exception{
        EntidadVendedor resultado;
        DAVendedores daVendedor;
        try {
            daVendedor=new DAVendedores();
            resultado=daVendedor.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje="Vendedor Recuperado exitosamente";
            }else{
               _mensaje="El vendedor no existe"; 
            }
                
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}