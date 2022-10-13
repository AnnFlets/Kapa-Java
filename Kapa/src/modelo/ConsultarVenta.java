package modelo;

import java.util.ArrayList;

public interface ConsultarVenta {
    public ArrayList<VentaVO> consultarVenta();
    public boolean actualizarVenta(VentaVO vvo);
    public void reporteVenta();
}