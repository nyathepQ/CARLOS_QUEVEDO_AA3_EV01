package Clases;

import java.sql.Timestamp;
/**
 *
 * @author nyath
 */
public class EquipoTrabajo {
    private int codigo;
    private String nombre_equipo;
    private String lider;
    private String miembro1;
    private String miembro2;
    private String user_crea;
    private Timestamp creado_el;
    private String user_modifica;
    private Timestamp modificado_el;

    public EquipoTrabajo() {        
    }

    public EquipoTrabajo(int codigo, String nombre_equipo, String lider, String miembro1, String miembro2, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.codigo = codigo;
        this.nombre_equipo = nombre_equipo;
        this.lider = lider;
        this.miembro1 = miembro1;
        this.miembro2 = miembro2;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    public EquipoTrabajo(String nombre_equipo, String lider, String miembro1, String miembro2, String user_crea) {
        this.nombre_equipo = nombre_equipo;
        this.lider = lider;
        this.miembro1 = miembro1;
        this.miembro2 = miembro2;
        this.user_crea = user_crea;
    }

    public EquipoTrabajo(String nombre_equipo, String lider, String miembro1, String miembro2, String user_modifica, Timestamp modificado_el) {
        this.nombre_equipo = nombre_equipo;
        this.lider = lider;
        this.miembro1 = miembro1;
        this.miembro2 = miembro2;
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

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public String getLider() {
        return lider;
    }

    public void setLider(String lider) {
        this.lider = lider;
    }

    public String getMiembro1() {
        return miembro1;
    }

    public void setMiembro1(String miembro1) {
        this.miembro1 = miembro1;
    }

    public String getMiembro2() {
        return miembro2;
    }

    public void setMiembro2(String miembro2) {
        this.miembro2 = miembro2;
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
