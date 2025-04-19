package Clases;
/**
 *
 * @author nyath
 */
import jakarta.persistence.*;

@Entity
@Table(name="usuarios_equipo")
public class Usuarios_equipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios_equipo")
    private int id_usuarios_equipo;
    
    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @Column(name = "es_lider")
    private boolean es_lider;
    
    // -- Constructores --

    public Usuarios_equipo() {
    }

    public Usuarios_equipo(Equipo equipo, Usuario usuario, boolean es_lider) {
        this.equipo = equipo;
        this.usuario = usuario;
        this.es_lider = es_lider;
    }

    public Usuarios_equipo(int id_usuarios_equipo, Equipo equipo, Usuario usuario, boolean es_lider) {
        this.id_usuarios_equipo = id_usuarios_equipo;
        this.equipo = equipo;
        this.usuario = usuario;
        this.es_lider = es_lider;
    }

    // -- Getters y Setters --

    public int getId_usuarios_equipo() {
        return id_usuarios_equipo;
    }

    public void setId_usuarios_equipo(int id_usuarios_equipo) {
        this.id_usuarios_equipo = id_usuarios_equipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean getEs_lider() {
        return es_lider;
    }

    public void setEs_lider(boolean es_lider) {
        this.es_lider = es_lider;
    }
}
