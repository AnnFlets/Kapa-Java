package modelo;

import java.util.ArrayList;

public interface ConsultarProveedor {
    public boolean insertarProveedor(ProveedorVO prvo);
    public ArrayList<ProveedorVO> consultarProveedor();
    public boolean actualizarProveedor(ProveedorVO prvo);
    public boolean eliminarProveedor(ProveedorVO prvo);
    public void reporteProveedor();
}