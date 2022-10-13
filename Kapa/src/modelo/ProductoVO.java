package modelo;

public class ProductoVO {

    /**
     * Atributos de la clase
     */
    private int idProducto;
    private String descripcionProducto;
    private String marcaProducto;
    private String presentacionProducto;
    private String categoriaProducto;
    private double precioCompraProducto;
    private double precioVentaProducto;
    private int existenciaProducto;
    private String imgProducto;
    private int idProveedorFK;

    /**
     * Constructor vacío
     */
    public ProductoVO() {
    }

    /**
     * Getters & Setters
     *
     * @return -> Retorna el valor almacenado en las variables
     */
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public String getPresentacionProducto() {
        return presentacionProducto;
    }

    public void setPresentacionProducto(String presentacionProducto) {
        this.presentacionProducto = presentacionProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public double getPrecioCompraProducto() {
        return precioCompraProducto;
    }

    public void setPrecioCompraProducto(double precioCompraProducto) {
        this.precioCompraProducto = precioCompraProducto;
    }

    public double getPrecioVentaProducto() {
        return precioVentaProducto;
    }

    public void setPrecioVentaProducto(double precioVentaProducto) {
        this.precioVentaProducto = precioVentaProducto;
    }

    public int getExistenciaProducto() {
        return existenciaProducto;
    }

    public void setExistenciaProducto(int existenciaProducto) {
        this.existenciaProducto = existenciaProducto;
    }

    public String getImgProducto() {
        return imgProducto;
    }

    public void setImgProducto(String imgProducto) {
        this.imgProducto = imgProducto;
    }

    public int getIdProveedorFK() {
        return idProveedorFK;
    }

    public void setIdProveedorFK(int idProveedorFK) {
        this.idProveedorFK = idProveedorFK;
    }
}
