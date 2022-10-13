package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import vista.FrmCliente;
import vista.FrmLogin;
import vista.FrmMenu;
import vista.FrmProductos;
import vista.FrmProveedor;
import vista.FrmVentas;

public class ControladorMenu implements ActionListener, WindowListener {

    FrmLogin vLogin = new FrmLogin();
    FrmMenu vMenu = new FrmMenu();
    FrmProductos vProducto = new FrmProductos();
    FrmProveedor vProveedor = new FrmProveedor();
    FrmCliente vCliente = new FrmCliente();
    FrmVentas vVentas = new FrmVentas();

    /**
     * Constructor con parámetros
     *
     * @param vLogin -> Representa la vista o JFrame del login
     * @param vMenu -> Representa la vista o JFrame del menú de administrador
     * @param vProducto -> Representa la vista o JFrame del administrador de
     * productos
     * @param vProveedor -> Representa la vista o JFrame del administrador de
     * proveedores
     * @param vCliente -> Representa la vista o JFrame del administrador de
     * clientes
     * @param vVentas -> Representa la vista o JFrame del administrador de
     * ventas
     */
    public ControladorMenu(FrmLogin vLogin, FrmMenu vMenu, FrmProductos vProducto,
            FrmProveedor vProveedor,
            FrmCliente vCliente, FrmVentas vVentas) {
        this.vLogin = vLogin;
        this.vMenu = vMenu;
        this.vProducto = vProducto;
        this.vProveedor = vProveedor;
        this.vCliente = vCliente;
        this.vVentas = vVentas;
        this.vMenu.addWindowListener(this);
        this.vMenu.btnIrProductos.addActionListener(this);
        this.vMenu.btnIrProveedores.addActionListener(this);
        this.vMenu.btnIrClientes.addActionListener(this);
        this.vMenu.btnIrVentas.addActionListener(this);
    }

    private void abrirProductos() {
        this.vProducto.setVisible(true);
        this.vProducto.setLocationRelativeTo(null);
        this.vProducto.setResizable(false);
        this.vProducto.setSize(1070, 645);
    }

    private void abrirProveedores() {
        this.vProveedor.setVisible(true);
        this.vProveedor.setLocationRelativeTo(null);
        this.vProveedor.setResizable(false);
        this.vProveedor.setSize(700, 540);
    }

    private void abrirClientes() {
        this.vCliente.setVisible(true);
        this.vCliente.setLocationRelativeTo(null);
        this.vCliente.setResizable(false);
        this.vCliente.setSize(1070, 645);
    }

    private void abrirVentas() {
        this.vVentas.setVisible(true);
        this.vVentas.setLocationRelativeTo(null);
        this.vVentas.setResizable(false);
        this.vVentas.setSize(1070, 880);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vMenu.btnIrProductos) {
            abrirProductos();
        }
        if (ae.getSource() == this.vMenu.btnIrProveedores) {
            abrirProveedores();
        }
        if (ae.getSource() == this.vMenu.btnIrClientes) {
            abrirClientes();
        }
        if (ae.getSource() == this.vMenu.btnIrVentas) {
            abrirVentas();
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        this.vProducto.dispose();
        this.vProveedor.dispose();
        this.vCliente.dispose();
        this.vVentas.dispose();
        this.vLogin.setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}
