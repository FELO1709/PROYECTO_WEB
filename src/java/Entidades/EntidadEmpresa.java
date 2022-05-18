package Entidades;

/**
 *
 * @author luism
 */
public class EntidadEmpresa {
    //Atributos
    private int codEmpresa;
    private String cedulaJuridica;
    private String nombreEmpresa;
    private String telefono;
    private String correo;
    private String direccion;
    private boolean existe;
    
    //Métodos GET
    public int getCodEmpresa() {
        return codEmpresa;
    }

    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean isExiste() {
        return existe;
    }
    
    //Métodos SET
    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    //Constructores
    public EntidadEmpresa(){
        codEmpresa = 0;
        cedulaJuridica = "";
        nombreEmpresa = "";
        telefono = "";
        correo = "";
        direccion = "";
        existe = false;
    }
    
    public EntidadEmpresa(int codEmpresa, String cedulaJuridica, String nombreEmpresa, String telefono, String correo, String direccion, boolean existe) {
        this.codEmpresa = codEmpresa;
        this.cedulaJuridica = cedulaJuridica;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.existe = existe;
    }
    
    public EntidadEmpresa(int codEmpresa, String cedulaJuridica, String nombreEmpresa, String telefono, String correo, String direccion) {
        this.codEmpresa = codEmpresa;
        this.cedulaJuridica = cedulaJuridica;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }
    
}
