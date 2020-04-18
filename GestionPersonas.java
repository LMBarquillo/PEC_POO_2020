import constantes.Valores;
import entidades.Cliente;
import entidades.ClienteEmpresa;
import entidades.ClientePersona;

/**
 * Clase GestionPersonas. Gestiona los menús de Personas y Clientes y las operaciones contra BBDDPersonas
 *
 * @author Luis Miguel Barquillo
 */
public class GestionPersonas {
	private Fabrica fabrica;

	public GestionPersonas(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	public void gestionUsuarios() {
		int opcion = fabrica.getEs().getMenu().menuGestionUsuarios();
		switch (opcion) {
			case Valores.GestionUsuarios.ALTA:

				break;
			case Valores.GestionUsuarios.BAJA:
				break;
			case Valores.GestionUsuarios.MODIFICACION:
				break;
			case Valores.GestionUsuarios.VOLVER:
				fabrica.principal();
		}
	}

	public void gestionClientes() {
		int opcion = fabrica.getEs().getMenu().menuGestionClientes();
		switch (opcion) {
			case Valores.GestionClientes.ALTA:
                altaClientes();
				break;
			case Valores.GestionClientes.BAJA:
				break;
			case Valores.GestionClientes.MODIFICACION:
			    modificarClientes();
				break;
			case Valores.GestionClientes.COMUNICAR_PRECIO:
				break;
			case Valores.GestionClientes.AVISAR_ENTREGA:
				break;
			case Valores.GestionClientes.VOLVER:
				fabrica.principal();
		}
	}

	private void altaClientes() {
		Cliente cliente;
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if(!fabrica.getBbddPersonas().existe(nif)) {
            String nombre = fabrica.getEs().getDatos().pedirString("Introduzca el nombre: ");
            String direccion = fabrica.getEs().getDatos().pedirString("Introduzca la dirección: ");
            String codigoPostal = fabrica.getEs().getDatos().pedirString("Introduzca el C.P.:");
            String localidad = fabrica.getEs().getDatos().pedirString("Introduzca la localidad: ");
            String telefono = fabrica.getEs().getDatos().pedirString("Introduzca el teléfono: ");
            String email = fabrica.getEs().getDatos().pedirString("Introduzca el email: ");

            if (fabrica.getEs().getDatos().pedirBooleano("¿El cliente es empresa? (S/N): ")) {
                String contacto = fabrica.getEs().getDatos().pedirString("Introduzca la persona de contacto: ");
                cliente = new ClienteEmpresa(nombre, nif, direccion, codigoPostal, localidad, telefono, email, contacto);
            } else {
                String skype = fabrica.getEs().getDatos().pedirString("Introduzca la dirección de skype: ");
                cliente = new ClientePersona(nombre, nif, direccion, codigoPostal, localidad, telefono, email, skype);
            }
            fabrica.getBbddPersonas().insertar(cliente);
            System.out.printf("El cliente con NIF %s ha sido introducido correctamente.\n", nif);
        } else {
            System.out.println("El nif introducido ya se encuentra en la base de datos.");
        }
        gestionClientes();
	}

	private void bajaClientes() {

    }

    private void modificarClientes() {
        String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
        if(fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif) instanceof Cliente) {
            Cliente cliente = (Cliente) fabrica.getBbddPersonas().obtener(nif);
            System.out.printf("Está modificando el cliente con NIF/CIF %s.\n", nif);
            System.out.println("Escriba solo los datos que quiera modificar. El resto, pulse ENTER.");

            String nombre = fabrica.getEs().getDatos().pedirString("Introduzca el nombre: ", true);
            if(nombre.length() > 0) cliente.setNombre(nombre);
            String direccion = fabrica.getEs().getDatos().pedirString("Introduzca la dirección: ", true);
            if(direccion.length() > 0) cliente.setDireccion(direccion);
            String codigoPostal = fabrica.getEs().getDatos().pedirString("Introduzca el C.P.:", true);
            if(codigoPostal.length() > 0) cliente.setCodigoPostal(codigoPostal);
            String localidad = fabrica.getEs().getDatos().pedirString("Introduzca la localidad: ", true);
            if(localidad.length() > 0) cliente.setLocalidad(localidad);
            String telefono = fabrica.getEs().getDatos().pedirString("Introduzca el teléfono: ", true);
            if(telefono.length() > 0) cliente.setTelefono(telefono);
            String email = fabrica.getEs().getDatos().pedirString("Introduzca el email: ", true);
            if(email.length() > 0) cliente.setEmail(email);

            if (fabrica.getBbddPersonas().obtener(nif) instanceof ClienteEmpresa) {
                String contacto = fabrica.getEs().getDatos().pedirString("Introduzca la persona de contacto: ", true);
                if(contacto.length() > 0) ((ClienteEmpresa) cliente).setPersonaContacto(contacto);
            } else {
                String skype = fabrica.getEs().getDatos().pedirString("Introduzca la dirección de skype: ", true);
                if(skype.length() > 0) ((ClientePersona) cliente).setSkype(skype);
            }
            System.out.println("Se ha modificado correctamente el cliente.");
        } else {
            System.out.println("El nif introducido no se corresponde con el de un cliente.");
        }
        gestionClientes();
    }



}
