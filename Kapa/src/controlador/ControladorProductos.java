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
import modelo.DetalleFacturaDAO;
import modelo.DetalleFacturaVO;
import modelo.ProductoDAO;
import modelo.ProductoVO;
import modelo.ProveedorDAO;
import modelo.ProveedorVO;
import vista.FrmProductos;

public class ControladorProductos implements ActionListener, WindowListener, MouseListener {

    FrmProductos vProducto = new FrmProductos();
    ProductoVO pvo = new ProductoVO();
    ProductoDAO pdao = new ProductoDAO();
    ProveedorVO prvo = new ProveedorVO();
    ProveedorDAO prdao = new ProveedorDAO();
    DetalleFacturaVO dfvo = new DetalleFacturaVO();
    DetalleFacturaDAO dfdao = new DetalleFacturaDAO();
    Extras extras = new Extras();
    DefaultTableModel modeloTablaProductos;
    boolean banderaReporte = false;

    /**
     * Constructor con parámetro
     *
     * @param vProducto -> Representa la vista del administrador de productos
     */
    public ControladorProductos(FrmProductos vProducto) {
        this.vProducto = vProducto;
        this.vProducto.addWindowListener(this);
        this.vProducto.tblProducto.addMouseListener(this);
        this.vProducto.btnLimpiarCampos.addActionListener(this);
        this.vProducto.btnCrearProductos.addActionListener(this);
        this.vProducto.btnActualizarProducto.addActionListener(this);
        this.vProducto.btnEliminarProducto.addActionListener(this);
        this.vProducto.btnReporteProducto.addActionListener(this);
        this.vProducto.btnSalirProducto.addActionListener(this);
    }

    private void mostrarProductos() {
        modeloTablaProductos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTablaProductos.setColumnCount(0);
        modeloTablaProductos.addColumn("ID");
        modeloTablaProductos.addColumn("Descripción");
        modeloTablaProductos.addColumn("Marca");
        modeloTablaProductos.addColumn("Presentación");
        modeloTablaProductos.addColumn("Categoría");
        modeloTablaProductos.addColumn("Precio Compra");
        modeloTablaProductos.addColumn("Precio Venta");
        modeloTablaProductos.addColumn("Existencias");
        modeloTablaProductos.addColumn("Img");
        modeloTablaProductos.addColumn("ID Proveedor");
        modeloTablaProductos.addColumn("Proveedor");
        for (ProductoVO producto : pdao.consultarProducto()) {
            for (ProveedorVO proveedor : prdao.consultarProveedor()) {
                if (proveedor.getIdProveedor() == producto.getIdProveedorFK()) {
                    modeloTablaProductos.addRow(new Object[]{producto.getIdProducto(),
                        producto.getDescripcionProducto(), producto.getMarcaProducto(),
                        producto.getPresentacionProducto(), producto.getCategoriaProducto(),
                        producto.getPrecioCompraProducto(), producto.getPrecioVentaProducto(),
                        producto.getExistenciaProducto(), producto.getImgProducto(),
                        producto.getIdProveedorFK(), proveedor.getNombreProveedor()});
                }
            }
        }
        this.vProducto.tblProducto.setModel(modeloTablaProductos);
        this.vProducto.tblProducto.getTableHeader().setReorderingAllowed(false);
        this.vProducto.tblProducto.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        this.vProducto.tblProducto.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        this.vProducto.tblProducto.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vProducto.tblProducto.getColumnModel().getColumn(0).setMinWidth(0);
        this.vProducto.tblProducto.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        this.vProducto.tblProducto.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        this.vProducto.tblProducto.getColumnModel().getColumn(8).setMaxWidth(0);
        this.vProducto.tblProducto.getColumnModel().getColumn(8).setMinWidth(0);
        this.vProducto.tblProducto.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        this.vProducto.tblProducto.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
        this.vProducto.tblProducto.getColumnModel().getColumn(9).setMaxWidth(0);
        this.vProducto.tblProducto.getColumnModel().getColumn(9).setMinWidth(0);
    }

    private void llenarComboboxProveedores() {
        for (ProveedorVO proveedor : prdao.consultarProveedor()) {
            this.vProducto.cmbNombreProveedor.addItem(proveedor.getNombreProveedor());
        }
    }

    private void borrarComboboxProveedores() {
        this.vProducto.cmbNombreProveedor.removeAllItems();
    }

    private void llenarCamposProducto() {
        int numero = 0;
        while (numero < 11) {
            switch (numero) {
                case 0:
                    this.vProducto.txtIdProducto.setText(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
                case 1:
                    this.vProducto.txtDescripcionProducto.setText(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
                case 2:
                    this.vProducto.txtMarcaProducto.setText(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
                case 3:
                    this.vProducto.txtPresentacionProducto.setText(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
                case 4:
                    this.vProducto.txtCategoriaProducto.setText(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
                case 5:
                    this.vProducto.txtPrecioCompraProducto.setText(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
                case 6:
                    this.vProducto.txtPrecioVentaProducto.setText(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
                case 7:
                    this.vProducto.txtExistenciaProducto.setText(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
                case 10:
                    this.vProducto.cmbNombreProveedor.setSelectedItem(String.valueOf(this.vProducto.tblProducto.getValueAt(this.vProducto.tblProducto.getSelectedRow(), numero)));
                    break;
            }
            numero++;
        }
    }

    private boolean verificarProductoDuplicado(int opcion) {
        boolean banderaProducto = true;
        if (opcion == 1) {
            for (ProductoVO producto : pdao.consultarProducto()) {
                if (producto.getDescripcionProducto().equals(this.vProducto.txtDescripcionProducto.getText())
                        && producto.getMarcaProducto().equals(this.vProducto.txtMarcaProducto.getText())
                        && producto.getPresentacionProducto().equals(this.vProducto.txtPresentacionProducto.getText())) {
                    banderaProducto = false;
                    this.vProducto.jopMensaje.showMessageDialog(null, "Ya existe un producto con la misma descripción, marca y presentación.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    if (!this.vProducto.txtIdProducto.getText().isEmpty()) {
                        limpiarCampos();
                    }
                    break;
                }
            }
        } else {
            ArrayList<ProductoVO> producto = pdao.consultarProducto();
            for (int i = 0; i < producto.size(); i++) {
                if (producto.get(i).getDescripcionProducto().equals(this.vProducto.txtDescripcionProducto.getText())
                        && producto.get(i).getMarcaProducto().equals(this.vProducto.txtMarcaProducto.getText())
                        && producto.get(i).getPresentacionProducto().equals(this.vProducto.txtPresentacionProducto.getText())) {
                    if (i != this.vProducto.tblProducto.getSelectedRow()) {
                        banderaProducto = false;
                        this.vProducto.jopMensaje.showMessageDialog(null, "Ya existe un producto con la misma descripción, marca y presentación.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                        limpiarCampos();
                        break;
                    }
                }
            }
        }
        return banderaProducto;
    }

    private int hallarIdProveedor() {
        int idProveedorProducto = 0;
        for (ProveedorVO proveedor : prdao.consultarProveedor()) {
            if (proveedor.getNombreProveedor().equals(this.vProducto.cmbNombreProveedor.getSelectedItem())) {
                idProveedorProducto = proveedor.getIdProveedor();
            }
        }
        return idProveedorProducto;
    }

    private void registrarProducto() {
        if (!this.vProducto.txtDescripcionProducto.getText().isEmpty()
                && !this.vProducto.txtMarcaProducto.getText().isEmpty()
                && !this.vProducto.txtPresentacionProducto.getText().isEmpty()
                && !this.vProducto.txtCategoriaProducto.getText().isEmpty()
                && !this.vProducto.txtPrecioCompraProducto.getText().isEmpty()
                && !this.vProducto.txtPrecioVentaProducto.getText().isEmpty()
                && !this.vProducto.txtExistenciaProducto.getText().isEmpty()) {
            if (verificarProductoDuplicado(1)) {
                try {
                    this.pvo.setDescripcionProducto(this.vProducto.txtDescripcionProducto.getText());
                    this.pvo.setMarcaProducto(this.vProducto.txtMarcaProducto.getText());
                    this.pvo.setPresentacionProducto(this.vProducto.txtPresentacionProducto.getText());
                    this.pvo.setCategoriaProducto(this.vProducto.txtCategoriaProducto.getText());
                    this.pvo.setPrecioCompraProducto(Double.parseDouble(this.vProducto.txtPrecioCompraProducto.getText()));
                    this.pvo.setPrecioVentaProducto(Double.parseDouble(this.vProducto.txtPrecioVentaProducto.getText()));
                    this.pvo.setExistenciaProducto(Integer.parseInt(this.vProducto.txtExistenciaProducto.getText()));
                    this.pvo.setIdProveedorFK(hallarIdProveedor());
                    if (!(this.pvo.getPrecioCompraProducto() <= 0)
                            || !(this.pvo.getPrecioVentaProducto() <= 0)
                            || !(this.pvo.getExistenciaProducto() <= 0)) {
                        if (pdao.insertarProducto(pvo) == true) {
                            limpiarCampos();
                            this.vProducto.jopMensaje.showMessageDialog(null, "Producto registrado correctamente.",
                                    "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            this.vProducto.jopMensaje.showMessageDialog(null, "Error, datos no registrados.",
                                    "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        this.vProducto.jopMensaje.showMessageDialog(null, "Los precios y existencias deben ser valores positivos.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    this.vProducto.jopMensaje.showMessageDialog(null, "Los campos de precio y existencia deben ser valores numéricos.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            this.vProducto.jopMensaje.showMessageDialog(null, "Todos los campos deben de tener información según corresponda",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void modificarProducto() {
        if (!this.vProducto.txtDescripcionProducto.getText().isEmpty()
                && !this.vProducto.txtMarcaProducto.getText().isEmpty()
                && !this.vProducto.txtPresentacionProducto.getText().isEmpty()
                && !this.vProducto.txtCategoriaProducto.getText().isEmpty()
                && !this.vProducto.txtPrecioCompraProducto.getText().isEmpty()
                && !this.vProducto.txtPrecioVentaProducto.getText().isEmpty()
                && !this.vProducto.txtExistenciaProducto.getText().isEmpty()) {
            if (verificarProductoDuplicado(2)) {
                try {
                    this.pvo.setIdProducto(Integer.parseInt(this.vProducto.txtIdProducto.getText()));
                    this.pvo.setDescripcionProducto(this.vProducto.txtDescripcionProducto.getText());
                    this.pvo.setMarcaProducto(this.vProducto.txtMarcaProducto.getText());
                    this.pvo.setPresentacionProducto(this.vProducto.txtPresentacionProducto.getText());
                    this.pvo.setCategoriaProducto(this.vProducto.txtCategoriaProducto.getText());
                    this.pvo.setPrecioCompraProducto(Double.parseDouble(this.vProducto.txtPrecioCompraProducto.getText()));
                    this.pvo.setPrecioVentaProducto(Double.parseDouble(this.vProducto.txtPrecioVentaProducto.getText()));
                    this.pvo.setExistenciaProducto(Integer.parseInt(this.vProducto.txtExistenciaProducto.getText()));
                    this.pvo.setIdProveedorFK(hallarIdProveedor());
                    if (!(this.pvo.getPrecioCompraProducto() <= 0)
                            || !(this.pvo.getPrecioVentaProducto() <= 0)
                            || !(this.pvo.getExistenciaProducto() <= 0)) {
                        if (pdao.actualizarProducto(pvo) == true) {
                            limpiarCampos();
                            this.vProducto.jopMensaje.showMessageDialog(null, "Producto actualizado correctamente.",
                                    "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            this.vProducto.jopMensaje.showMessageDialog(null, "Error, datos no actualizado.",
                                    "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        this.vProducto.jopMensaje.showMessageDialog(null, "Los precios y existencias deben ser valores positivos.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    this.vProducto.jopMensaje.showMessageDialog(null, "Los campos de precio y existencia deben ser valores numéricos.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            this.vProducto.jopMensaje.showMessageDialog(null, "Todos los campos deben de tener información según corresponda",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean verificarProductoEnDetalleFactura() {
        boolean productoEnDetalle = false;
        for (DetalleFacturaVO detalle : dfdao.consultarDetalleFactura()) {
            if (detalle.getIdProductoFK() == Integer.valueOf(this.vProducto.txtIdProducto.getText())) {
                productoEnDetalle = true;
                break;
            }
        }
        return productoEnDetalle;
    }

    private void eliminarProducto() {
        if (!this.vProducto.txtIdProducto.getText().isEmpty()) {
            if (!verificarProductoEnDetalleFactura()) {
                this.pvo.setIdProducto(Integer.parseInt(this.vProducto.txtIdProducto.getText()));
                if (pdao.eliminarProducto(pvo) == true) {
                    limpiarCampos();
                    this.vProducto.jopMensaje.showMessageDialog(null, "Producto eliminado correctamente.",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    this.vProducto.jopMensaje.showMessageDialog(null, "Error, no se pudo eliminar el producto.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                this.vProducto.jopMensaje.showMessageDialog(null, "No se puede eliminar el producto porque se encuentra en un detalle de factura.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            this.vProducto.jopMensaje.showMessageDialog(null, "No ha seleccionado ningún producto.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reporteProductos() {
        pdao.reporteProducto();
        pdao.jasperViewer.setDefaultCloseOperation(this.vProducto.DISPOSE_ON_CLOSE);
        pdao.jasperViewer.setVisible(true);
        banderaReporte = true;
    }

    private void limpiarCampos() {
        this.vProducto.txtIdProducto.setText("");
        this.vProducto.txtDescripcionProducto.setText("");
        this.vProducto.txtMarcaProducto.setText("");
        this.vProducto.txtPresentacionProducto.setText("");
        this.vProducto.txtCategoriaProducto.setText("");
        this.vProducto.txtPrecioCompraProducto.setText("");
        this.vProducto.txtPrecioVentaProducto.setText("");
        this.vProducto.txtExistenciaProducto.setText("");
        this.vProducto.cmbNombreProveedor.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vProducto.btnLimpiarCampos) {
            limpiarCampos();
        }
        if (ae.getSource() == this.vProducto.btnCrearProductos) {
            registrarProducto();
        }
        if (ae.getSource() == this.vProducto.btnActualizarProducto) {
            modificarProducto();
        }
        if (ae.getSource() == this.vProducto.btnEliminarProducto) {
            eliminarProducto();
        }
        if (ae.getSource() == this.vProducto.btnReporteProducto) {
            reporteProductos();
        }
        if (ae.getSource() == this.vProducto.btnSalirProducto) {
            this.vProducto.dispose();
            limpiarCampos();
            if (banderaReporte) {
                pdao.jasperViewer.setVisible(false);
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
        mostrarProductos();
        llenarComboboxProveedores();
    }

    @Override
    public void windowClosing(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
        limpiarCampos();
        if (banderaReporte) {
            pdao.jasperViewer.setVisible(false);
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
        mostrarProductos();
        borrarComboboxProveedores();
        llenarComboboxProveedores();
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == this.vProducto.tblProducto) {
            if (me.getClickCount() == 2) {
                llenarCamposProducto();
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
