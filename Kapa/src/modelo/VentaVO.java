package modelo;

public class VentaVO {
    /**
     * Atributos de la clase
     */
    private int idFactura;
    private int numeroFactura;
    private String serieFactura;
    private String fechaFactura;
    private double totalFactura;
    private String tipoPagoFactura;
    private String estadoFactura;
    private int idClienteFK;

    /**
     * Constructor vacío
     */
    public VentaVO() {
    }

    /**
     * Getters & Setters
     * @return -> Retorna el valor almacenado en las variables
     */
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getSerieFactura() {
        return serieFactura;
    }

    public void setSerieFactura(String serieFactura) {
        this.serieFactura = serieFactura;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getTipoPagoFactura() {
        return tipoPagoFactura;
    }

    public void setTipoPagoFactura(String tipoPagoFactura) {
        this.tipoPagoFactura = tipoPagoFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public int getIdClienteFK() {
        return idClienteFK;
    }

    public void setIdClienteFK(int idClienteFK) {
        this.idClienteFK = idClienteFK;
    }
}