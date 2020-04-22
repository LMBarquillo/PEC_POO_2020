import constantes.Valores;
import entidades.*;

import java.util.List;

/**
 * Clase GestionPersonas. Gestiona los men�s de Personas y Clientes y las operaciones contra BBDDPersonas
 *
 * @author Luis Miguel Barquillo
 */
public class GestionPersonas {
	private final Fabrica fabrica;

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
				bajaClientes();
				break;
			case Valores.GestionClientes.MODIFICACION:
				modificarClientes();
				break;
			case Valores.GestionClientes.LISTADO:
				listadoClientes();
				break;
			case Valores.GestionClientes.COMUNICAR_PRECIO:
				comunicarPrecio();
				break;
			case Valores.GestionClientes.AVISAR_ENTREGA:
				avisarEntrega();
				break;
			case Valores.GestionClientes.VOLVER:
				fabrica.principal();
		}
	}

	private void altaClientes() {
		Cliente cliente;
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (!fabrica.getBbddPersonas().existe(nif)) {
			String nombre = fabrica.getEs().getDatos().pedirString("Introduzca el nombre: ");
			String direccion = fabrica.getEs().getDatos().pedirString("Introduzca la direcci�n: ");
			String codigoPostal = fabrica.getEs().getDatos().pedirString("Introduzca el C.P.:");
			String localidad = fabrica.getEs().getDatos().pedirString("Introduzca la localidad: ");
			String telefono = fabrica.getEs().getDatos().pedirString("Introduzca el tel�fono: ");
			String email = fabrica.getEs().getDatos().pedirString("Introduzca el email: ");

			if (fabrica.getEs().getDatos().pedirBooleano("�El cliente es empresa? (S/N): ")) {
				String contacto = fabrica.getEs().getDatos().pedirString("Introduzca la persona de contacto: ");
				cliente = new ClienteEmpresa(nombre, nif, direccion, codigoPostal, localidad, telefono, email, contacto);
			} else {
				String skype = fabrica.getEs().getDatos().pedirString("Introduzca la direcci�n de skype: ");
				cliente = new ClientePersona(nombre, nif, direccion, codigoPostal, localidad, telefono, email, skype);
			}
			fabrica.getBbddPersonas().insertar(cliente);
			System.out.printf("El cliente con NIF %s ha sido introducido correctamente.\n", nif);
		} else {
			System.out.println("El NIF introducido ya se encuentra en la base de datos.");
		}
		gestionClientes();
	}

	private void bajaClientes() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif) instanceof Cliente) {
			// Ante una eliminaci�n, pedir siempre confirmaci�n
			if (fabrica.getEs().getDatos().pedirBooleano(String.format("�Est� seguro de que desea eliminar el cliente con NIF %s? (S/N): ", nif))) {
				fabrica.getBbddPersonas().eliminar(nif);
				System.out.printf("El cliente con NIF %s ha sido eliminado.\n", nif);
			}
		} else {
			System.out.println("El NIF introducido no se corresponde con el de un cliente.");
		}
		gestionClientes();
	}

	private void modificarClientes() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif) instanceof Cliente) {
			Cliente cliente = (Cliente) fabrica.getBbddPersonas().obtener(nif);
			System.out.printf("Est� modificando el cliente con NIF/CIF %s.\n", nif);
			System.out.println("Escriba solo los datos que quiera modificar. El resto, pulse ENTER.");

			String nombre = fabrica.getEs().getDatos().pedirString("Introduzca el nombre: ", true);
			if (nombre.length() > 0) cliente.setNombre(nombre);
			String direccion = fabrica.getEs().getDatos().pedirString("Introduzca la direcci�n: ", true);
			if (direccion.length() > 0) cliente.setDireccion(direccion);
			String codigoPostal = fabrica.getEs().getDatos().pedirString("Introduzca el C.P.:", true);
			if (codigoPostal.length() > 0) cliente.setCodigoPostal(codigoPostal);
			String localidad = fabrica.getEs().getDatos().pedirString("Introduzca la localidad: ", true);
			if (localidad.length() > 0) cliente.setLocalidad(localidad);
			String telefono = fabrica.getEs().getDatos().pedirString("Introduzca el tel�fono: ", true);
			if (telefono.length() > 0) cliente.setTelefono(telefono);
			String email = fabrica.getEs().getDatos().pedirString("Introduzca el email: ", true);
			if (email.length() > 0) cliente.setEmail(email);

			if (fabrica.getBbddPersonas().obtener(nif) instanceof ClienteEmpresa) {
				String contacto = fabrica.getEs().getDatos().pedirString("Introduzca la persona de contacto: ", true);
				if (contacto.length() > 0) ((ClienteEmpresa) cliente).setPersonaContacto(contacto);
			} else {
				String skype = fabrica.getEs().getDatos().pedirString("Introduzca la direcci�n de skype: ", true);
				if (skype.length() > 0) ((ClientePersona) cliente).setSkype(skype);
			}
			System.out.println("Se ha modificado correctamente el cliente.");
		} else {
			System.out.println("El nif introducido no se corresponde con el de un cliente.");
		}
		gestionClientes();
	}

	private void listadoClientes() {
		List<Persona> personas = fabrica.getBbddPersonas().listar();
		for (Persona p : personas) {
			if (p instanceof Cliente) {
				System.out.println(p.toString());
			}
		}
		gestionClientes();
	}

	private void comunicarPrecio() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif) instanceof Cliente) {
			Cliente cliente = (Cliente) fabrica.getBbddPersonas().obtener(nif);
			if (cliente.getMuebles().size() > 0) {
				int pos = fabrica.getEs().getMenu().menuListado("Elija el mueble del cliente: ", cliente.getMuebles().toArray()) - 1;
				Mueble mueble = cliente.getMuebles().get(pos);
				if (mueble.getPrecio() == null) {
					Double precio = fabrica.getEs().getDatos().pedirDecimal("Introduzca el precio del mueble: ");
					mueble.setPrecio(precio);
				}
				System.out.println("Se le comunicar� al cliente la siguiente informaci�n:");
				System.out.printf("Mueble: %s\n", mueble);
				System.out.printf("Precio: %.2f\n", mueble.getPrecio());
			} else {
				System.out.println("El cliente introducido no ha pedido ning�n mueble.");
			}
		} else {
			System.out.println("El nif introducido no se corresponde con el de un cliente.");
		}
		gestionClientes();
	}

	private void avisarEntrega() {
		int id = fabrica.getEs().getDatos().pedirEntero("Introduzca el N�mero de Trabajo: ");
		if (fabrica.getBbddMuebles().existe(id)) {
			Mueble mueble = fabrica.getBbddMuebles().obtener(id);
			System.out.printf("El mueble introducido ha sido pedido por %s\n", mueble.getCliente().getNombre());
			if(fabrica.getEs().getDatos().pedirBooleano("�Desea notificarle que ya est� disponible para la recogida? (S/N): ")) {
				// En este punto enviar�amos un email.
				System.out.printf("Se le ha enviado una notificaci�n al cliente al email: %s\n", mueble.getCliente().getEmail());
				System.out.printf("Puede avisarle personalmente en el tel�fono: %s\n", mueble.getCliente().getTelefono());
			}
		} else {
			System.out.println("El trabajo introducido no existe.");
		}
		gestionClientes();
	}
}
