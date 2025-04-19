package Clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Timestamp;

/**
 *
 * @author nyath
 */
public class Servicio {
    private int id_servicio;
    private int id_cliente;
    private int id_equipo;
    private int id_tipo_limp;
    private LocalDate fecha;
    private LocalTime hora;
    private LocalTime tiempo_estimado;
    private LocalTime tiempo_finalizacion;
    private int precio;
    private String observacion;
    private String user_crea;
    private Timestamp creado_el;
    private String user_modifica;
    private Timestamp modificado_el;
    
    //Constructores
    public Servicio(){        
    }

    public Servicio(int id_servicio, int id_cliente, int id_equipo, int id_tipo_limp, LocalDate fecha, LocalTime hora, LocalTime tiempo_estimado, LocalTime tiempo_finalizacion, int precio, String observacion, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        this.id_servicio = id_servicio;
        this.id_cliente = id_cliente;
        this.id_equipo = id_equipo;
        this.id_tipo_limp = id_tipo_limp;
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

    public Servicio(int id_cliente, int id_equipo, int id_tipo_limp, LocalDate fecha, LocalTime hora, LocalTime tiempo_estimado, LocalTime tiempo_finalizacion, int precio, String observacion, String user_crea) {
        this.id_cliente = id_cliente;
        this.id_equipo = id_equipo;
        this.id_tipo_limp = id_tipo_limp;
        this.fecha = fecha;
        this.hora = hora;
        this.tiempo_estimado = tiempo_estimado;
        this.tiempo_finalizacion = tiempo_finalizacion;
        this.precio = precio;
        this.observacion = observacion;
        this.user_crea = user_crea;
    }

    public Servicio(int id_cliente, int id_equipo, int id_tipo_limp, LocalDate fecha, LocalTime hora, LocalTime tiempo_estimado, LocalTime tiempo_finalizacion, int precio, String observacion, String user_modifica, Timestamp modificado_el) {
        this.id_cliente = id_cliente;
        this.id_equipo = id_equipo;
        this.id_tipo_limp = id_tipo_limp;
        this.fecha = fecha;
        this.hora = hora;
        this.tiempo_estimado = tiempo_estimado;
        this.tiempo_finalizacion = tiempo_finalizacion;
        this.precio = precio;
        this.observacion = observacion;
        this.user_modifica = user_modifica;
        this.modificado_el = modificado_el;
    }

    //getters y setters

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public int getId_tipo_limp() {
        return id_tipo_limp;
    }

    public void setId_tipo_limp(int id_tipo_limp) {
        this.id_tipo_limp = id_tipo_limp;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalTime getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(LocalTime tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public LocalTime getTiempo_finalizacion() {
        return tiempo_finalizacion;
    }

    public void setTiempo_finalizacion(LocalTime tiempo_finalizacion) {
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
