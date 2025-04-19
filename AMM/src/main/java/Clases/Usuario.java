package Clases;

import java.sql.Timestamp;

/**
 *
 * @author nyath
 */
public class Usuario extends Persona{
    private int tipo_user;
    private String user;
    private String password;
    private int tipo_docu;
    private String documento;
    
    //constructores
    public Usuario() {
    }
    
    public Usuario(int tipo_user, String user, String password, int tipo_docu, String documento, String nombres, String apellidos, String telefono, String email, String user_crea) {
        super(nombres, apellidos, telefono, email, user_crea);
        this.tipo_user = tipo_user;
        this.user = user;
        this.password = password;
        this.tipo_docu = tipo_docu;
        this.documento = documento;
    }

    public Usuario(int tipo_user, String user, String password, int tipo_docu, String documento, String nombres, String apellidos, String telefono, String email, String user_modifica, Timestamp modificado_el) {
        super(nombres, apellidos, telefono, email, user_modifica, modificado_el);
        this.tipo_user = tipo_user;
        this.user = user;
        this.password = password;
        this.tipo_docu = tipo_docu;
        this.documento = documento;
    }

    public Usuario(int tipo_user, String user, String password, int tipo_docu, String documento, String codigo, String nombres, String apellidos, String telefono, String email, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        super(codigo, nombres, apellidos, telefono, email, user_crea, creado_el, user_modifica, modificado_el);
        this.tipo_user = tipo_user;
        this.user = user;
        this.password = password;
        this.tipo_docu = tipo_docu;
        this.documento = documento;
    }
    
    //getters y setters

    public int getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(int tipo_user) {
        this.tipo_user = tipo_user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo_docu() {
        return tipo_docu;
    }

    public void setTipo_docu(int tipo_docu) {
        this.tipo_docu = tipo_docu;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }    
}