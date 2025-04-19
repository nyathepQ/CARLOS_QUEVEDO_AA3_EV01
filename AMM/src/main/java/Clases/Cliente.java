package Clases;

import java.sql.Timestamp;

/**
 *
 * @author nyath
 */
public class Cliente extends Persona {
    //atributos
    private String direccion;
    private String observaciones;
        
    //constructores
    public Cliente() {
    }

    public Cliente(String direccion, String observaciones, String nombres, String apellidos, String telefono, String email, String user_crea) {
        super(nombres, apellidos, telefono, email, user_crea);
        this.direccion = direccion;
        this.observaciones = observaciones;
    }

    public Cliente(String direccion, String observaciones, String nombres, String apellidos, String telefono, String email, String user_modifica, Timestamp modificado_el) {
        super(nombres, apellidos, telefono, email, user_modifica, modificado_el);
        this.direccion = direccion;
        this.observaciones = observaciones;
    }

    public Cliente(String direccion, String observaciones, String codigo, String nombres, String apellidos, String telefono, String email, String user_crea, Timestamp creado_el, String user_modifica, Timestamp modificado_el) {
        super(codigo, nombres, apellidos, telefono, email, user_crea, creado_el, user_modifica, modificado_el);
        this.direccion = direccion;
        this.observaciones = observaciones;
    }
    
    //getters y settes

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String clienteToString(Cliente cliente) {
        return cliente.getNombres() + " " + cliente.getApellidos();
    }
    
}
