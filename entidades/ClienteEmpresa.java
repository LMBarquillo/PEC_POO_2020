package entidades;


/**
 * Clase ClienteEmpresa. Define a los clientes que son de tipo Persona Jurídica
 * @author Luis Miguel Barquillo Romero
 */
public class ClienteEmpresa extends Cliente
{
    private String personaContacto;

    public ClienteEmpresa(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, String email, String personaContacto) {
        super(nombre, nif, direccion, codigoPostal, localidad, telefono, email);
        this.personaContacto = personaContacto;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    @Override public boolean esEmpresa() {
        return true;
    }
}
