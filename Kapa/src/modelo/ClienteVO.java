package modelo;

public class ClienteVO {

    /**
     * Atributos de la clase
     */
    private int idCliente;
    private String usuarioCliente;
    private String contraseniaCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String nitCliente;
    private String direccionCliente;
    private String telefonoCliente;
    private String correoCliente;

    /**
     * Constructor vacío
     */
    public ClienteVO() {
    }

    /**
     * Getters & Setters
     *
     * @return -> Retorna el valor almacenado en las variables
     */
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(String usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public String getContraseniaCliente() {
        return contraseniaCliente;
    }

    public void setContraseniaCliente(String contraseniaCliente) {
        this.contraseniaCliente = contraseniaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }
}
