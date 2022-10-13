package controlador;

import extras.Extras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClienteDAO;
import modelo.ClienteVO;
import modelo.DetalleFacturaDAO;
import modelo.DetalleFacturaVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.VentaDAO;
import modelo.VentaVO;
import vista.FrmVentas;

public class ControladorVenta implements ActionListener, WindowListener, MouseListener {

    FrmVentas vVentas = new FrmVentas();
    VentaVO vvo = new VentaVO();
    VentaDAO vdao = new VentaDAO();
    ClienteVO cvo = new ClienteVO();
    ClienteDAO cdao = new ClienteDAO();
    ProductoVO pvo = new ProductoVO();
    ProductoDAO pdao = new ProductoDAO();
    DetalleFacturaVO dfvo = new DetalleFacturaVO();
    DetalleFacturaDAO dfdao = new DetalleFacturaDAO();
    Extras extras = new Extras();
    DefaultTableModel modeloTablaVentas;
    DefaultTableModel modeloDetalle;
    boolean banderaReporte = false;

    /**
     * Controlador con parámetro
     *
     * @param vVentas -> Representa la vista del administrador de ventas
     */
    public ControladorVenta(FrmVentas vVentas) {
        this.vVentas = vVentas;
        this.vVentas.addWindowListener(this);
        this.vVentas.tblVentas.addMouseListener(this);
        this.vVentas.lblLimpiarVentas.addMouseListener(this);
        this.vVentas.lblReportesVentas.addMouseListener(this);
        this.vVentas.lblSalirVentas.addMouseListener(this);
        this.vVentas.btnAceptarVentas.addActionListener(this);
    }

    private void mostrarVentas() {
        modeloTablaVentas = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTablaVentas.setColumnCount(0);
        modeloTablaVentas.addColumn("ID");
        modeloTablaVentas.addColumn("Número");
        modeloTablaVentas.addColumn("Serie");
        modeloTablaVentas.addColumn("Fecha");
        modeloTablaVentas.addColumn("Total");
        modeloTablaVentas.addColumn("Tipo pago");
        modeloTablaVentas.addColumn("Estado");
        modeloTablaVentas.addColumn("ID Cliente");
        for (VentaVO vvo : vdao.consultarVenta()) {
            modeloTablaVentas.addRow(new Object[]{vvo.getIdFactura(),
                vvo.getNumeroFactura(), vvo.getSerieFactura(),
                extras.cambiarFormatoAFechaVista(vvo.getFechaFactura()), vvo.getTotalFactura(),
                vvo.getTipoPagoFactura(), vvo.getEstadoFactura(),
                vvo.getIdClienteFK()});
        }
        this.vVentas.tblVentas.setModel(modeloTablaVentas);
        this.vVentas.tblVentas.getTableHeader().setReorderingAllowed(false);
        this.vVentas.tblVentas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vVentas.tblVentas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vVentas.tblVentas.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vVentas.tblVentas.getColumnModel().getColumn(0).setMinWidth(0);
        this.vVentas.tblVentas.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        this.vVentas.tblVentas.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        this.vVentas.tblVentas.getColumnModel().getColumn(5).setMaxWidth(0);
        this.vVentas.tblVentas.getColumnModel().getColumn(5).setMinWidth(0);
        this.vVentas.tblVentas.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        this.vVentas.tblVentas.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        this.vVentas.tblVentas.getColumnModel().getColumn(7).setMaxWidth(0);
        this.vVentas.tblVentas.getColumnModel().getColumn(7).setMinWidth(0);
    }

    private void llenarCamposVenta() {
        int numero = 0;
        while (numero < 8) {
            switch (numero) {
                case 0:
                    this.vVentas.txtIdVentas.setText(String.valueOf(this.vVentas.tblVentas.getValueAt(this.vVentas.tblVentas.getSelectedRow(), numero)));
                    break;
                case 1:
                    this.vVentas.txtNumeroVentas.setText(String.valueOf(this.vVentas.tblVentas.getValueAt(this.vVentas.tblVentas.getSelectedRow(), numero)));
                    break;
                case 2:
                    this.vVentas.txtSerieVentas.setText(String.valueOf(this.vVentas.tblVentas.getValueAt(this.vVentas.tblVentas.getSelectedRow(), numero)));
                    break;
                case 3:
                    this.vVentas.txtFechaVentas.setText(String.valueOf(this.vVentas.tblVentas.getValueAt(this.vVentas.tblVentas.getSelectedRow(), numero)));
                    break;
                case 4:
                    this.vVentas.txtTotalVentas.setText(String.valueOf(this.vVentas.tblVentas.getValueAt(this.vVentas.tblVentas.getSelectedRow(), numero)));
                    break;
                case 5:
                    break;
                case 6:
                    this.vVentas.cmbEstadoVentas.setSelectedItem(String.valueOf(this.vVentas.tblVentas.getValueAt(this.vVentas.tblVentas.getSelectedRow(), numero)));
                    break;
                case 7:
                    llenarCamposCliente(numero);
                    break;
            }
            numero++;
        }
    }

    private void llenarCamposCliente(int numero) {
        for (ClienteVO cvo : cdao.consultarCliente()) {
            if (String.valueOf(cvo.getIdCliente()).equals(String.valueOf(this.vVentas.tblVentas.getValueAt(this.vVentas.tblVentas.getSelectedRow(), numero)))) {
                this.vVentas.txtNombreClienteVentas.setText(cvo.getNombreCliente());
                this.vVentas.txtApellidoClienteVentas.setText(cvo.getApellidoCliente());
                this.vVentas.txtNitClienteVentas.setText(cvo.getNitCliente());
            }
        }
    }

    private void mostrarDetalleVenta() {
        modeloDetalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloDetalle.setColumnCount(0);
        modeloDetalle.addColumn("ID");
        modeloDetalle.addColumn("Cantidad");
        modeloDetalle.addColumn("Subtotal");
        modeloDetalle.addColumn("ID Factura");
        modeloDetalle.addColumn("ID Producto");
        modeloDetalle.addColumn("Producto");
        for (DetalleFacturaVO dfvo : dfdao.consultarDetalleFactura()) {
            if (String.valueOf(dfvo.getIdFacturaFK()).equals(String.valueOf(this.vVentas.tblVentas.getValueAt(this.vVentas.tblVentas.getSelectedRow(), 0)))) {
                for (ProductoVO pvo : pdao.consultarProducto()) {
                    if (String.valueOf(pvo.getIdProducto()).equals(String.valueOf(dfvo.getIdProductoFK()))) {
                        modeloDetalle.addRow(new Object[]{dfvo.getIdDetalleFactura(),
                            dfvo.getCantidadDetalleFactura(), dfvo.getSubtotalDetalleFactura(),
                            dfvo.getIdFacturaFK(), dfvo.getIdProductoFK(), pvo.getDescripcionProducto()});
                        break;
                    }
                }
            }
        }
        this.vVentas.tblDetalleVentas.setModel(modeloDetalle);
        this.vVentas.tblDetalleVentas.getTableHeader().setReorderingAllowed(false);
        this.vVentas.tblDetalleVentas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vVentas.tblDetalleVentas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vVentas.tblDetalleVentas.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vVentas.tblDetalleVentas.getColumnModel().getColumn(0).setMinWidth(0);
        this.vVentas.tblDetalleVentas.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        this.vVentas.tblDetalleVentas.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        this.vVentas.tblDetalleVentas.getColumnModel().getColumn(3).setMaxWidth(0);
        this.vVentas.tblDetalleVentas.getColumnModel().getColumn(3).setMinWidth(0);
        this.vVentas.tblDetalleVentas.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        this.vVentas.tblDetalleVentas.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        this.vVentas.tblDetalleVentas.getColumnModel().getColumn(4).setMaxWidth(0);
        this.vVentas.tblDetalleVentas.getColumnModel().getColumn(4).setMinWidth(0);
    }

    private void actualizarEstadoVenta() {
        if (!this.vVentas.txtIdVentas.getText().isEmpty()) {
            this.vvo.setIdFactura(Integer.valueOf(this.vVentas.txtIdVentas.getText()));
            this.vvo.setEstadoFactura(String.valueOf(this.vVentas.cmbEstadoVentas.getSelectedItem()));
            if (vdao.actualizarVenta(vvo) == true) {
                this.vVentas.jopMensajeVentas.showMessageDialog(null, "Estado de la venta actualizado con éxito.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                mostrarVentas();
                limpiarCampos();
            } else {
                this.vVentas.jopMensajeVentas.showMessageDialog(null, "Error, no se pudo actualizar el estado de la venta.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            this.vVentas.jopMensajeVentas.showMessageDialog(null, "No ha seleccionado una venta a actualizar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reporteVentas() {
        vdao.reporteVenta();
        vdao.jasperViewer.setDefaultCloseOperation(this.vVentas.DISPOSE_ON_CLOSE);
        vdao.jasperViewer.setVisible(true);
        banderaReporte = true;
    }

    private void limpiarCampos() {
        this.vVentas.txtIdVentas.setText("");
        this.vVentas.txtNumeroVentas.setText("");
        this.vVentas.txtSerieVentas.setText("");
        this.vVentas.txtFechaVentas.setText("");
        this.vVentas.txtTotalVentas.setText("");
        this.vVentas.txtNombreClienteVentas.setText("");
        this.vVentas.txtApellidoClienteVentas.setText("");
        this.vVentas.txtNitClienteVentas.setText("");
        this.vVentas.cmbEstadoVentas.setSelectedIndex(0);
    }

    private void limpiarTablaDetalles() {
        modeloDetalle = (DefaultTableModel) this.vVentas.tblDetalleVentas.getModel();
        modeloDetalle.getDataVector().removeAllElements();
        this.vVentas.tblDetalleVentas.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vVentas.btnAceptarVentas) {
            actualizarEstadoVenta();
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
        mostrarVentas();
    }

    @Override
    public void windowClosing(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
        if (!this.vVentas.txtIdVentas.getText().isEmpty()) {
            limpiarCampos();
            limpiarTablaDetalles();
        }
        if (banderaReporte) {
            vdao.jasperViewer.setVisible(false);
        }
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
        mostrarVentas();
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == this.vVentas.tblVentas) {
            if (me.getClickCount() == 2) {
                llenarCamposVenta();
                mostrarDetalleVenta();
            }
        }
        if (me.getSource() == this.vVentas.lblLimpiarVentas) {
            limpiarCampos();
            limpiarTablaDetalles();
        }
        if (me.getSource() == this.vVentas.lblReportesVentas) {
            reporteVentas();
        }
        if (me.getSource() == this.vVentas.lblSalirVentas) {
            this.vVentas.dispose();
            if (!this.vVentas.txtIdVentas.getText().isEmpty()) {
                limpiarCampos();
                limpiarTablaDetalles();
            }
            if (banderaReporte) {
                vdao.jasperViewer.setVisible(false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
