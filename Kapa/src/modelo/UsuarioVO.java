package modelo;

public class UsuarioVO {
    /**
     * Atributos de la clase
     */
    private int idUsuario;
    private String nombreUsuario;
    private String contraseniaUsuario;

    /**
     * Constructo vacío
     */
    public UsuarioVO() {
    }

    /**
     * Getters & Setters
     * @return -> Retorna el valor almacenado en las variables
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }  
}