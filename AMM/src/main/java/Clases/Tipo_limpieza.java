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
@Table(name = "tipo_limpieza")
public class Tipo_limpieza {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipoLimp")
    private int id_tipoLimp;
    
    @Column(name = "nombre_tipo")
    private String nombre_tipo;
    
    @Column(name = "user_crea")
    private String user_crea;
    
    @Column(name = "creado_el")
    private Timestamp creado_el;
    
    @Column(name = "user_modifica")
    private String user_modifica;
    
    @Column(name = "modificado_el")
    private Timestamp modificado_el;
    
    @OneToMany(mappedBy = "tipoLimpieza")
    private List<Servicio> servicios = new ArrayList();
    
    // -- Constructores --

    public Tipo_limpieza() {
    }

    public Tipo_limpieza(String nombre_tipo, String user_crea) {
        this.nombre_tipo = nombre_tipo;
        this.user_crea = user_crea;
    }

    public Tipo_limpieza(String nombre_tipo, String user_modifica, Timestamp modificado_el) {
        this.nombre_tipo = nombre_tipo;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    public Tipo_limpieza(int id_tipoLimp, String nombre_tipo, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.id_tipoLimp = id_tipoLimp;
        this.nombre_tipo = nombre_tipo;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }
    
    // -- Getters y Setters --

    public int getId_tipoLimp() {
        return id_tipoLimp;
    }

    public void setId_tipoLimp(int id_tipoLimp) {
        this.id_tipoLimp = id_tipoLimp;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
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

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
