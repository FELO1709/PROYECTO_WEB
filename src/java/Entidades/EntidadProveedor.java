package Entidades;

/**
 *
 * @author luism
 */
public class EntidadProveedor extends EntidadPersona{
    //Hereda los atributos de EntidadPersona
    //Atributos Propios    
    private int codProveedor;
    private int codEmpresa;
    private String nombreEmpresa;
    
    //Métodos GET
    public int getCodProveedor() {
        return codProveedor;
    }
    public int getCodEmpresa() {
        return codEmpresa;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    
    //Métodos SET
    public void setCodProveedor(int codProveedor) {
        this.codProveedor = codProveedor;
    }
    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    //Constructores
    public EntidadProveedor(){
        codProveedor = 0;
        codEmpresa = 0;
        nombreEmpresa = "";
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
    
    public EntidadProveedor(int codigo, int codigoE, String nombreEmp, String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, String direccion, String estado, boolean existe) {
        codProveedor = codigo;
        codEmpresa = codigoE;
        nombreEmpresa = nombreEmp;
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

    public EntidadProveedor(int codigo, int codigoE, String nombreEmp, String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, String direccion, String estado) {
        codProveedor = codigo;
        codEmpresa = codigoE;
        nombreEmpresa = nombreEmp;
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
