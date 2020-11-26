package jsf.clases;

import entidades.Rol;
import entidades.Usuario;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    private Usuario usuario = new Usuario();

    public LoginController() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void printUP() {
        System.out.println("User: " + usuario.getUsuario() + " Pass: " + usuario.getContraseña());
    }

    public String validate() {
        ConectionController cc = new ConectionController();
        System.out.println("User: " + usuario.getUsuario() + " Pass: " + usuario.getContraseña());
        ResultSet resultSet = cc.validateUser(usuario.getUsuario(), usuario.getContraseña());
        if (resultSet != null) {
            Rol rol = new Rol();
            try {
                rol.setId(resultSet.getShort("id_rol"));
                System.out.println("Rol: " + rol.getId());
                String rolString = String.valueOf(rol.getId()).trim();
                System.out.println("rolString: " + rolString);
                 if (rolString.contains("0")) {
                    System.out.println("Entrando a ADMINISTRACION");
                    return "/paginas/admin/administrador.xhtml";
                }
                if (rolString.contains("1")) {
                    System.out.println("Entrando al proceso 1");
                    return "/paginas/admin/carroceria.xhtml";
                }
                if (rolString.contains("2")) {
                    System.out.println("Entrando al proceso 2");
                    return "/paginas/admin/puertas.xhtml";
                }
                if (rolString.contains("3")) {
                    System.out.println("Entrando al proceso 3");
                    return "/paginas/admin/pintura.xhtml";
                }
                if (rolString.contains("4")) {
                    System.out.println("Entrando al proceso 4");
                    return "/paginas/admin/llantas.xhtml";
                }
                if (rolString.contains("5")) {
                    System.out.println("Entrando al proceso 5");
                    return "/paginas/admin/motor.xhtml";
                }
                if (rolString.contains("6")) {
                    System.out.println("Entrando al proceso 6");
                    return "/paginas/admin/interiores.xhtml";
                }
                if (rolString.contains("7")) {
                    System.out.println("Entrando al proceso 7");
                    return "/paginas/admin/rendimiento.xhtml";
                }
                if (rolString.contains("8")) {
                    System.out.println("Entrando al proceso 8");
                    return "/paginas/admin/calidad.xhtml";
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return "login.xhtml";
        }
        return "login.xhtml";
    }
}
