package Entidades;

/**
 *
 * @author luism
 */
public class EntidadCategoria {
    //Atributos    
    private int codCategoria;
    private String nombreCategoria;
    private boolean existe;
    
    //Métodos GET
    public int getCodCategoria() {
        return codCategoria;
    }
    public String getNombreCategoria() {
        return nombreCategoria;
    }
    public boolean isExiste() {
        return existe;
    }
    
    //Métodos SET
    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    //Constructores
    public EntidadCategoria() {
        codCategoria = 0;
        nombreCategoria = "";
        existe = false;
    }
    
    public EntidadCategoria(int codCategoria, String nombreCategoria, boolean existe) {
        this.codCategoria = codCategoria;
        this.nombreCategoria = nombreCategoria;
        this.existe = existe;           
    }   
    
    public EntidadCategoria(int codCategoria, String nombreCategoria) {
        this.codCategoria = codCategoria;
        this.nombreCategoria = nombreCategoria; 
    }
}
