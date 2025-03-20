package co.edu.uniquindio.model;

public class Habitacion {
    private String numero;
    private String tipoHabitacion;
    private String precio;
    private String disponibilidad;
    private String descripcion;

    public Habitacion(String numero, String tipoHabitacion, String precio, String disponibilidad, String descripcion) {
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.descripcion = descripcion;
    }

    public Habitacion() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
    
