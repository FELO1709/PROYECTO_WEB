package LogicaNegocio;

import AccesoDatos.DAPagos;
import Entidades.EntidadPago;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLPagos {
        //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }

    public BLPagos() {
    }
    
      public List<EntidadPago> ListarPagos(String condicion) throws Exception{
        List<EntidadPago> pagos =new ArrayList();
        DAPagos adPago;
        try {
            adPago=new DAPagos();
            pagos=adPago.ListarPagos(condicion);
            
        } catch (Exception e) {
            throw e;
        }
        return pagos;
    }
}
