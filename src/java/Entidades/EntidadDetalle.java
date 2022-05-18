package Entidades;

/**
 *
 * @author luism
 */
public class EntidadDetalle {

    //Atributos
    private int numFactura;
    private int codProducto;
    private String nombreProducto;
    private int cantidadProd;
    private double precio;
    private boolean existe;
    
    //Métodos GET  
    public int getNumFactura() {
        return numFactura;
    }
    
    public int getCodProducto() {
        return codProducto;
    }

    public int getCantidadProd() {
        return cantidadProd;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public boolean isExiste() {
        return existe;
    }

    //Métodos SET    
    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }
    
    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public void setCantidadProd(int cantidadProd) {
        this.cantidadProd = cantidadProd;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    //Constructores
    public EntidadDetalle(){
        numFactura = 0;
        codProducto = 0;
        nombreProducto = "";
        cantidadProd = 0;
        precio = 0;
        existe = false;
    }
    
    public EntidadDetalle(int numFactura, int codProducto, String nombP, int cantidadProd, double precio, boolean existe) {
        this.numFactura = numFactura;
        this.codProducto = codProducto;
        this.nombreProducto = nombP;
        this.cantidadProd = cantidadProd;
        this.precio = precio;
        this.existe = existe;
    }
    
    public EntidadDetalle(int numFactura, int codProducto, String nombP,int cantidadProd, double precio) {
        this.numFactura = numFactura;
        this.codProducto = codProducto;
        this.nombreProducto = nombP;
        this.precio = precio;
        this.cantidadProd = cantidadProd;
    }
}
