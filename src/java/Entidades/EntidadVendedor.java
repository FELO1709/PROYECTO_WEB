package Entidades;

/**
 *
 * @author luism
 */
public class EntidadVendedor extends EntidadPersona {
    //Hereda los atributos de EntidadPersona
    //Atributos Propios    
    private int codVendedor;
    
    //Métodos GET
    public int getCodVendedor() {
        return codVendedor;
    }
    //Métodos SET
    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }
    
    //Constructores
    public EntidadVendedor(){
        codVendedor = 0;
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
    
    public EntidadVendedor(int codigo, String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, String direccion, String estado, boolean existe) {
        codVendedor = codigo;
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

    public EntidadVendedor(int codigo, String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, String direccion, String estado) {
        codVendedor = codigo;
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
