package Clases;
/**
 *
 * @author nyath
 */
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name= "servicio")
public class Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private int id_servicio;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente") //clave foreana
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_equipo") //clave foreana
    private Equipo equipo;
    
    @ManyToOne
    @JoinColumn(name = "id_tipoLimp") //clave foreana
    private Tipo_limpieza tipoLimpieza;
    
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "hora")
    private Time hora;
    
    @Column(name = "tiempo_estimado")
    private Time tiempo_estimado;
    
    @Column(name = "tiempo_finalizacion")
    private Time tiempo_finalizacion;
    
    @Column(name = "precio")
    private int precio;
    
    @Column(name = "observacion")
    private String observacion;
    
    @Column(name = "user_crea")
    private String user_crea;
    
    @Column(name = "creado_el", insertable = false, updatable = false)
    private Timestamp creado_el;
    
    @Column(name = "user_modifica")
    private String user_modifica;
    
    @Column(name = "modificado_el")
    private Timestamp modificado_el;
    
    //Constructores
    public Servicio(){        
    }

    public Servicio(Cliente cliente, Equipo equipo, Tipo_limpieza tipoLimpieza, Date fecha, Time hora, Time tiempo_estimado, Time tiempo_finalizacion, int precio, String observacion, String user_crea) {
        this.cliente = cliente;
        this.equipo = equipo;
        this.tipoLimpieza = tipoLimpieza;
        this.fecha = fecha;
        this.hora = hora;
        this.tiempo_estimado = tiempo_estimado;
        this.tiempo_finalizacion = tiempo_finalizacion;
        this.precio = precio;
        this.observacion = observacion;
        this.user_crea = user_crea;
    }

    public Servicio(Cliente cliente, Equipo equipo, Tipo_limpieza tipoLimpieza, Date fecha, Time hora, Time tiempo_estimado, Time tiempo_finalizacion, int precio, String observacion, String user_modifica, Timestamp modificado_el) {
        this.cliente = cliente;
        this.equipo = equipo;
        this.tipoLimpieza = tipoLimpieza;
        this.fecha = fecha;
        this.hora = hora;
        this.tiempo_estimado = tiempo_estimado;
        this.tiempo_finalizacion = tiempo_finalizacion;
        this.precio = precio;
        this.observacion = observacion;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    public Servicio(int id_servicio, Cliente cliente, Equipo equipo, Tipo_limpieza tipoLimpieza, Date fecha, Time hora, Time tiempo_estimado, Time tiempo_finalizacion, int precio, String observacion, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.id_servicio = id_servicio;
        this.cliente = cliente;
        this.equipo = equipo;
        this.tipoLimpieza = tipoLimpieza;
        this.fecha = fecha;
        this.hora = hora;
        this.tiempo_estimado = tiempo_estimado;
        this.tiempo_finalizacion = tiempo_finalizacion;
        this.precio = precio;
        this.observacion = observacion;
        this.user_crea = user_crea;
        this.creado_el = creado_el;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    // -- Getteres y Setters --

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Tipo_limpieza getTipoLimpieza() {
        return tipoLimpieza;
    }

    public void setTipoLimpieza(Tipo_limpieza tipoLimpieza) {
        this.tipoLimpieza = tipoLimpieza;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Time getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(Time tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public Time getTiempo_finalizacion() {
        return tiempo_finalizacion;
    }

    public void setTiempo_finalizacion(Time tiempo_finalizacion) {
        this.tiempo_finalizacion = tiempo_finalizacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
