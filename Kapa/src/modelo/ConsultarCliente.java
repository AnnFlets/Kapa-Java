package modelo;

import java.util.ArrayList;

public interface ConsultarCliente {
    public boolean insertarCliente(ClienteVO cvo);
    public ArrayList<ClienteVO> consultarCliente();
    public boolean actualizarCliente(ClienteVO cvo);
    public boolean eliminarCliente(ClienteVO cvo);
    public void reporteCliente();
}