package LogicaNegocio;

import AccesoDatos.DAMovimientos;
import Entidades.EntidadMovimiento;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BLMovimientos {
    //Atributos
    private String _mensaje;
    
    public String getMensaje(){
        return _mensaje;
    }

    public BLMovimientos() {
    }
    
      public List<EntidadMovimiento> ListarMovimientos(String condicion) throws Exception{
        List<EntidadMovimiento> movimientos =new ArrayList();
        DAMovimientos adMovimiento;
        try {
            adMovimiento=new DAMovimientos();
            movimientos=adMovimiento.ListarMovimientos(condicion);
            
        } catch (Exception e) {
            throw e;
        }
        return movimientos;
    }
    
}
