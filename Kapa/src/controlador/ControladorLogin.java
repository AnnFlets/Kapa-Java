package controlador;

import extras.Extras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.ErrorDAO;
import modelo.ErrorVO;
import modelo.UsuarioDAO;
import modelo.UsuarioVO;
import vista.FrmLogin;
import vista.FrmMenu;

public class ControladorLogin implements ActionListener {

    FrmLogin vLogin = new FrmLogin();
    FrmMenu vMenu = new FrmMenu();
    UsuarioVO uvo = new UsuarioVO();
    UsuarioDAO udao = new UsuarioDAO();
    Extras extras = new Extras();
    ErrorVO evo = new ErrorVO();
    ErrorDAO edao = new ErrorDAO();
    boolean login;

    /**
     * Controlador con parámetros
     *
     * @param vLogin -> Representa la vista o JFrame del login
     * @param vMenu -> Representa la vista o JFrame del menú de administrador
     */
    public ControladorLogin(FrmLogin vLogin, FrmMenu vMenu) {
        this.vLogin = vLogin;
        this.vMenu = vMenu;
        this.vLogin.btnLogin.addActionListener(this);
    }

    /**
     * Verifica que el usuario y contraseña pertenezcan a algún registro tipo
     * administrador y que los campos no se encuentren vacíos
     */
    private void verificarUsuario() {
        login = false;
        String usuario = this.vLogin.txtUsuario.getText();
        String contrasenia = this.vLogin.txtContrasenia.getText();
        if (!(usuario.equals("") || contrasenia.equals(""))) {
            ArrayList<UsuarioVO> usuarios = udao.consultarUsuario();
            for (int i = 0; i < usuarios.size(); i++) {
                String user = usuarios.get(i).getNombreUsuario();
                String password = usuarios.get(i).getContraseniaUsuario();
                if (user.equals(usuario)) {
                    if (password.equals(contrasenia)) {
                        login = true;
                    }
                }
            }
        } else {
            this.vLogin.jopMensajeLogin.showMessageDialog(null, "No ha ingresado ningún usuario o contraseña.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarCampos() {
        this.vLogin.txtUsuario.setText("");
        this.vLogin.txtContrasenia.setText("");
        this.vLogin.lblMensajeLogin.setText("");
    }

    /**
     * Método que apertura el menú del administrador
     */
    private void abrirMenu() {
        this.vLogin.dispose();
        this.vMenu.setVisible(true);
        this.vMenu.setLocationRelativeTo(null);
        this.vMenu.setResizable(false);
        this.vMenu.setSize(1250, 610);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.vLogin.btnLogin) {
            verificarUsuario();
            if (login) {
                limpiarCampos();
                abrirMenu();
            } else {
                if (!(this.vLogin.txtUsuario.getText().equals("")
                        || this.vLogin.txtContrasenia.getText().equals(""))) {
                    evo.setDescripcionError("Usuario y/o contraseña incorrectos");
                    evo.setFechaError(extras.devolverFechaActual());
                    edao.insertarError(evo);
                    this.vLogin.lblMensajeLogin.setText("* Usuario y/o contraseña incorrectos");
                }
            }
        }
    }
}
