package LogicaNegocio;

import AccesoDatos.DADetalles;
import Entidades.EntidadDetalle;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLDetalles {

    //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }
       
    public BLDetalles() {
    }

     public int Eliminar(EntidadDetalle detalle) throws Exception{
        int Resultado = 0;
        
        try {
            DADetalles DA = new DADetalles();
            Resultado = DA.Eliminar(detalle);
        } catch (Exception ex) {
            Resultado = -1;
            throw ex;
        }
        return Resultado;
    }
    
    public List<EntidadDetalle> ListarDetalles(String condicion) throws Exception {
        List<EntidadDetalle> detalles = new ArrayList();
        DADetalles addetalle;

        try {
            addetalle = new DADetalles();
            detalles = addetalle.ListarDetalles(condicion);

        } catch (Exception e) {
            throw e;
        }
        return detalles;
    }
}
