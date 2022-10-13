package kapa;

import controlador.ControladorCliente;
import controlador.ControladorLogin;
import controlador.ControladorMenu;
import controlador.ControladorProductos;
import controlador.ControladorProveedor;
import controlador.ControladorVenta;
import extras.Extras;
import modelo.ClienteDAO;
import modelo.ClienteVO;
import modelo.DetalleFacturaDAO;
import modelo.DetalleFacturaVO;
import modelo.ErrorDAO;
import modelo.ErrorVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import modelo.VentaDAO;
import modelo.VentaVO;
import vista.FrmCliente;
import vista.FrmLogin;
import vista.FrmMenu;
import vista.FrmProductos;
import vista.FrmProveedor;
import vista.FrmVentas;

public class Kapa {

    public static void main(String[] args) {
        /**
         * Vistas
         */
        FrmLogin vLogin = new FrmLogin();
        FrmMenu vMenu = new FrmMenu();
        FrmProductos vProducto = new FrmProductos();
        FrmProveedor vProveedor = new FrmProveedor();
        FrmCliente vCliente = new FrmCliente();
        FrmVentas vVentas = new FrmVentas();

        /**
         * Modelos
         */
        UsuarioVO uvo = new UsuarioVO();
        UsuarioDAO udao = new UsuarioDAO();
        ProductoVO pvo = new ProductoVO();
        ProductoDAO pdao = new ProductoDAO();
        ProveedorVO prvo = new ProveedorVO();
        ProveedorDAO prdao = new ProveedorDAO();
        ClienteVO cvo = new ClienteVO();
        ClienteDAO cdao = new ClienteDAO();
        VentaVO vvo = new VentaVO();
        VentaDAO vdao = new VentaDAO();
        DetalleFacturaVO dfvo = new DetalleFacturaVO();
        DetalleFacturaDAO dfdao = new DetalleFacturaDAO();
        ErrorVO evo = new ErrorVO();
        ErrorDAO edao = new ErrorDAO();

        /**
         * Controladores
         */
        ControladorLogin cLogin = new ControladorLogin(vLogin, vMenu);
        ControladorMenu cMenu = new ControladorMenu(vLogin, vMenu, vProducto, vProveedor, vCliente, vVentas);
        ControladorProductos cProducto = new ControladorProductos(vProducto);
        ControladorProveedor cProveedor = new ControladorProveedor(vProveedor);
        ControladorCliente cCliente = new ControladorCliente(vCliente);
        ControladorVenta cVenta = new ControladorVenta(vVentas);
          
        /**
         * Extras
         */
        Extras extras = new Extras();

        /**
         * Configuración vista inicial
         */
        vLogin.setVisible(true);
        vLogin.setLocationRelativeTo(null);
        vLogin.setResizable(false);
        vLogin.setSize(480, 660);
    }
}
