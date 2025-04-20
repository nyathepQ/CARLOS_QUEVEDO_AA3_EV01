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
@Table(name ="tipo_usuario")
public class Tipo_usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipoUsua")
    private int id_tipoUsua;
    
    @Column(name = "nombre_tipo")
    private String nombre_tipo;
    
    @Column(name = "user_crea")
    private String user_crea;
    
    @Column(name = "creado_el", insertable = false, updatable = false)
    private Timestamp creado_el;
    
    @Column(name = "user_modifica")
    private String user_modifica;
    
    @Column(name = "modificado_el")
    private Timestamp modificado_el;
    
    //Relación uno a muchos
    @OneToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuarios = new ArrayList<>();
    
    // -- Constructores --

    public Tipo_usuario() {
    }

    public Tipo_usuario(String nombre_tipo, String user_crea) {
        this.nombre_tipo = nombre_tipo;
        this.user_crea = user_crea;
    }

    public Tipo_usuario(String nombre_tipo, String user_modifica, Timestamp modificado_el) {
        this.nombre_tipo = nombre_tipo;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    public Tipo_usuario(int id_tipoUsua, String nombre_tipo, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.id_tipoUsua = id_tipoUsua;
        this.nombre_tipo = nombre_tipo;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }
    
    // -- Getters y Setters --

    public int getId_tipoUsua() {
        return id_tipoUsua;
    }

    public void setId_tipoUsua(int id_tipoUsua) {
        this.id_tipoUsua = id_tipoUsua;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
