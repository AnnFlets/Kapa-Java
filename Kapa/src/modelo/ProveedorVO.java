package modelo;

public class ProveedorVO {
    /**
     * Atributos de la clase
     */
    private int idProveedor;
    private String nombreProveedor;
    private String telefonoProveedor;

    /**
     * Constructo vacío
     */
    public ProveedorVO() {
    }

    /**
     * Getters & Setters
     * @return -> Retorna el valor almacenado en las variables
     */
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }
}