package modelo;

import conexion.Conector;

public class ErrorDAO implements ConsultarError{

    @Override
    public boolean insertarError(ErrorVO evo) {
        Conector conector = new Conector();
        try{
            conector.conectar();
            String query = "INSERT INTO error_tabla(descripcion_error, "
                    + "fecha_error) "
                    + "VALUES('" + evo.getDescripcionError() + "', "
                    + "'" + evo.getFechaError() + "')";
            conector.consultasMultiples(query);
        }catch(Exception e){
            System.err.println("Error[Insertar-Error]: " + e.getMessage());
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }
}