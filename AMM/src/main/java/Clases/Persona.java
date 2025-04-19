package Clases;

import java.sql.Timestamp;
/**
 *
 * @author nyath
 */
public class Persona {
    //atributos
    private String codigo;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String user_crea;
    private Timestamp creado_el;
    private String user_modifica;
    private Timestamp modificado_el;
        
    //constructores
    
    public Persona(){        
    }
    
    public Persona(String nombres, String apellidos, String telefono, String email, String user_crea){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.user_crea = user_crea;
    }

    public Persona(String nombres, String apellidos, String telefono, String email, String user_modifica, Timestamp modificado_el) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }    
    
    
    public Persona(String codigo, String nombres, String apellidos, String telefono, String email, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }
    
    //metodos

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_crea() {
        return user_crea;
    }

    public void setUser_crea(String user_crea) {
        this.user_crea = user_crea;
    }

    public Timestamp getCreado_el() {
        return creado_el;
    }

    public void setCreado_el(Timestamp creado_el) {
        this.creado_el = creado_el;
    }

    public String getUser_modifica() {
        return user_modifica;
    }

    public void setUser_modifica(String user_modifica) {
        this.user_modifica = user_modifica;
    }

    public Timestamp getModificado_el() {
        return modificado_el;
    }

    public void setModificado_el(Timestamp modificado_el) {
        this.modificado_el = modificado_el;
    }

    
    
}
