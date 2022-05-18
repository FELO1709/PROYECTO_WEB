package LogicaNegocio;

import AccesoDatos.DAClientes;
import Entidades.EntidadCliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLClientes {
    //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }
    
    public BLClientes(){
        
    }
    
     public int Insertar(EntidadCliente cliente) throws Exception{
        int id=0;
        DAClientes adcliente;
        try {
            adcliente=new DAClientes();
            id=adcliente.Insertar(cliente);
            _mensaje=adcliente.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    public int Modificar(EntidadCliente cliente) throws Exception{
        int resultado=0;
        DAClientes adcliente;
        try {
            adcliente=new DAClientes();
            resultado=adcliente.Modificar(cliente);
            _mensaje=adcliente.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Eliminar(EntidadCliente cliente) throws Exception{
        int resultado=0;
        DAClientes adcliente;
        try {
            adcliente=new DAClientes();
            resultado=adcliente.Eliminar(cliente);
            _mensaje=adcliente.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public List<EntidadCliente> ListarClientes(String condicion) throws Exception{
        List<EntidadCliente> clientes = new ArrayList();
        DAClientes daCliente;
        try {
            daCliente = new DAClientes();
            clientes = daCliente.ListarClientes(condicion);
        } catch (Exception ex) {
            throw ex;
        }
        return clientes;
    }
    
     public EntidadCliente ObtenerRegistro(String condicion) throws Exception{
        EntidadCliente resultado;
        DAClientes adcliente;
        try {
            adcliente=new DAClientes();
            resultado=adcliente.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje="Cliente Recuperado exitosamente";
            }else{
               _mensaje="El cliente no existe"; 
            }
                
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
