package Entidades;

/**
 *
 * @author luism
 */
public class EntidadPersona {
    
    //Atributos
    protected String cedula;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
    protected String telefono;
    protected String correo;
    protected String direccion;
    protected String estado;
    protected boolean existe;
    
    //Métodos GET
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
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

    public String getEstado() {
        return estado;
    }
    
    public boolean isExiste() {
        return existe;
    }
    
    //Métodos SET
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    //Constructores
    public EntidadPersona(){
        cedula = "";
        nombre = "";
        apellido1 = "";
        apellido2 = "";
        telefono = "";
        correo = "";
        direccion = "";
        estado = "";
        existe = false;      
    }
    
    public EntidadPersona(String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, String direccion, String estado, boolean existe) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.estado = estado;
        this.existe = existe;
    }
    
    public EntidadPersona(String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, String direccion, String estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.estado = estado;
    }
}
