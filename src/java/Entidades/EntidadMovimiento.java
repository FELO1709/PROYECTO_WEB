package Entidades;

import java.util.Date;

/**
 *
 * @author luism
 */
public class EntidadMovimiento {

    //Atributos
    private int codMovimiento;
    private int numFactura;
    private Date fechaMov;
    private int existenciaInv;
    private int cantMov;
    private int existenciaAct;
    
    //Métodos GET
    public int getCodMovimiento() {
        return codMovimiento;
    }
    
    public int getNumFactura() {
        return numFactura;
    }

    public Date getFechaMov() {
        return fechaMov;
    }

    public int getExistenciaInv() {
        return existenciaInv;
    }
    
    public int getCantMov() {
        return cantMov;
    }
    
    public int getExistenciaAct() {
        return existenciaAct;
    }


    //Métodos SET
    public void setCodMovimiento(int codMovimiento) {
        this.codMovimiento = codMovimiento;
    }
    
    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public void setFechaMov(Date fechaMov) {
        this.fechaMov = fechaMov;
    }

    public void setExistenciaInv(int existenciaInv) {
        this.existenciaInv = existenciaInv;
    }
    
    public void setCantMov(int cantMov) {
        this.cantMov = cantMov;
    }
    
    public void setExistenciaAct(int existenciaAct) {
        this.existenciaAct = existenciaAct;
    }  


    //Constructores
    public EntidadMovimiento(){
        codMovimiento = 0;
        numFactura = 0;
        fechaMov = new Date();
        existenciaInv = 0;
        cantMov = 0;
        existenciaAct = 0;       
    }
    
    public EntidadMovimiento(int codMovimiento, int numFactura, Date fechaMov, int existenciaInv, int cantMov, int existenciaAct) {
        this.codMovimiento = codMovimiento;
        this.numFactura = numFactura;
        this.fechaMov = fechaMov;
        this.existenciaInv = existenciaInv;
        this.cantMov = cantMov;
        this.existenciaAct = existenciaAct;
    }
    
}
