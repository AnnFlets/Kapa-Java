package modelo;

public class DetalleFacturaVO {
    /**
     * Atributos de la clase
     */
    private int idDetalleFactura;
    private int cantidadDetalleFactura;
    private double subtotalDetalleFactura;
    private int idFacturaFK;
    private int idProductoFK;

    /**
     * Constructor vacío
     */
    public DetalleFacturaVO() {
    }

    /**
     * Getters & Setters
     * @return -> Retorna el valor almacenado en las variables
     */
    public int getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(int idDetalleFactura) {    
        this.idDetalleFactura = idDetalleFactura;
    }

    public int getCantidadDetalleFactura() {
        return cantidadDetalleFactura;
    }

    public void setCantidadDetalleFactura(int cantidadDetalleFactura) {
        this.cantidadDetalleFactura = cantidadDetalleFactura;
    }

    public double getSubtotalDetalleFactura() {
        return subtotalDetalleFactura;
    }

    public void setSubtotalDetalleFactura(double subtotalDetalleFactura) {
        this.subtotalDetalleFactura = subtotalDetalleFactura;
    }

    public int getIdFacturaFK() {
        return idFacturaFK;
    }

    public void setIdFacturaFK(int idFacturaFK) {
        this.idFacturaFK = idFacturaFK;
    }

    public int getIdProductoFK() {
        return idProductoFK;
    }

    public void setIdProductoFK(int idProductoFK) {
        this.idProductoFK = idProductoFK;
    }
}