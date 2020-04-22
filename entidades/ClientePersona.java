package entidades;


/**
 * Clase ClientePersona. Define a los clientes que son Persona Física
 * @author Luis Miguel Barquillo Romero
 */
public class ClientePersona extends Cliente
{
    public String skype;    // Ya no se me ocurría qué más campos meter...

    public ClientePersona(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, String email, String skype) {
        super(nombre, nif, direccion, codigoPostal, localidad, telefono, email);
        this.skype = skype;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override public boolean esEmpresa() {
        return false;
    }
}
