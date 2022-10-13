package modelo;

public class ErrorVO {
    /**
     * Atributos de la clase
     */
    String descripcionError;
    String fechaError;

    /**
     * Constructor vacío
     */
    public ErrorVO() {
    }

    /**
     * Getters & Setters
     * @return -> Retorna el valor almacenado en las variables
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getFechaError() {
        return fechaError;
    }

    public void setFechaError(String fechaError) {
        this.fechaError = fechaError;
    }
}