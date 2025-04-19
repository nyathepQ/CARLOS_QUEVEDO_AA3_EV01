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
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id_cliente;
    
    @Column(name = "nombre_cliente")
    private String nombre_cliente;
    
    @Column(name = "apellido_cliente")
    private String apellido_cliente;
    
    @Column(name = "direccion_cliente")
    private String direccion_cliente;
    
    @Column(name = "telefono_cliente")
    private String telefono_cliente;
    
    @Column(name = "correo_cliente")
    private String correo_cliente;
    
    @Column(name = "observacion_cliente")
    private String observacion_cliente;
    
    @Column(name = "user_crea")
    private String user_crea;
    
    @Column(name = "creado_el")
    private Timestamp creado_el;
    
    @Column(name = "user_modifica")
    private String user_modifica;
    
    @Column(name = "modificado_el")
    private Timestamp modificado_el;
    
    @OneToMany(mappedBy = "cliente")
    private List<Servicio> servicios = new ArrayList<>();
        
    //constructores
    public Cliente() {
    }

    public Cliente(String nombre_cliente, String apellido_cliente, String direccion_cliente, String telefono_cliente, String correo_cliente, String observacion_cliente, String user_crea) {
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.direccion_cliente = direccion_cliente;
        this.telefono_cliente = telefono_cliente;
        this.correo_cliente = correo_cliente;
        this.observacion_cliente = observacion_cliente;
        this.user_crea = user_crea;
    }
    
    public Cliente(String nombre_cliente, String apellido_cliente, String direccion_cliente, String telefono_cliente, String correo_cliente, String observacion_cliente, String user_modifica, Timestamp modificado_el) {
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.direccion_cliente = direccion_cliente;
        this.telefono_cliente = telefono_cliente;
        this.correo_cliente = correo_cliente;
        this.observacion_cliente = observacion_cliente;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }
    
    public Cliente(int id_cliente, String nombre_cliente, String apellido_cliente, String direccion_cliente, String telefono_cliente, String correo_cliente, String observacion_cliente, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.direccion_cliente = direccion_cliente;
        this.telefono_cliente = telefono_cliente;
        this.correo_cliente = correo_cliente;
        this.observacion_cliente = observacion_cliente;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }
    
    // -- Getters y Setters --

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getCorreo_cliente() {
        return correo_cliente;
    }

    public void setCorreo_cliente(String correo_cliente) {
        this.correo_cliente = correo_cliente;
    }

    public String getObservacion_cliente() {
        return observacion_cliente;
    }

    public void setObservacion_cliente(String observacion_cliente) {
        this.observacion_cliente = observacion_cliente;
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
    
    // -- Metodos --
    @Override
    public String toString(){
        String nombre = nombre_cliente != null ? nombre_cliente : "";
        String apellido = apellido_cliente != null ? apellido_cliente : "";
        return (nombre + " " + apellido).trim();
    }
}
