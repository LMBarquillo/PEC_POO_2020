package entidades;


/**
 * Clase cliente. Define un cliente que puede comprar un mueble.
 * @author Luis Miguel Barquillo Romero
 */
public class Cliente extends Persona
{
    private String email;

    public Cliente(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, String email) {
        super(nombre, nif, direccion, codigoPostal, localidad, telefono);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
