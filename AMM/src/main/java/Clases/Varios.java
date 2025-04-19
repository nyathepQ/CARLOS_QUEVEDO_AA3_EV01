package Clases;

import java.sql.Timestamp;

/**
 *
 * @author nyath
 */
public class Varios {
    private int codigo;
    private String nombre;
    private String user_crea;
    private Timestamp creado_el;
    private String user_modifica;
    private Timestamp modificado_el;
    
    
    //constructores
    public Varios() {    
    }

    public Varios(int codigo, String nombre, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    public Varios(String nombre, String user_crea) {
        this.nombre = nombre;
        this.user_crea = user_crea;
    }

    public Varios(String nombre, String user_modifica, Timestamp modificado_el) {
        this.nombre = nombre;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }
    
    
    //getters y setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
