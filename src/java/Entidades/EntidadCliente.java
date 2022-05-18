package Entidades;

/**
 *
 * @author luism
 */
public class EntidadCliente extends EntidadPersona {

    //Hereda los atributos de EntidadPersona
    //Atributos Propios    
    private int codCliente;
    
    //Métodos GET
    public int getCodCliente() {
        return codCliente;
    }
    //Métodos SET
    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
    
    //Constructores
    public EntidadCliente(){
        codCliente = 0;
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
    
    public EntidadCliente(int codigo, String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, String direccion, String estado, boolean existe) {
        codCliente = codigo;
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

    public EntidadCliente(int codigo, String cedula, String nombre, String apellido1, String apellido2, String telefono, String correo, String direccion, String estado) {
        codCliente = codigo;
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
