package entidades;

/**
 * Clase Persona. Define los objeto de tipo persona
 * @author : Luis Miguel Barquillo
 */
public abstract class Persona
{
    private String nombre;  // o Razón Social
    private String nif;     // o CIF
    private String direccion;
    private String codigoPostal;
    private String localidad;
    private String telefono;

    public Persona(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public abstract boolean esEmpleado();
    public abstract boolean esCliente();

    @Override public String toString() {
        return String.format("%s: %s - %s %s (%s)", nif, nombre, codigoPostal, localidad, telefono);
    }
}
