import constantes.Material;
import constantes.Turno;
import constantes.Valores;
import entidades.*;

import java.util.Date;
import java.util.List;

/**
 * Clase GestionPersonas. Gestiona los menús de Personas y Clientes y las operaciones contra BBDDPersonas
 *
 * @author Luis Miguel Barquillo
 */
public class GestionPersonas {
	private final Fabrica fabrica;

	public GestionPersonas(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	public void gestionEmpleados() {
		int opcion = fabrica.getEs().getMenu().menuGestionEmpleados();
		switch (opcion) {
			case Valores.GestionEmpleados.ALTA:
				altaEmpleados();
				break;
			case Valores.GestionEmpleados.BAJA:
				break;
			case Valores.GestionEmpleados.MODIFICACION:
				break;
			case Valores.GestionEmpleados.LISTADO:
				listadoEmpleados();
				break;
			case Valores.GestionEmpleados.VOLVER:
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


	private void altaEmpleados() {
		Empleado empleado;
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (!fabrica.getBbddPersonas().existe(nif)) {
			String nombre = fabrica.getEs().getDatos().pedirString("Introduzca el nombre: ");
			String direccion = fabrica.getEs().getDatos().pedirString("Introduzca la dirección: ");
			String codigoPostal = fabrica.getEs().getDatos().pedirString("Introduzca el C.P.:");
			String localidad = fabrica.getEs().getDatos().pedirString("Introduzca la localidad: ");
			String telefono = fabrica.getEs().getDatos().pedirString("Introduzca el teléfono: ");
			Date antiguedad = fabrica.getEs().getDatos().pedirFecha("Introduzca la antiguedad (DD/MM/AAAA): ");
			double salario = fabrica.getEs().getDatos().pedirDecimal("Introduzca el salario: ");

			switch (fabrica.getEs().getMenu().menuTipoEmpleado()) {
				case Valores.TipoEmpleado.JEFE:
					altaJefe(nombre, nif, direccion, codigoPostal, localidad, telefono, antiguedad, salario);
					break;
				case Valores.TipoEmpleado.COMERCIAL:
					altaComercial(nombre, nif, direccion, codigoPostal, localidad, telefono, antiguedad, salario);
					break;
				case Valores.TipoEmpleado.ARTESANO:
					altaArtesano(nombre, nif, direccion, codigoPostal, localidad, telefono, antiguedad, salario);
					break;
			}
		} else {
			System.out.println("El NIF introducido ya se encuentra en la base de datos.");
		}
		gestionEmpleados();
	}

	private void altaJefe(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario) {
		double acciones = fabrica.getEs().getDatos().pedirDecimal("Introduzca el porcentaje de acciones: ");
		fabrica.getBbddPersonas().insertar(new Jefe(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, acciones));
		System.out.println("El nuevo jefe ha sido insertado correctamente");
	}

	private void altaComercial(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario) {
		double comision = fabrica.getEs().getDatos().pedirDecimal("Introduzca el porcentaje de comisión: ");
		fabrica.getBbddPersonas().insertar(new Comercial(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, comision));
		System.out.println("El nuevo comercial ha sido insertado correctamente");
	}

	private void altaArtesano(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario) {
		Material especialidad = Material.values()[fabrica.getEs().getMenu().menuListado("¿Cuál es su especialidad?", Material.values())-1];
		if(fabrica.getEs().getDatos().pedirBooleano("¿El artesano está en plantilla? (S/N): ")) {
			altaArtesanoPlantilla(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, especialidad);
		} else {
			altaArtesanoPorHoras(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, especialidad);
		}
	}

	private void altaArtesanoPlantilla(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario, Material especialidad) {
		Turno turno = Turno.values()[fabrica.getEs().getMenu().menuListado("¿Qué turno realiza?", Turno.values())-1];
		fabrica.getBbddPersonas().insertar(new ArtesanoEnPlantilla(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, especialidad, turno));
		System.out.println("El nuevo artesano en plantilla ha sido insertado correctamente");
	}

	private void altaArtesanoPorHoras(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario, Material especialidad) {
		int horas = fabrica.getEs().getDatos().pedirEntero("¿Cuántas horas diarias realiza?", 1, 8);
		fabrica.getBbddPersonas().insertar(new ArtesanoPorHoras(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, especialidad, horas));
		System.out.println("El nuevo artesano por horas ha sido insertado correctamente");
	}

	private void listadoEmpleados() {
		List<Persona> personas = fabrica.getBbddPersonas().listar();
		for (Persona p : personas) {
			if (p instanceof Empleado) {
				System.out.println(p.toString());
			}
		}
		gestionEmpleados();
	}

	private void altaClientes() {
		Cliente cliente;
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (!fabrica.getBbddPersonas().existe(nif)) {
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
			System.out.println("El NIF introducido ya se encuentra en la base de datos.");
		}
		gestionClientes();
	}

	private void bajaClientes() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif) instanceof Cliente) {
			// Ante una eliminación, pedir siempre confirmación
			if (fabrica.getEs().getDatos().pedirBooleano(String.format("¿Está seguro de que desea eliminar el cliente con NIF %s? (S/N): ", nif))) {
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
			System.out.printf("Está modificando el cliente con NIF/CIF %s.\n", nif);
			System.out.println("Escriba solo los datos que quiera modificar. El resto, pulse ENTER.");

			String nombre = fabrica.getEs().getDatos().pedirString("Introduzca el nombre: ", true);
			if (nombre.length() > 0) cliente.setNombre(nombre);
			String direccion = fabrica.getEs().getDatos().pedirString("Introduzca la dirección: ", true);
			if (direccion.length() > 0) cliente.setDireccion(direccion);
			String codigoPostal = fabrica.getEs().getDatos().pedirString("Introduzca el C.P.:", true);
			if (codigoPostal.length() > 0) cliente.setCodigoPostal(codigoPostal);
			String localidad = fabrica.getEs().getDatos().pedirString("Introduzca la localidad: ", true);
			if (localidad.length() > 0) cliente.setLocalidad(localidad);
			String telefono = fabrica.getEs().getDatos().pedirString("Introduzca el teléfono: ", true);
			if (telefono.length() > 0) cliente.setTelefono(telefono);
			String email = fabrica.getEs().getDatos().pedirString("Introduzca el email: ", true);
			if (email.length() > 0) cliente.setEmail(email);

			if (fabrica.getBbddPersonas().obtener(nif) instanceof ClienteEmpresa) {
				String contacto = fabrica.getEs().getDatos().pedirString("Introduzca la persona de contacto: ", true);
				if (contacto.length() > 0) ((ClienteEmpresa) cliente).setPersonaContacto(contacto);
			} else {
				String skype = fabrica.getEs().getDatos().pedirString("Introduzca la dirección de skype: ", true);
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
				System.out.println("Se le comunicará al cliente la siguiente información:");
				System.out.printf("Mueble: %s\n", mueble);
				System.out.printf("Precio: %.2f\n", mueble.getPrecio());
			} else {
				System.out.println("El cliente introducido no ha pedido ningún mueble.");
			}
		} else {
			System.out.println("El nif introducido no se corresponde con el de un cliente.");
		}
		gestionClientes();
	}

	private void avisarEntrega() {
		int id = fabrica.getEs().getDatos().pedirEntero("Introduzca el Número de Trabajo: ");
		if (fabrica.getBbddMuebles().existe(id)) {
			Mueble mueble = fabrica.getBbddMuebles().obtener(id);
			System.out.printf("El mueble introducido ha sido pedido por %s\n", mueble.getCliente().getNombre());
			if(fabrica.getEs().getDatos().pedirBooleano("¿Desea notificarle que ya está disponible para la recogida? (S/N): ")) {
				// En este punto enviaríamos un email.
				System.out.printf("Se le ha enviado una notificación al cliente al email: %s\n", mueble.getCliente().getEmail());
				System.out.printf("Puede avisarle personalmente en el teléfono: %s\n", mueble.getCliente().getTelefono());
			}
		} else {
			System.out.println("El trabajo introducido no existe.");
		}
		gestionClientes();
	}
}
