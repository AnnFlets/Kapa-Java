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

public class ClienteDAO implements ConsultarCliente {

    ErrorVO evo = new ErrorVO();
    ErrorDAO edao = new ErrorDAO();
    Extras extras = new Extras();

    public JasperViewer jasperViewer;

    @Override
    public boolean insertarCliente(ClienteVO cvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "INSERT INTO cliente (usuario_cliente, "
                    + "contrasenia_cliente, "
                    + "nombre_cliente, "
                    + "apellido_cliente, "
                    + "nit_cliente, "
                    + "direccion_cliente, "
                    + "telefono_cliente, "
                    + "correo_cliente) "
                    + "VALUES ('" + cvo.getUsuarioCliente() + "', "
                    + "'" + cvo.getContraseniaCliente() + "', "
                    + "'" + cvo.getNombreCliente() + "', "
                    + "'" + cvo.getApellidoCliente() + "', "
                    + "'" + cvo.getNitCliente() + "', "
                    + "'" + cvo.getDireccionCliente() + "', "
                    + "'" + cvo.getTelefonoCliente() + "', "
                    + "'" + cvo.getCorreoCliente() + "')";
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Insertar-Cliente]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public ArrayList<ClienteVO> consultarCliente() {
        Conector conector = new Conector();
        ArrayList<ClienteVO> informacionCliente = new ArrayList<>();
        try {
            conector.conectar();
            String query = "SELECT "
                    + "c.id_cliente, "
                    + "c.usuario_cliente, "
                    + "c.contrasenia_cliente, "
                    + "c.nombre_cliente, "
                    + "c.apellido_cliente, "
                    + "c.nit_cliente, "
                    + "c.direccion_cliente, "
                    + "c.telefono_cliente, "
                    + "c.correo_cliente "
                    + "FROM cliente c";
            ResultSet resultSet = conector.consultaDatos(query);
            while (resultSet.next()) {
                ClienteVO cvo = new ClienteVO();
                cvo.setIdCliente(resultSet.getInt(1));
                cvo.setUsuarioCliente(resultSet.getString(2));
                cvo.setContraseniaCliente(resultSet.getString(3));
                cvo.setNombreCliente(resultSet.getString(4));
                cvo.setApellidoCliente(resultSet.getString(5));
                cvo.setNitCliente(resultSet.getString(6));
                cvo.setDireccionCliente(resultSet.getString(7));
                cvo.setTelefonoCliente(resultSet.getString(8));
                cvo.setCorreoCliente(resultSet.getString(9));
                informacionCliente.add(cvo);
            }
            conector.desconectar();
        } catch (Exception e) {
            evo.setDescripcionError("[Consultar-Cliente]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
        return informacionCliente;
    }

    @Override
    public boolean actualizarCliente(ClienteVO cvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "UPDATE dbkapa.cliente c "
                    + "SET c.usuario_cliente = '" + cvo.getUsuarioCliente() + "', "
                    + "c.contrasenia_cliente = '" + cvo.getContraseniaCliente() + "', "
                    + "c.nombre_cliente = '" + cvo.getNombreCliente() + "', "
                    + "c.apellido_cliente = '" + cvo.getApellidoCliente() + "', "
                    + "c.nit_cliente = '" + cvo.getNitCliente() + "', "
                    + "c.direccion_cliente = '" + cvo.getDireccionCliente() + "', "
                    + "c.telefono_cliente = '" + cvo.getTelefonoCliente() + "', "
                    + "c.correo_cliente = '" + cvo.getCorreoCliente() + "' "
                    + "WHERE c.id_cliente = " + cvo.getIdCliente();
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Actualizar-Cliente]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public boolean eliminarCliente(ClienteVO cvo) {
        Conector conector = new Conector();
        try {
            conector.conectar();
            String query = "DELETE FROM dbkapa.cliente WHERE id_cliente = " + cvo.getIdCliente();
            conector.consultasMultiples(query);
        } catch (Exception e) {
            evo.setDescripcionError("[Eliminar-Cliente]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
            return false;
        }
        conector.desconectar();
        return true;
    }

    @Override
    public void reporteCliente() {
        Conector conector = new Conector();
        try {
            conector.conectar();
            JasperReport reporteCliente;
            String ruta = "/reportes/ReporteClientes.jasper";
            reporteCliente = (JasperReport) JRLoader.loadObject(getClass().getResource(ruta));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteCliente, null, conector.connection);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            this.jasperViewer = jasperViewer;
        } catch (Exception e) {
            evo.setDescripcionError("[Reporte-Clientes]: " + e.getMessage());
            evo.setFechaError(extras.devolverFechaActual());
            edao.insertarError(evo);
            conector.desconectar();
        }
    }
}
