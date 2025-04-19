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
@Table(name="usuario")
public class Usuario {
    
    @Id
    @Column(name = "id_usuario")
    private String id_usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario") //clave foranea
    private Tipo_usuario tipoUsuario;
    
    @Column(name = "nombre_usuario")
    private String nombre_usuario;
    
    @Column(name = "contrasena_usuario")
    private String contrasena_usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_tipoDocu") //clave foranea
    private Tipo_documento tipoDocumento;
    
    @Column(name = "documento_usuario")
    private String documento_usuario;
    
    @Column(name = "nombres")
    private String nombres;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "correo_usuario")
    private String correo_usuario;
    
    @Column(name = "user_crea")
    private String user_crea;
    
    @Column(name = "creado_el")
    private Timestamp creado_el;
    
    @Column(name = "user_modifica")
    private String user_modifica;
    
    @Column(name = "modificado_el")
    private Timestamp modificado_el;
    
    @OneToMany(mappedBy = "usuario")
    private List<Usuarios_equipo> usuariosEquipos = new ArrayList<>();
    
    // -- Constructores --
    public Usuario() {
    }

    public Usuario(Tipo_usuario tipoUsuario, String nombre_usuario, String contrasena_usuario, Tipo_documento tipoDocumento, String documento_usuario, String nombres, String apellidos, String telefono, String correo_usuario, String user_crea) {
        this.tipoUsuario = tipoUsuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasena_usuario = contrasena_usuario;
        this.tipoDocumento = tipoDocumento;
        this.documento_usuario = documento_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo_usuario = correo_usuario;
        this.user_crea = user_crea;
    }

    public Usuario(Tipo_usuario tipoUsuario, String nombre_usuario, String contrasena_usuario, Tipo_documento tipoDocumento, String documento_usuario, String nombres, String apellidos, String telefono, String correo_usuario, String user_modifica, Timestamp modificado_el) {
        this.tipoUsuario = tipoUsuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasena_usuario = contrasena_usuario;
        this.tipoDocumento = tipoDocumento;
        this.documento_usuario = documento_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo_usuario = correo_usuario;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    public Usuario(String id_usuario, Tipo_usuario tipoUsuario, String nombre_usuario, String contrasena_usuario, Tipo_documento tipoDocumento, String documento_usuario, String nombres, String apellidos, String telefono, String correo_usuario, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.id_usuario = id_usuario;
        this.tipoUsuario = tipoUsuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasena_usuario = contrasena_usuario;
        this.tipoDocumento = tipoDocumento;
        this.documento_usuario = documento_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo_usuario = correo_usuario;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }
    
    // -- Getters y Setters --

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Tipo_usuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Tipo_usuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena_usuario() {
        return contrasena_usuario;
    }

    public void setContrasena_usuario(String contrasena_usuario) {
        this.contrasena_usuario = contrasena_usuario;
    }

    public Tipo_documento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Tipo_documento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento_usuario() {
        return documento_usuario;
    }

    public void setDocumento_usuario(String documento_usuario) {
        this.documento_usuario = documento_usuario;
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

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
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
    
    // -- Metodos --
    @Override
    public String toString(){
        String nombre = nombres != null ? nombres : "";
        String apellido = apellidos != null ? apellidos : "";
        return (nombre + " " + apellido).trim();
    }
}