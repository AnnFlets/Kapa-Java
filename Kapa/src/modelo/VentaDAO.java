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

public class VentaDAO implements ConsultarVenta{
    
    ErrorVO evo = new ErrorVO();
    ErrorDAO edao = new ErrorDAO();
    Extras extras = new Extras();

    public JasperViewer jasperViewer;
    
    @Override
    public ArrayList<VentaVO> consultarVenta() {
        Conector conector = new Conector();
        ArrayList<VentaVO> informacionVenta = new ArrayList<>();
        try{
            conector.conectar();
            String query = "SELECT "
                    + "f.id_factura, "
                    + "f.numero_factura, "
                    + "f.serie_factura, "
                    + "f.fecha_factura, "
                    + "f.total_factura, "
                    + "f.tipo_pago_factura, "
                    + "f.estado_factura, "
                    + "f.id_cliente_fk "
                    + "FROM factura f";
            ResultSet resultSet = conector.consultaDatos(query);
            while(resultSet.next()){
                VentaVO vvo = new VentaVO();
                vvo.setIdFactura(resultSet.getInt(1));
                vvo.setNumeroFactura(resultSet.getInt(2));
                vvo.setSerieFactura(resultSet.getString(3));
                vvo.setFechaFactura(resultSet.getString(4));
                vvo.setTotalFactura(resultSet.getDouble(5));
                vvo.setTipoPagoFactura(resultSet.getString(6));
                vvo.setEstadoFactura(resultSet.getString(7));
                vvo.setIdClienteFK(resultSet.getInt(8));
                informacionVenta.add(vvo);
            }
            conector.desconectar();
        }catch(Exception e){
            evo.setDescripcionError("[Consultar-Venta]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
        return informacionVenta;
    }

    @Override
    public boolean actualizarVenta(VentaVO vvo) {
        Conector conector = new Conector();
        try{
            conector.conectar();
            String query = "UPDATE dbkapa.factura f " +
            "SET f.estado_factura = '" + vvo.getEstadoFactura() + "' " +
                    "WHERE f.id_factura = " + vvo.getIdFactura();
            conector.consultasMultiples(query);
        }catch(Exception e){
            evo.setDescripcionError("[Actualizar-Venta]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public void reporteVenta() {
        Conector conector = new Conector();
        try{
            conector.conectar();
            JasperReport reporteVenta;
            String ruta = "/reportes/ReporteVentas.jasper";
            reporteVenta = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteVenta, null, conector.connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            this.jasperViewer = jasperViewer;
        }catch(Exception e){
            evo.setDescripcionError("[Reporte-Ventas]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
    }
}