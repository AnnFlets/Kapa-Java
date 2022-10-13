package modelo;

import java.util.ArrayList;

public interface ConsultarProducto {
    public boolean insertarProducto(ProductoVO pvo);
    public ArrayList<ProductoVO> consultarProducto();
    public boolean actualizarProducto(ProductoVO pvo);
    public boolean eliminarProducto(ProductoVO pvo);
    public void reporteProducto();
}