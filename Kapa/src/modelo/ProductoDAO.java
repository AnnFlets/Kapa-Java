package modelo;

import conexion.Conector;
import extras.Extras;
import java.sql.ResultSet;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ProductoDAO implements ConsultarProducto {

    ErrorVO evo = new ErrorVO();
    ErrorDAO edao = new ErrorDAO();
    Extras extras = new Extras();
    public JasperViewer jasperViewer;

    @Override
    public boolean insertarProducto(ProductoVO pvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "INSERT INTO producto (descripcion_producto, "
                    + "marca_producto, "
                    + "presentacion_producto, "
                    + "categoria_producto, "
                    + "precio_compra_producto, "
                    + "precio_venta_producto, "
                    + "existencia_producto, "
                    + "id_proveedor_fk) "
                    + "VALUES ('" + pvo.getDescripcionProducto() + "', "
                    + "'" + pvo.getMarcaProducto() + "', "
                    + "'" + pvo.getPresentacionProducto() + "', "
                    + "'" + pvo.getCategoriaProducto() + "', "
                    + "" + pvo.getPrecioCompraProducto() + ", "
                    + "" + pvo.getPrecioVentaProducto() + ", "
                    + "" + pvo.getExistenciaProducto() + ", "
                    + "" + pvo.getIdProveedorFK() + ")";
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Insertar-Producto]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public ArrayList<ProductoVO> consultarProducto() {
        Conector conector = new Conector();
        ArrayList<ProductoVO> informacionProducto = new ArrayList<>();
        try {
            conector.conectar();
            String query = "SELECT "
                    + "p.id_producto, "
                    + "p.descripcion_producto, "
                    + "p.marca_producto, "
                    + "p.presentacion_producto, "
                    + "p.categoria_producto, "
                    + "p.precio_compra_producto, "
                    + "p.precio_venta_producto, "
                    + "p.existencia_producto, "
                    + "p.img_producto, "
                    + "p.id_proveedor_fk "
                    + "FROM producto p";
            ResultSet resultSet = conector.consultaDatos(query);
            while (resultSet.next()) {
                ProductoVO pvo = new ProductoVO();
                pvo.setIdProducto(resultSet.getInt(1));
                pvo.setDescripcionProducto(resultSet.getString(2));
                pvo.setMarcaProducto(resultSet.getString(3));
                pvo.setPresentacionProducto(resultSet.getString(4));
                pvo.setCategoriaProducto(resultSet.getString(5));
                pvo.setPrecioCompraProducto(resultSet.getDouble(6));
                pvo.setPrecioVentaProducto(resultSet.getDouble(7));
                pvo.setExistenciaProducto(resultSet.getInt(8));
                pvo.setImgProducto(resultSet.getString(9));
                pvo.setIdProveedorFK(resultSet.getInt(10));
                informacionProducto.add(pvo);
            }
            conector.desconectar();
        } catch (Exception e) {
            evo.setDescripcionError("[Consultar-Producto]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
        return informacionProducto;
    }

    @Override
    public boolean actualizarProducto(ProductoVO pvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "UPDATE dbkapa.producto p "
                    + "SET p.descripcion_producto = '" + pvo.getDescripcionProducto() + "', "
                    + "p.marca_producto = '" + pvo.getMarcaProducto() + "', "
                    + "p.presentacion_producto = '" + pvo.getPresentacionProducto() + "', "
                    + "p.categoria_producto = '" + pvo.getCategoriaProducto() + "', "
                    + "p.precio_compra_producto = " + pvo.getPrecioCompraProducto() + ", "
                    + "p.precio_venta_producto = " + pvo.getPrecioVentaProducto() + ", "
                    + "p.existencia_producto = " + pvo.getExistenciaProducto() + ", "
                    + "p.id_proveedor_fk = " + pvo.getIdProveedorFK() + " "
                    + "WHERE p.id_producto = " + pvo.getIdProducto();
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Actualizar-Producto]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public boolean eliminarProducto(ProductoVO pvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "DELETE FROM dbkapa.producto WHERE id_producto = " + pvo.getIdProducto();
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Eliminar-Producto]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public void reporteProducto() {
        Conector conector = new Conector();
        try {
            conector.conectar();
            JasperReport reporteProductos;
            String ruta = "/reportes/ReporteProductos.jasper";
            reporteProductos = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteProductos, null, conector.connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            this.jasperViewer = jasperViewer;
        } catch (Exception e) {
            evo.setDescripcionError("[Reporte-Productos]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
    }
}
