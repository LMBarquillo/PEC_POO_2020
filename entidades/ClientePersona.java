package entidades;


/**
 * Clase ClientePersona. Define a los clientes que son Persona F�sica
 * @author Luis Miguel Barquillo Romero
 */
public class ClientePersona extends Cliente
{
    public String skype;    // Ya no se me ocurr�a qu� m�s campos meter...

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
