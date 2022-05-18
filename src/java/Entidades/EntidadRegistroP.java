package Entidades;

import java.util.Date;

/**
 *
 * @author luism
 */
public class EntidadRegistroP {
    //Atributos
    private int codRegistroP;
    private int codPago;
    private Date fechaRegistro;
    private double total;
    private double saldoPendiente;
    
    //Métodos GET
    public int getCodRegistroP() {
        return codRegistroP;
    }
    
    public int getCodPago() {
        return codPago;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public double getTotal() {
        return total;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    //Métodos SET
    public void setCodRegistroP(int codRegistroP) {
        this.codRegistroP = codRegistroP;
    } 
    
    public void setCodPago(int codPago) {
        this.codPago = codPago;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    //Constructores
    public EntidadRegistroP(){
        codRegistroP = 0;
        codPago = 0;
        fechaRegistro = new Date();
        total = 0.0;
        saldoPendiente = 0.0;
    }
    
    public EntidadRegistroP(int codRegistroP, int codPago, Date fechaRegistro, double total, double saldoPendiente) {
        this.codRegistroP = codRegistroP;
        this.codPago = codPago;
        this.fechaRegistro = fechaRegistro;
        this.total = total;
        this.saldoPendiente = saldoPendiente;
    }
    
}
