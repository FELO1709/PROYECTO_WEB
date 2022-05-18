package Entidades;

import java.util.Date;

/**
 *
 * @author luism
 */
public class EntidadPago {
    //Atributos
    private int codPago;
    private int numFactura;
    private Date fechaPago;
    private double totalPago;
    private double impuesto;
    private boolean existe;

    //Métodos GET
    public int getCodPago() {
        return codPago;
    }

    public int getNumFactura() {
        return numFactura;
    }
    
    public Date getFechaPago() {
        return fechaPago;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public double getImpuesto() {
        return impuesto;
    }
    
    public boolean isExiste() {
        return existe;
    }

    //Métodos SET
    public void setCodPago(int codPago) {
        this.codPago = codPago;
    }
    
    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }
    
    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    //Constructores
    public EntidadPago(){
        codPago = 0;
        fechaPago = new Date();
        totalPago = 0.0;
        impuesto = 0.0;
        existe = false;
    }
    
    public EntidadPago(int codPago, int numFactura, Date fechaPago, double totalPago, double impuesto, boolean existe) {
        this.codPago = codPago;
        this.numFactura = numFactura;
        this.fechaPago = fechaPago;
        this.totalPago = totalPago;
        this.impuesto = impuesto;
        this.existe = existe;
    }
    
    public EntidadPago(int codPago, int numFactura, Date fechaPago, double totalPago, double impuesto) {
        this.codPago = codPago;
        this.numFactura = numFactura;
        this.fechaPago = fechaPago;
        this.totalPago = totalPago;
        this.impuesto = impuesto;
    }
    
}
