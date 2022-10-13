package controlador;

import extras.Extras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClienteDAO;
import modelo.ClienteVO;
import modelo.VentaDAO;
import modelo.VentaVO;
import vista.FrmCliente;

public class ControladorCliente implements ActionListener, WindowListener, MouseListener {

    FrmCliente vCliente = new FrmCliente();
    ClienteVO cvo = new ClienteVO();
    ClienteDAO cdao = new ClienteDAO();
    VentaVO vvo = new VentaVO();
    VentaDAO vdao = new VentaDAO();
    Extras extras = new Extras();
    DefaultTableModel modeloTablaCliente;
    boolean banderaReporte = false;

    /**
     * Constructor con parámetro
     *
     * @param vCliente -> Representa la vista del administrador de clientes
     */
    public ControladorCliente(FrmCliente vCliente) {
        this.vCliente = vCliente;
        this.vCliente.addWindowListener(this);
        this.vCliente.tblClientes.addMouseListener(this);
        this.vCliente.btnLimpiarCliente.addActionListener(this);
        this.vCliente.btnCrearCliente.addActionListener(this);
        this.vCliente.btnActualizarCliente.addActionListener(this);
        this.vCliente.btnEliminarCliente.addActionListener(this);
        this.vCliente.btnReporteCliente.addActionListener(this);
        this.vCliente.btnSalirCliente.addActionListener(this);
    }

    private void mostrarClientes() {
        modeloTablaCliente = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTablaCliente.setColumnCount(0);
        modeloTablaCliente.addColumn("ID");
        modeloTablaCliente.addColumn("Usuario");
        modeloTablaCliente.addColumn("Contraseña");
        modeloTablaCliente.addColumn("Nombre");
        modeloTablaCliente.addColumn("Apellido");
        modeloTablaCliente.addColumn("NIT");
        modeloTablaCliente.addColumn("Dirección");
        modeloTablaCliente.addColumn("Teléfono");
        modeloTablaCliente.addColumn("Correo");
        for (ClienteVO cvo : cdao.consultarCliente()) {
            modeloTablaCliente.addRow(new Object[]{cvo.getIdCliente(),
                cvo.getUsuarioCliente(), cvo.getContraseniaCliente(),
                cvo.getNombreCliente(), cvo.getApellidoCliente(),
                cvo.getNitCliente(), cvo.getDireccionCliente(),
                cvo.getTelefonoCliente(), cvo.getCorreoCliente()});
        }
        this.vCliente.tblClientes.setModel(modeloTablaCliente);
        this.vCliente.tblClientes.getTableHeader().setReorderingAllowed(false);
        this.vCliente.tblClientes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vCliente.tblClientes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vCliente.tblClientes.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vCliente.tblClientes.getColumnModel().getColumn(0).setMinWidth(0);
        this.vCliente.tblClientes.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        this.vCliente.tblClientes.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        this.vCliente.tblClientes.getColumnModel().getColumn(1).setMaxWidth(0);
        this.vCliente.tblClientes.getColumnModel().getColumn(1).setMinWidth(0);
        this.vCliente.tblClientes.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        this.vCliente.tblClientes.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        this.vCliente.tblClientes.getColumnModel().getColumn(2).setMaxWidth(0);
        this.vCliente.tblClientes.getColumnModel().getColumn(2).setMinWidth(0);
    }

    private void llenarCamposCliente() {
        int numero = 0;
        while (numero < 9) {
            switch (numero) {
                case 0:
                    this.vCliente.txtIdCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
                case 1:
                    this.vCliente.txtUsuarioCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
                case 2:
                    this.vCliente.txtContraseniaCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
                case 3:
                    this.vCliente.txtNombreCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
                case 4:
                    this.vCliente.txtApellidoCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
                case 5:
                    this.vCliente.txtNitCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
                case 6:
                    this.vCliente.txtDireccionCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
                case 7:
                    this.vCliente.txtTelefonoCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
                case 8:
                    this.vCliente.txtCorreoCliente.setText(String.valueOf(this.vCliente.tblClientes.getValueAt(this.vCliente.tblClientes.getSelectedRow(), numero)));
                    break;
            }
            numero++;
        }
    }

    private boolean verificarClienteDuplicado(int opcion) {
        boolean banderaCliente = true;
        if (opcion == 1) {
            for (ClienteVO cliente : cdao.consultarCliente()) {
                if (cliente.getUsuarioCliente().equals(this.vCliente.txtUsuarioCliente.getText())
                        || cliente.getCorreoCliente().equals(this.vCliente.txtCorreoCliente.getText())
                        || cliente.getNitCliente().equals(this.vCliente.txtNitCliente.getText())) {
                    banderaCliente = false;
                    this.vCliente.jopMensajeCliente.showMessageDialog(null, "Ya existe un cliente con el mismo usuario, nit y/o correo electrónico.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    if (!this.vCliente.txtIdCliente.getText().isEmpty()) {
                        limpiarCampos();
                    }
                    break;
                }
            }
        } else {
            ArrayList<ClienteVO> cliente = cdao.consultarCliente();
            for (int i = 0; i < cliente.size(); i++) {
                if (cliente.get(i).getUsuarioCliente().equals(this.vCliente.txtUsuarioCliente.getText())
                        || cliente.get(i).getCorreoCliente().equals(this.vCliente.txtCorreoCliente.getText())
                        || cliente.get(i).getNitCliente().equals(this.vCliente.txtNitCliente.getText())) {
                    if (i != this.vCliente.tblClientes.getSelectedRow()) {
                        banderaCliente = false;
                        this.vCliente.jopMensajeCliente.showMessageDialog(null, "Ya existe un cliente con el mismo usuario, nit y/o correo electrónico.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                        limpiarCampos();
                        break;
                    }
                }
            }
        }
        return banderaCliente;
    }

    private void registrarCliente() {
        if (!this.vCliente.txtUsuarioCliente.getText().isEmpty()
                && !this.vCliente.txtContraseniaCliente.getText().isEmpty()
                && !this.vCliente.txtNombreCliente.getText().isEmpty()
                && !this.vCliente.txtApellidoCliente.getText().isEmpty()
                && !this.vCliente.txtNitCliente.getText().isEmpty()
                && !this.vCliente.txtDireccionCliente.getText().isEmpty()
                && !this.vCliente.txtTelefonoCliente.getText().isEmpty()
                && !this.vCliente.txtCorreoCliente.getText().isEmpty()) {
            if (verificarClienteDuplicado(1)) {
                this.cvo.setUsuarioCliente(this.vCliente.txtUsuarioCliente.getText());
                this.cvo.setContraseniaCliente(this.vCliente.txtContraseniaCliente.getText());
                this.cvo.setNombreCliente(this.vCliente.txtNombreCliente.getText());
                this.cvo.setApellidoCliente(this.vCliente.txtApellidoCliente.getText());
                this.cvo.setNitCliente(this.vCliente.txtNitCliente.getText());
                this.cvo.setDireccionCliente(this.vCliente.txtDireccionCliente.getText());
                this.cvo.setTelefonoCliente(this.vCliente.txtTelefonoCliente.getText());
                this.cvo.setCorreoCliente(this.vCliente.txtCorreoCliente.getText());
                if (cdao.insertarCliente(cvo) == true) {
                    limpiarCampos();
                    this.vCliente.jopMensajeCliente.showMessageDialog(null, "Cliente registrado correctamente.",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    this.vCliente.jopMensajeCliente.showMessageDialog(null, "Error, datos no registrados.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            this.vCliente.jopMensajeCliente.showMessageDialog(null, "No se aceptan campos vacíos (a excepción de ID)",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void modificarCliente() {
        if (!this.vCliente.txtIdCliente.getText().isEmpty()
                && !this.vCliente.txtUsuarioCliente.getText().isEmpty()
                && !this.vCliente.txtContraseniaCliente.getText().isEmpty()
                && !this.vCliente.txtNombreCliente.getText().isEmpty()
                && !this.vCliente.txtApellidoCliente.getText().isEmpty()
                && !this.vCliente.txtNitCliente.getText().isEmpty()
                && !this.vCliente.txtDireccionCliente.getText().isEmpty()
                && !this.vCliente.txtTelefonoCliente.getText().isEmpty()
                && !this.vCliente.txtCorreoCliente.getText().isEmpty()) {
            if (verificarClienteDuplicado(2)) {
                this.cvo.setIdCliente(Integer.parseInt(this.vCliente.txtIdCliente.getText()));
                this.cvo.setUsuarioCliente(this.vCliente.txtUsuarioCliente.getText());
                this.cvo.setContraseniaCliente(this.vCliente.txtContraseniaCliente.getText());
                this.cvo.setNombreCliente(this.vCliente.txtNombreCliente.getText());
                this.cvo.setApellidoCliente(this.vCliente.txtApellidoCliente.getText());
                this.cvo.setNitCliente(this.vCliente.txtNitCliente.getText());
                this.cvo.setDireccionCliente(this.vCliente.txtDireccionCliente.getText());
                this.cvo.setTelefonoCliente(this.vCliente.txtTelefonoCliente.getText());
                this.cvo.setCorreoCliente(this.vCliente.txtCorreoCliente.getText());
                if (cdao.actualizarCliente(cvo) == true) {
                    limpiarCampos();
                    this.vCliente.jopMensajeCliente.showMessageDialog(null, "Cliente actualizado correctamente.",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    this.vCliente.jopMensajeCliente.showMessageDialog(null, "Error, no se pudo actualizar el cliente.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            this.vCliente.jopMensajeCliente.showMessageDialog(null, "No se aceptan campos vacíos.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean verificarClienteConFacturas() {
        boolean clienteConFactura = false;
        for (VentaVO venta : vdao.consultarVenta()) {
            if (venta.getIdClienteFK() == Integer.valueOf(this.vCliente.txtIdCliente.getText())) {
                clienteConFactura = true;
                break;
            }
        }
        return clienteConFactura;
    }

    private void eliminarCliente() {
        if (!this.vCliente.txtIdCliente.getText().isEmpty()) {
            if (!verificarClienteConFacturas()) {
                this.cvo.setIdCliente(Integer.parseInt(this.vCliente.txtIdCliente.getText()));
                if (cdao.eliminarCliente(cvo) == true) {
                    limpiarCampos();
                    this.vCliente.jopMensajeCliente.showMessageDialog(null, "Cliente eliminado correctamente.",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    this.vCliente.jopMensajeCliente.showMessageDialog(null, "Error, no se pudo eliminar el cliente.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                this.vCliente.jopMensajeCliente.showMessageDialog(null, "No se puede eliminar el cliente porque tiene facturas a su nombre.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            this.vCliente.jopMensajeCliente.showMessageDialog(null, "No ha seleccionado ningún cliente.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reporteClientes() {
        cdao.reporteCliente();
        cdao.jasperViewer.setDefaultCloseOperation(this.vCliente.DISPOSE_ON_CLOSE);
        cdao.jasperViewer.setVisible(true);
        banderaReporte = true;
    }

    private void limpiarCampos() {
        this.vCliente.txtIdCliente.setText("");
        this.vCliente.txtUsuarioCliente.setText("");
        this.vCliente.txtContraseniaCliente.setText("");
        this.vCliente.txtNombreCliente.setText("");
        this.vCliente.txtApellidoCliente.setText("");
        this.vCliente.txtNitCliente.setText("");
        this.vCliente.txtTelefonoCliente.setText("");
        this.vCliente.txtCorreoCliente.setText("");
        this.vCliente.txtDireccionCliente.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vCliente.btnLimpiarCliente) {
            limpiarCampos();
        }
        if (ae.getSource() == this.vCliente.btnCrearCliente) {
            registrarCliente();
        }
        if (ae.getSource() == this.vCliente.btnActualizarCliente) {
            modificarCliente();
        }
        if (ae.getSource() == this.vCliente.btnEliminarCliente) {
            eliminarCliente();
        }
        if (ae.getSource() == this.vCliente.btnReporteCliente) {
            reporteClientes();
        }
        if (ae.getSource() == this.vCliente.btnSalirCliente) {
            this.vCliente.dispose();
            limpiarCampos();
            if (banderaReporte) {
                cdao.jasperViewer.setVisible(false);
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
        mostrarClientes();
    }

    @Override
    public void windowClosing(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
        limpiarCampos();
        if (banderaReporte) {
            cdao.jasperViewer.setVisible(false);
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
        mostrarClientes();
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == this.vCliente.tblClientes) {
            if (me.getClickCount() == 2) {
                llenarCamposCliente();
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
