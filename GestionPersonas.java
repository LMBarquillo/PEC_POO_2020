import constantes.Condicion;
import constantes.Material;
import constantes.Turno;
import constantes.Valores;
import entidades.*;

import java.util.Date;
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

	/**
	 * M�todo para las acciones principales de gesti�n de empleados
	 */
	public void gestionEmpleados() {
		int opcion = fabrica.getEs().getMenu().menuGestionEmpleados();
		switch (opcion) {
			case Valores.GestionEmpleados.ALTA:
				altaEmpleados();
				break;
			case Valores.GestionEmpleados.BAJA:
				bajaEmpleados();
				break;
			case Valores.GestionEmpleados.MODIFICACION:
				modificarEmpleados();
				break;
			case Valores.GestionEmpleados.LISTADO:
				listadoEmpleados();
				break;
			case Valores.GestionEmpleados.VOLVER:
				fabrica.principal();
		}
	}

	/**
	 * M�todo para las acciones de gesti�n de clientes
	 */
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

	/**
	 * M�todo para dar de alta un nuevo empleado
	 */
	private void altaEmpleados() {
		Empleado empleado;
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (!fabrica.getBbddPersonas().existe(nif)) {
			String nombre = fabrica.getEs().getDatos().pedirString("Introduzca el nombre: ");
			String direccion = fabrica.getEs().getDatos().pedirString("Introduzca la direcci�n: ");
			String codigoPostal = fabrica.getEs().getDatos().pedirString("Introduzca el C.P.:");
			String localidad = fabrica.getEs().getDatos().pedirString("Introduzca la localidad: ");
			String telefono = fabrica.getEs().getDatos().pedirString("Introduzca el tel�fono: ");
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

	/**
	 * M�todo para dar de alta un Jefe
	 * @param nombre Nombre
	 * @param nif Nif
	 * @param direccion Direcci�n
	 * @param cp C�digo postal
	 * @param localidad Localidad
	 * @param telefono Tel�fono
	 * @param antiguedad Antiguedad en la empresa
	 * @param salario Salario mensual
	 */
	private void altaJefe(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario) {
		double acciones = fabrica.getEs().getDatos().pedirDecimal("Introduzca el porcentaje de acciones: ");
		fabrica.getBbddPersonas().insertar(new Jefe(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, acciones));
		System.out.println("El nuevo jefe ha sido insertado correctamente");
	}

	/**
	 * M�todo para dar de alta un empleado Comercial
	 * @param nombre Nombre
	 * @param nif Nif
	 * @param direccion Direcci�n
	 * @param cp C�digo postal
	 * @param localidad Localidad
	 * @param telefono Tel�fono
	 * @param antiguedad Antiguedad en la empresa
	 * @param salario Salario mensual
	 */
	private void altaComercial(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario) {
		double comision = fabrica.getEs().getDatos().pedirDecimal("Introduzca el porcentaje de comisi�n: ");
		fabrica.getBbddPersonas().insertar(new Comercial(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, comision));
		System.out.println("El nuevo comercial ha sido insertado correctamente");
	}

	/**
	 * M�todo para dar de alta un Artesano
	 * @param nombre Nombre
	 * @param nif Nif
	 * @param direccion Direcci�n
	 * @param cp C�digo postal
	 * @param localidad Localidad
	 * @param telefono Tel�fono
	 * @param antiguedad Antiguedad en la empresa
	 * @param salario Salario mensual
	 */
	private void altaArtesano(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario) {
		Material especialidad = Material.values()[fabrica.getEs().getMenu().menuListado("�Cu�l es su especialidad?", Material.values())-1];
		if(fabrica.getEs().getDatos().pedirBooleano("�El artesano est� en plantilla? (S/N): ")) {
			altaArtesanoPlantilla(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, especialidad);
		} else {
			altaArtesanoPorHoras(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, especialidad);
		}
	}

	/**
	 * M�todo para dar de alta un Artesano que se encuentra en plantilla
	 * @param nombre Nombre
	 * @param nif Nif
	 * @param direccion Direcci�n
	 * @param cp C�digo postal
	 * @param localidad Localidad
	 * @param telefono Tel�fono
	 * @param antiguedad Antiguedad en la empresa
	 * @param salario Salario mensual
	 * @param especialidad Especialidad del artesano
	 */
	private void altaArtesanoPlantilla(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario, Material especialidad) {
		Turno turno = Turno.values()[fabrica.getEs().getMenu().menuListado("�Qu� turno realiza?", Turno.values())-1];
		fabrica.getBbddPersonas().insertar(new ArtesanoEnPlantilla(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, especialidad, turno));
		System.out.println("El nuevo artesano en plantilla ha sido insertado correctamente");
	}

	/**
	 * M�todo para dar de alta un Artesano que trabaja por horas
	 * @param nombre Nombre
	 * @param nif Nif
	 * @param direccion Direcci�n
	 * @param cp C�digo postal
	 * @param localidad Localidad
	 * @param telefono Tel�fono
	 * @param antiguedad Antiguedad en la empresa
	 * @param salario Salario mensual
	 * @param especialidad Especialidad del artesano
	 */
	private void altaArtesanoPorHoras(String nombre, String nif, String direccion, String cp, String localidad, String telefono, Date antiguedad, double salario, Material especialidad) {
		int horas = fabrica.getEs().getDatos().pedirEntero("�Cu�ntas horas diarias realiza?", 1, 8);
		fabrica.getBbddPersonas().insertar(new ArtesanoPorHoras(nombre, nif, direccion, cp, localidad, telefono, antiguedad, salario, especialidad, horas));
		System.out.println("El nuevo artesano por horas ha sido insertado correctamente");
	}

	/**
	 * M�todo para modificar los datos de los empleados
	 */
	private void modificarEmpleados() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif).esEmpleado()) {
			Empleado empleado = (Empleado) fabrica.getBbddPersonas().obtener(nif);
			System.out.printf("Est� modificando el empleado con NIF %s.\n", nif);
			System.out.println("Escriba solo los datos que quiera modificar. El resto, pulse ENTER.");

			actualizaPersona(empleado);		// Actualizaci�n de datos comunes a toda persona.
			Date antiguedad = fabrica.getEs().getDatos().pedirFecha("Introduzca la antiguedad (DD/MM/AAAA): ", true);
			if(antiguedad != null) empleado.setAntiguedad(antiguedad);
			Double salario = fabrica.getEs().getDatos().pedirDecimal("Introduzca el salario: ", true);
			if(salario != null) empleado.setSalario(salario);

			switch (empleado.categoria()) {
				case JEFE:
					modificarJefe((Jefe) empleado);
					break;
				case COMERCIAL:
					modificarComercial((Comercial) empleado);
					break;
				default:
					modificarArtesano((Artesano) empleado);
					break;
			}
		}

		gestionEmpleados();
	}

	/**
	 * M�todo para la modificaci�n de los valores propios de un empleado Jefe
	 */
	private void modificarJefe(Jefe jefe) {
		Double acciones = fabrica.getEs().getDatos().pedirDecimal("Introduzca el porcentaje de acciones: ", true);
		if(acciones != null) jefe.setPorcentajeAcciones(acciones);
	}

	/**
	 * M�todo para la modificaci�n de los valores propios de un empleado Comercial
	 */
	private void modificarComercial(Comercial comercial) {
		Double comision = fabrica.getEs().getDatos().pedirDecimal("Introduzca la comisi�n: ", true);
		if(comision != null) comercial.setPorcentajeComision(comision);
	}

	/**
	 * M�todo para la modificaci�n de los valores propios de un empleado Artesano
	 */
	private void modificarArtesano(Artesano artesano) {
		if(artesano.condicion() == Condicion.POR_HORAS) {
			Integer horas = fabrica.getEs().getDatos().pedirEntero("Introduzca el n�mero de horas diarias: ", true, 1, 8);
			((ArtesanoPorHoras) artesano).setNumHoras(horas);
		} else {
			if(fabrica.getEs().getDatos().pedirBooleano("�Desea cambiar el turno de trabajo? (S/N): ")) {
				Turno turno = Turno.values()[fabrica.getEs().getMenu().menuListado("Seleccione el nuevo turno", Turno.values())-1];
				((ArtesanoEnPlantilla) artesano).setTurno(turno);
			}
		}
	}

	/**
	 * M�todo para la modificaci�n de los valores gen�ricos de todos los objetos que heredan de Persona
	 */
	private void actualizaPersona(Persona persona) {
		String nombre = fabrica.getEs().getDatos().pedirString("Introduzca el nombre: ", true);
		if (nombre.length() > 0) persona.setNombre(nombre);
		String direccion = fabrica.getEs().getDatos().pedirString("Introduzca la direcci�n: ", true);
		if (direccion.length() > 0) persona.setDireccion(direccion);
		String codigoPostal = fabrica.getEs().getDatos().pedirString("Introduzca el C.P.:", true);
		if (codigoPostal.length() > 0) persona.setCodigoPostal(codigoPostal);
		String localidad = fabrica.getEs().getDatos().pedirString("Introduzca la localidad: ", true);
		if (localidad.length() > 0) persona.setLocalidad(localidad);
		String telefono = fabrica.getEs().getDatos().pedirString("Introduzca el tel�fono: ", true);
		if (telefono.length() > 0) persona.setTelefono(telefono);
	}

	/**
	 * M�todo para dar de baja un empleado en la empresa
	 */
	private void bajaEmpleados() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif).esEmpleado()) {
			if (fabrica.getEs().getDatos().pedirBooleano(String.format("�Est� seguro de que desea eliminar el empleado con NIF %s? (S/N): ", nif))) {
				fabrica.getBbddPersonas().eliminar(nif);
				System.out.printf("El empleado con NIF %s ha sido eliminado.\n", nif);
			}
		} else {
			System.out.println("El NIF introducido no se corresponde con el de un empleado.");
		}
		gestionEmpleados();
	}

	/**
	 * M�todo para mostrar un listado de todos los empleados
	 */
	private void listadoEmpleados() {
		List<Persona> personas = fabrica.getBbddPersonas().listar();
		for (Persona p : personas) {
			if (p.esEmpleado()) {
				System.out.println(p.toString());
			}
		}
		gestionEmpleados();
	}

	/**
	 * M�todo para dar de alta un nuevo cliente
	 */
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

	/**
	 * M�todo para dar de baja a un cliente
	 */
	private void bajaClientes() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif).esCliente()) {
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

	/**
	 * M�todo para modificar los datos de un cliente
	 */
	private void modificarClientes() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif).esCliente()) {
			Cliente cliente = (Cliente) fabrica.getBbddPersonas().obtener(nif);
			System.out.printf("Est� modificando el cliente con NIF/CIF %s.\n", nif);
			System.out.println("Escriba solo los datos que quiera modificar. El resto, pulse ENTER.");

			actualizaPersona(cliente);
			String email = fabrica.getEs().getDatos().pedirString("Introduzca el email: ", true);
			if (email.length() > 0) cliente.setEmail(email);

			if (((Cliente) fabrica.getBbddPersonas().obtener(nif)).esEmpresa()) {
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

	/**
	 * M�todo para generar un listado con los clientes que se encuentran en el sistema
	 */
	private void listadoClientes() {
		List<Persona> personas = fabrica.getBbddPersonas().listar();
		for (Persona p : personas) {
			if (p.esCliente()) {
				System.out.println(p.toString());
			}
		}
		gestionClientes();
	}

	/**
	 * M�todo para asignar precio a un mueble y comunic�rselo al cliente.
	 */
	private void comunicarPrecio() {
		String nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF/CIF: ");
		if (fabrica.getBbddPersonas().existe(nif) && fabrica.getBbddPersonas().obtener(nif).esCliente()) {
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
				// En este punto, nuestra aplicaci�n podr�a enviar un email al cliente con la informaci�n
			} else {
				System.out.println("El cliente introducido no ha pedido ning�n mueble.");
			}
		} else {
			System.out.println("El nif introducido no se corresponde con el de un cliente.");
		}
		gestionClientes();
	}

	/**
	 * M�todo para notificar a un cliente de la entrega de su mueble
	 */
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
