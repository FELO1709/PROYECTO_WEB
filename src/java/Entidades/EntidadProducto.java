package Entidades;

/**
 *
 * @author luism
 */
public class EntidadProducto {

    //Atributos
    private int codProducto;
    private int codCategoria;
    private String nombreCategoria;
    private int codMarca;
    private String nombreMarca;
    private String nombre;
    private String descripcion;
    private int existencia;
    private double precioCompra;
    private double precioVenta;
    private double impuesto;
    private int borrado;
    private boolean existe;
    
    //Métodos GET
    public int getCodProducto() {
        return codProducto;
    }
     
    public int getCodCategoria() {
        return codCategoria;
    }
    
    public String getNombreCategoria() {
        return nombreCategoria;
    }
     
    public int getCodMarca() {
        return codMarca;
    }
    
    public String getNombreMarca() {
        return nombreMarca;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getExistencia() {
        return existencia;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public int getBorrado() {
        return borrado;
    }
    
    public boolean isExiste() {
        return existe;
    }
    
    //Métodos SET
    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }
    
    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }
    
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    public void setCodMarca(int codMarca) {
        this.codMarca = codMarca;
    }
    
    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }
    
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    //Constructores
    public EntidadProducto(){
        codProducto = 0;
        codCategoria = 0;
        nombreCategoria = "";
        codMarca = 0;
        nombreMarca = "";
        nombre = "";
        descripcion = "";
        existencia = 0;
        precioCompra = 0.0;
        precioVenta = 0.0;
        impuesto = 0.0;
        borrado = 0;
        existe = false;
    }
    
    public EntidadProducto(int codProducto, int codCategoria, String nombCat, int codMarca, String nombM, String nombre, String descripcion, int existencia, double precioCompra, double precioVenta, double impuesto, int borrado, boolean existe) {
        this.codProducto = codProducto;
        this.codCategoria = codCategoria;
        this.nombreCategoria = nombCat;
        this.codMarca = codMarca;
        this.nombreMarca = nombM;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.impuesto = impuesto;
        this.borrado = borrado;
        this.existe = existe;
    }
    
    public EntidadProducto(int codProducto, int codCategoria, String nombCat, int codMarca, String nombM, String nombre, String descripcion, int existencia, double precioCompra, double precioVenta, double impuesto, int borrado) {
        this.codProducto = codProducto;
        this.codCategoria = codCategoria;
        this.nombreCategoria = nombCat;
        this.codMarca = codMarca;
        this.nombreMarca = nombM;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.impuesto = impuesto;
        this.borrado = borrado;
    }
}
