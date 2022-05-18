package Entidades;

/**
 *
 * @author luism
 */
public class EntidadMarca {

    //Atributos    
    private int codMarca;
    private String nombreMarca;
    private boolean existe;
   
    //Métodos GET
    public int getCodMarca() {
        return codMarca;
    }
    public String getNombreMarca() {
        return nombreMarca;
    }
    
    public boolean isExiste() {
        return existe;
    }
    
    //Métodos SET
    public void setCodMarca(int codMarca) {
        this.codMarca = codMarca;
    }
    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }
    
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    //Constructores
    public EntidadMarca() {
        codMarca = 0;
        nombreMarca = "";
        existe = false;
    }
    
    public EntidadMarca(int codMarca, String nombreMarca, boolean existe) {
        this.codMarca = codMarca;
        this.nombreMarca = nombreMarca;
        this.existe = existe;
    }   
    
    public EntidadMarca(int codMarca, String nombreMarca) {
        this.codMarca = codMarca;
        this.nombreMarca = nombreMarca;
    } 
}
