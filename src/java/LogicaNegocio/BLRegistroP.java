package LogicaNegocio;

import AccesoDatos.DARegistrosP;
import Entidades.EntidadRegistroP;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLRegistroP {
     //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }

    public BLRegistroP() {
    }
    
    public List<EntidadRegistroP> ListarRegistrosPagos() throws Exception{
        List<EntidadRegistroP> registrosPagos=new ArrayList();
        DARegistrosP adRegistroPago;
        try {
            adRegistroPago=new DARegistrosP();
            registrosPagos=adRegistroPago.ListarRegistrosPagos();
            
        } catch (Exception e) {
            throw e;
        }
        return registrosPagos;
    }
}
