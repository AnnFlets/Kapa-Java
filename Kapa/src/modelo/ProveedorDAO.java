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

public class ProveedorDAO implements ConsultarProveedor {

    ErrorVO evo = new ErrorVO();
    ErrorDAO edao = new ErrorDAO();
    Extras extras = new Extras();

    public JasperViewer jasperViewer;

    @Override
    public boolean insertarProveedor(ProveedorVO prvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "INSERT INTO proveedor (nombre_proveedor, "
                    + "telefono_proveedor) "
                    + "VALUES ('" + prvo.getNombreProveedor() + "', "
                    + "'" + prvo.getTelefonoProveedor() + "')";
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Insertar-Proveedor]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public ArrayList<ProveedorVO> consultarProveedor() {
        Conector conector = new Conector();
        ArrayList<ProveedorVO> informacionProveedor = new ArrayList<>();
        try {
            conector.conectar();
            String query = "SELECT "
                    + "p.id_proveedor, "
                    + "p.nombre_proveedor, "
                    + "p.telefono_proveedor "
                    + "FROM proveedor p";
            ResultSet resultSet = conector.consultaDatos(query);
            while (resultSet.next()) {
                ProveedorVO prvo = new ProveedorVO();
                prvo.setIdProveedor(resultSet.getInt(1));
                prvo.setNombreProveedor(resultSet.getString(2));
                prvo.setTelefonoProveedor(resultSet.getString(3));
                informacionProveedor.add(prvo);
            }
            conector.desconectar();
        } catch (Exception e) {
            evo.setDescripcionError("[Consultar-Proveedor]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
        return informacionProveedor;
    }

    @Override
    public boolean actualizarProveedor(ProveedorVO prvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "UPDATE dbkapa.proveedor p "
                    + "SET p.nombre_proveedor = '" + prvo.getNombreProveedor() + "', "
                    + "p.telefono_proveedor = '" + prvo.getTelefonoProveedor() + "' "
                    + "WHERE p.id_proveedor = " + prvo.getIdProveedor();
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Actualizar-Proveedor]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public boolean eliminarProveedor(ProveedorVO prvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "DELETE FROM dbkapa.proveedor WHERE id_proveedor = " + prvo.getIdProveedor();
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Eliminar-Proveedor]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public void reporteProveedor() {
        Conector conector = new Conector();
        try {
            conector.conectar();
            JasperReport reporteProveedores;
            String ruta = "/reportes/ReporteProveedores.jasper";
            reporteProveedores = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteProveedores, null, conector.connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            this.jasperViewer = jasperViewer;
        } catch (Exception e) {
            evo.setDescripcionError("[Reporte-Proveedores]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
    }
}
