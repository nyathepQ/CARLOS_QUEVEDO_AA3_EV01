package Clases;
/**
 *
 * @author nyath
 */
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipo") 
public class Equipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private int id_equipo;
    
    @Column(name = "nombre_equipo")
    private String nombre_equipo;
    
    @Column(name = "user_crea")
    private String user_crea;
    
    @Column(name = "creado_el")
    private Timestamp creado_el;
    
    @Column(name = "user_modifica")
    private String user_modifica;
    
    @Column(name = "modificado_el")
    private Timestamp modificado_el;
    
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuarios_equipo> usuariosEquipos = new ArrayList();
    
    @OneToMany(mappedBy = "equipo")
    private List<Servicio> servicios = new ArrayList<>();
    
    // === CAMPOS AUXILIARES (no mapeados a DB) ===
    @Transient
    private String lider;

    @Transient
    private String miembro1;

    @Transient
    private String miembro2;
    
    // -- Constructores --
    public Equipo() {
    }

    public Equipo(String nombre_equipo, String user_crea) {
        this.nombre_equipo = nombre_equipo;
        this.user_crea = user_crea;
    }

    public Equipo(String nombre_equipo, String user_modifica, Timestamp modificado_el) {
        this.nombre_equipo = nombre_equipo;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    public Equipo(int id_equipo, String nombre_equipo, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.id_equipo = id_equipo;
        this.nombre_equipo = nombre_equipo;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }
    
    // -- Getters y Setters --

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
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

    public List<Usuarios_equipo> getUsuariosEquipos() {
        return usuariosEquipos;
    }

    public void setUsuariosEquipos(List<Usuarios_equipo> usuariosEquipos) {
        this.usuariosEquipos = usuariosEquipos;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
    
     // === Getters y Setters para auxiliares ===

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
}
