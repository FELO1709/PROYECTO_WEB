package LogicaNegocio;

import AccesoDatos.DAEmpresas;
import Entidades.EntidadEmpresa;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLEmpresas {
     //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }

    public BLEmpresas() {
    }
    
    public int Insertar(EntidadEmpresa empresa) throws Exception{
        int resultado=0;
        DAEmpresas daEmpresa=new DAEmpresas();;
        try {
            resultado=daEmpresa.Insertar(empresa);
            _mensaje=daEmpresa.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Modificar(EntidadEmpresa empresa) throws Exception{
        int resultado=01;
        DAEmpresas daEmpresa=new DAEmpresas();
        try {
            resultado=daEmpresa.Modificar(empresa);
            _mensaje=daEmpresa.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public int Eliminar(EntidadEmpresa empresa) throws Exception{
        int resultado=0;
        DAEmpresas daEmpresa=new DAEmpresas();
        try {
            resultado=daEmpresa.Eliminar(empresa);
            _mensaje=daEmpresa.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    public List<EntidadEmpresa> ListarEmpresas(String condicion) throws Exception{
        List<EntidadEmpresa> empresas = new ArrayList();
        DAEmpresas daEmpresa= new DAEmpresas();
        try {
            empresas = daEmpresa.ListarEmpresas(condicion);
        } catch (Exception ex) {
            throw ex;
        }
        return empresas;
    }
    
    public EntidadEmpresa ObtenerRegistro(String condicion) throws Exception{
        EntidadEmpresa resultado;
        DAEmpresas daEmpresa=new DAEmpresas();
        try {
            resultado=daEmpresa.ObtenerRegistro(condicion);
            if(resultado.isExiste()){
                _mensaje="Empresa Recuperada exitosamente";
            }else{
               _mensaje="La empresa no existe"; 
            }
                
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
   
}
