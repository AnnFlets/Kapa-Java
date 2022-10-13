package modelo;

import conexion.Conector;
import extras.Extras;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO implements ConsultarUsuario{
    
    ErrorVO evo = new ErrorVO();
    ErrorDAO edao = new ErrorDAO();
    Extras extras = new Extras();

    @Override
    public ArrayList<UsuarioVO> consultarUsuario() {
        Conector conector = new Conector();
        ArrayList<UsuarioVO> informacionUsuario = new ArrayList<>();
        try{
            conector.conectar();
            String query = "SELECT "
                    + "a.id_usuario, "
                    + "a.nombre_usuario, "
                    + "a.contrasenia_usuario "
                    + "FROM administrador a";
            ResultSet resultSet = conector.consultaDatos(query);
            while(resultSet.next()){
                UsuarioVO uvo = new UsuarioVO();
                uvo.setIdUsuario(resultSet.getInt(1));
                uvo.setNombreUsuario(resultSet.getString(2));
                uvo.setContraseniaUsuario(resultSet.getString(3));
                informacionUsuario.add(uvo);
            }
            conector.desconectar();
        }catch(Exception e){
            evo.setDescripcionError("[Consultar-Usuario]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
        return informacionUsuario;
    }
}