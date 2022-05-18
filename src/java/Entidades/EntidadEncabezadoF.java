package Entidades;

import java.sql.Date;

/**
 *
 * @author luism
 */
public class EntidadEncabezadoF {

    //Atributos
    private int numFactura;
    private int codVendedor;
    private String nombreVendedor;
    private Date fechaFactura;
    private int codCliente;
    private String cedulaCliente;
    private String nombreCliente;
    private String estado;
    private double totalFactura;
    private String tipoFactura;
    private boolean existe;

    //Métodos GET
    public int getNumFactura() {
        return numFactura;
    }

    public int getCodVendedor() {
        return codVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isExiste() {
        return existe;
    }

    public double getTotal() {
        return totalFactura;
    }
    
    public String getTipoFactura() {
        return tipoFactura;
    }

    //Métodos SET
    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public void setTotal(double totalFactura) {
        this.totalFactura = totalFactura;
    }
    
    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    //Constructores
    public EntidadEncabezadoF() {
        numFactura = 0;
        codVendedor = 0;
        nombreVendedor = "";
        fechaFactura = null;
        codCliente = 0;
        cedulaCliente = "";
        nombreCliente = "";
        estado = "";
        totalFactura = 0;
        tipoFactura = "";
        existe = false;
    }

    public EntidadEncabezadoF(int numFactura, int codVendedor, String nombreVendedor, Date fechaFactura, int codCliente, String ced, String nombreCliente, String estado, double totalF, String tipoF, boolean existe) {
        this.numFactura = numFactura;
        this.codVendedor = codVendedor;
        this.nombreVendedor = nombreVendedor;
        this.fechaFactura = fechaFactura;
        this.codCliente = codCliente;
        this.cedulaCliente = ced;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.totalFactura = totalF;
        this.tipoFactura = tipoF;
        this.existe = existe;
    }

    public EntidadEncabezadoF(int numFactura, int codVendedor, String nombreVendedor, Date fechaFactura, int codCliente, String ced, String nombreCliente, String estado, double totalF, String tipoF) {
        this.numFactura = numFactura;
        this.codVendedor = codVendedor;
        this.nombreVendedor = nombreVendedor;
        this.fechaFactura = fechaFactura;
        this.codCliente = codCliente;
        this.cedulaCliente = ced;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.totalFactura = totalF;
        this.tipoFactura = tipoF;
    }

}
