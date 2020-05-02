import constantes.*;
import entidades.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase GestionMuebles. Gestiona los menús de muebles y operaciones contra BBDDMuebles
 *
 * @author Luis Miguel Barquillo
 */
public class GestionMuebles {
	private final Fabrica fabrica;

	public GestionMuebles(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	/**
	 * Menú principal de muebles
	 */
	public void gestionPrincipalMuebles() {
		int opcion = fabrica.getEs().getMenu().menuPrincipalMuebles();
		switch (opcion) {
			case Valores.PrincipalMuebles.JEFE:
				accesoMenuJefes();
				break;
			case Valores.PrincipalMuebles.ARTESANOS:
				gestionArtesanosMuebles();
				break;
			case Valores.PrincipalMuebles.VOLVER:
				fabrica.principal();
		}
	}

	/**
	 * Control de acceso de jefatura a menús con opciones propias de los jefes
	 */
	private void accesoMenuJefes() {
		String nif = fabrica.getEs().getDatos().pedirString("Por favor, introduce tu NIF: ");
		if(esJefe(nif)) {
			gestionJefeMuebles();
		} else {
			System.out.println("El nif introducido no pertenece a un jefe.");
		}
		gestionPrincipalMuebles();
	}

	/**
	 * Opciones de jefatura para la gestión de muebles
	 */
	private void gestionJefeMuebles() {
		int opcion = fabrica.getEs().getMenu().menuJefeMuebles();
		switch (opcion) {
			case Valores.JefeMuebles.RECEPCIONAR:
				recepcionarPedido();
				break;
			case Valores.JefeMuebles.ASIGNAR:
				asignarPedido();
				break;
			case Valores.JefeMuebles.INSPECCIONAR:
				inspeccionarPedido();
				break;
			case Valores.JefeMuebles.VOLVER:
				gestionPrincipalMuebles();
		}
	}

	/**
	 * Control de acceso de los artesanos para las opciones propias de su categoría
	 */
	private void gestionArtesanosMuebles() {
		String nif = fabrica.getEs().getDatos().pedirString("Por favor, introduce tu NIF: ");
		if(esArtesano(nif)) {
			menuGestionArtesanos(nif);
		} else {
			System.out.println("El nif introducido no pertenece a un artesano.");
		}
		gestionPrincipalMuebles();
	}

	/**
	 * Opciones de artesanos para la gestión de los muebles.
	 * @param nif Nif del artesano que realiza las acciones
	 */
	private void menuGestionArtesanos(String nif) {
		int opcion = fabrica.getEs().getMenu().menuArtesanoMuebles();
		switch (opcion) {
			case Valores.ArtesanoMuebles.VER_TRABAJOS:
				verTrabajos(nif);
				break;
			case Valores.ArtesanoMuebles.CAMBIAR_ESTADO:
				cambiarEstado(nif);
				break;
			case Valores.ArtesanoMuebles.ANADIR_ANOTACION:
				crearNotaMueble(nif);
				break;
			case Valores.ArtesanoMuebles.VOLVER:
				gestionPrincipalMuebles();
		}
	}

	/**
	 * Método para recepcionar pedido de un nuevo mueble
	 */
	private void recepcionarPedido() {
		String nifCliente = fabrica.getEs().getDatos().pedirString("Introduzca el NIF del cliente: ");
		if (fabrica.getBbddPersonas().existe(nifCliente)) {
			Persona persona = fabrica.getBbddPersonas().obtener(nifCliente);
			if (persona.esCliente()) {
				crearMueble((Cliente) persona);
			} else {
				System.out.println("El nif introducido no pertenece a un cliente.");
			}
		} else {
			System.out.println("El cliente no se encuentra. Debe darlo de alta antes de crear un pedido.");
		}
		gestionJefeMuebles();
	}

	/**
	 * Método para inspeccionar el progreso de un pedido
	 */
	private void inspeccionarPedido() {
		int numTrabajo = fabrica.getEs().getDatos().pedirEntero("Introduce el número de trabajo: ");
		if(fabrica.getBbddMuebles().existe(numTrabajo)) {
			Mueble mueble = fabrica.getBbddMuebles().obtener(numTrabajo);
			System.out.printf("El mueble seleccionado es: %s\n", mueble.toString());
			System.out.printf("El mueble ha sido pedido por: %s\n", mueble.getCliente().getNombre());
			if(mueble.getArtesano() == null) {
				System.out.println("El mueble no tiene asignado ningún artesano aún.");
			} else {
				System.out.printf("El mueble está siendo fabricado por: %s\n", mueble.getArtesano().getNombre());
			}
			System.out.printf("El estado actual del mueble es: %s\n", mueble.getEstado().toString());
			if(mueble.getNotas().size() > 0) {
				System.out.println("El artesano ha dejado las siguientes anotaciones: ");
				for (String nota : mueble.getNotas()) {
					System.out.printf("  - %s\n", nota);
				}
			}
		} else {
			System.out.printf("No existe el trabajo número %d.\n", numTrabajo);
		}
		gestionJefeMuebles();
	}

	/**
	 * Método para seleccionar un pedido al que poderle asignar un artesano
	 */
	private void asignarPedido() {
		int numTrabajo = fabrica.getEs().getDatos().pedirEntero("Introduce el número de trabajo: ");
		if(fabrica.getBbddMuebles().existe(numTrabajo)) {
			Mueble mueble = fabrica.getBbddMuebles().obtener(numTrabajo);
			System.out.printf("Ha seleccionado: %d: %s\n", mueble.getNumTrabajo(), mueble.toString());
			if(mueble.getArtesano() == null) {
				asignarArtesano(mueble);
			} else {
				System.out.printf("El trabajo seleccionado está siendo realizado por %s\n", mueble.getArtesano().getNombre());
				if(fabrica.getEs().getDatos().pedirBooleano("¿Desea asignarle un nuevo artesano? (S/N): ")) {
					asignarArtesano(mueble);
				}
			}
		} else {
			System.out.printf("No existe el trabajo número %d.\n", numTrabajo);
		}
		gestionJefeMuebles();
	}

	/**
	 * Método para ver el listado de los trabajos de un artesano según su nif
	 * @param nif Nif del artesano
	 */
	private void verTrabajos(String nif) {
			List<Mueble> lista = new ArrayList<>();
			for(Mueble mueble : fabrica.getBbddMuebles().listar()) {
				if(mueble.hasArtesano() && mueble.getArtesano().getNif().equals(nif) && mueble.getEstado() != Estado.FINALIZADO) {
					lista.add(mueble);
				}
			}
			if(lista.size() > 0) {
				System.out.println("Listado de muebles asignados: ");
				for(Mueble mueble : lista) {
					System.out.printf("  %d - %s - Estado: %s\n", mueble.getNumTrabajo(), mueble.toString(), mueble.getEstado().toString());
				}
			} else {
				System.out.println("Actualmente no tienes ningún mueble asignado.");
			}

		menuGestionArtesanos(nif);
	}

	/**
	 * Método para modificar el estado de un trabajo asignado a un artesano
	 * @param nif Nif del artesano
	 */
	private void cambiarEstado(String nif) {
		int trabajo = fabrica.getEs().getDatos().pedirEntero("Introduce el trabajo que desea modificar: ");
		if(fabrica.getBbddMuebles().existe(trabajo)) {
			if(fabrica.getBbddMuebles().obtener(trabajo).hasArtesano() &&
					fabrica.getBbddMuebles().obtener(trabajo).getArtesano().getNif().equals(nif)) {
				cambiarEstado(fabrica.getBbddMuebles().obtener(trabajo));
			} else {
				System.out.println("El trabajo introducido no te ha sido asignado.");
			}
		} else {
			System.out.println("El número de trabajo introducido no existe.");
		}

		menuGestionArtesanos(nif);
	}

	/**
	 * Método para crear anotaciones respecto a la fabricación de un mueble
	 * @param nif Nif del artesano
	 */
	private void crearNotaMueble(String nif) {
		int trabajo = fabrica.getEs().getDatos().pedirEntero("Introduce el trabajo al que deseas añadir una nota: ");
		if(fabrica.getBbddMuebles().existe(trabajo)) {
			Mueble mueble = fabrica.getBbddMuebles().obtener(trabajo);
			if(mueble.getArtesano() != null && mueble.getArtesano().getNif().equals(nif)) {
				mueble.addNota(fabrica.getEs().getDatos().pedirString("Introduce la nota: "));
			} else {
				System.out.println("El número de trabajo introducido no te ha sido asignado, y no puedes añadir notas.");
			}
		} else {
			System.out.println("El número de trabajo introducido no existe.");
		}

		menuGestionArtesanos(nif);
	}

	/**
	 * Método para cambiar el estado de fabricación de un mueble
	 * @param mueble Mueble que vamos a cambiar
	 */
	private void cambiarEstado(Mueble mueble) {
		Estado estado = Estado.values()[fabrica.getEs().getMenu().menuEstado()-1];
		mueble.setEstado(estado);
		System.out.printf("El trabajo número %d ha pasado al estado %s\n", mueble.getNumTrabajo(), estado.toString());
	}

	/**
	 * Método para asignar un pedido a un artesano concreto
	 * @param mueble Mueble que queremos asignar
	 */
	private void asignarArtesano(Mueble mueble) {
		String nif;
		do {
			nif = fabrica.getEs().getDatos().pedirString("Introduzca el NIF del artesano al que desea asignar el trabajo: ");
			if(!esArtesano(nif)) {
				System.out.println("El nif introducido no pertenece a un artesano.");
			}
		} while (!esArtesano(nif));

		mueble.setArtesano((Artesano) fabrica.getBbddPersonas().obtener(nif));
		System.out.printf("El trabajo número %d ha sido asignado a %s\n", mueble.getNumTrabajo(), mueble.getArtesano().getNombre());
		gestionJefeMuebles();
	}

	/**
	 * Método utilidad para saber si un nif pertenece a un artesano
	 * @param nif Nif que vamos a comprobar
	 * @return Devuelve si es o no artesano
	 */
	private boolean esArtesano(String nif) {
		return fabrica.getBbddPersonas().existe(nif) && (fabrica.getBbddPersonas().obtener(nif) instanceof Artesano);
	}

	/**
	 * Método utilidad para saber si un nif pertenece a un jefe
	 * @param nif Nif que vamos a comprobar
	 * @return Devuelve si es o no jefe
	 */
	private boolean esJefe(String nif) {
		return fabrica.getBbddPersonas().existe(nif) && (fabrica.getBbddPersonas().obtener(nif) instanceof Jefe);
	}

	/**
	 * Método para crear un nuevo Mueble en nuestro sistema
	 * @param cliente Cliente al que se le asignará el mueble
	 */
	private void crearMueble(Cliente cliente) {
		int numTrabajo = fabrica.getBbddMuebles().nuevoNumeroTrabajo();

		if (fabrica.getEs().getMenu().menuTipoMueble() == Valores.TipoMuebles.MESA) {
			crearMesa(cliente, numTrabajo);
		} else {
			crearSilla(cliente, numTrabajo);
		}
	}

	/**
	 * Método para crear una mesa
	 * @param cliente Cliente propietario
	 * @param numTrabajo Número de trabajo
	 */
	private void crearMesa(Cliente cliente, int numTrabajo) {
		int ancho = fabrica.getEs().getDatos().pedirEntero("Introduzca el ANCHO de la mesa en cm. (40-100): ", 40, 100);
		int largo = fabrica.getEs().getDatos().pedirEntero("Introduzca el LARGO de la mesa en cm. (80-200): ", 80, 200);

		switch (fabrica.getEs().getMenu().menuTipoMesa()) {
			case Valores.TipoMesa.COMEDOR:
				crearMesaComedor(cliente, numTrabajo, ancho, largo);
				break;
			case Valores.TipoMesa.CAFE:
				crearMesaCafe(cliente, numTrabajo, ancho, largo);
				break;
			case Valores.TipoMesa.DORMITORIO:
				crearMesaDormitorio(cliente, numTrabajo, ancho, largo);
				break;
		}
	}

	/**
	 * Método para crear una Mesa de Comedor
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param ancho Ancho de la mesa
	 * @param largo Largo de la mesa
	 */
	private void crearMesaComedor(Cliente cliente, int numTrabajo, int ancho, int largo) {
		Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera() - 1];
		boolean extensible = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la mesa sea extensible? (S/N): ");

		Mueble mesa = new MesaComedor(numTrabajo, cliente, ancho, largo, madera, extensible);
		fabrica.getBbddMuebles().insertar(mesa);
		cliente.getMuebles().add(mesa);
		System.out.printf("La Mesa de Comedor ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	/**
	 * Método para crear una Mesa de Café
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param ancho Ancho de la mesa
	 * @param largo Largo de la mesa
	 */
	private void crearMesaCafe(Cliente cliente, int numTrabajo, int ancho, int largo) {
		boolean revistero = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la mesa tenga revistero? (S/N): ");
		if (fabrica.getEs().getMenu().menuTipoMesaCafe() == Valores.TipoMesaCafe.MADERA) {
			crearMesaCafeMadera(cliente, numTrabajo, ancho, largo, revistero);
		} else {
			crearMesaCafeCristal(cliente, numTrabajo, ancho, largo, revistero);
		}
	}

	/**
	 * Método para crear una Mesa de Café de Madera
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param ancho Ancho de la mesa
	 * @param largo Largo de la mesa
	 * @param revistero Si tiene o no revistero
	 */
	private void crearMesaCafeMadera(Cliente cliente, int numTrabajo, int ancho, int largo, boolean revistero) {
		Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera() - 1];

		Mueble mesa = new MesaCafeMadera(numTrabajo, cliente, ancho, largo, revistero, madera);
		fabrica.getBbddMuebles().insertar(mesa);
		cliente.getMuebles().add(mesa);
		System.out.printf("La Mesa de Café de madera ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	/**
	 * Método para crear una Mesa de Café de Cristal
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param ancho Ancho de la mesa
	 * @param largo Largo de la mesa
	 * @param revistero Si tiene o no revistero
	 */
	private void crearMesaCafeCristal(Cliente cliente, int numTrabajo, int ancho, int largo, boolean revistero) {
		boolean labrado = fabrica.getEs().getDatos().pedirBooleano("¿Desea una labrado en el cristal? (S/N): ");

		Mueble mesa = new MesaCafeCristal(numTrabajo, cliente, ancho, largo, revistero, labrado);
		fabrica.getBbddMuebles().insertar(mesa);
		cliente.getMuebles().add(mesa);
		System.out.printf("La Mesa de Café de cristal ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	/**
	 * Método para crear una Mesa de Dormitorio
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param ancho Ancho de la mesa
	 * @param largo Largo de la mesa
	 */
	private void crearMesaDormitorio(Cliente cliente, int numTrabajo, int ancho, int largo) {
		Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera() - 1];
		int cajones = fabrica.getEs().getDatos().pedirEntero("Número de cajones (1-5): ", 1, 5);

		Mueble mesa = new MesaDormitorio(numTrabajo, cliente, ancho, largo, madera, cajones);
		fabrica.getBbddMuebles().insertar(mesa);
		cliente.getMuebles().add(mesa);
		System.out.printf("La Mesa de Dormitorio ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	/**
	 * Método para crear una Silla
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 */
	private void crearSilla(Cliente cliente, int numTrabajo) {
		boolean acolchada = fabrica.getEs().getDatos().pedirBooleano("¿Desea acolchar el asiento? (S/N): ");

		switch (fabrica.getEs().getMenu().menuTipoSilla()) {
			case Valores.TipoSilla.PLEGABLE:
				crearSillaPlegable(cliente, numTrabajo, acolchada);
				break;
			case Valores.TipoSilla.COCINA:
				crearSillaCocina(cliente, numTrabajo, acolchada);
				break;
			case Valores.TipoSilla.OFICINA:
				crearSillaOficina(cliente, numTrabajo, acolchada);
				break;
		}
	}

	/**
	 * Método para crear una Silla Plegable
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param acolchada Si está o no acolchada
	 */
	private void crearSillaPlegable(Cliente cliente, int numTrabajo, boolean acolchada) {
		Color color = Color.values()[fabrica.getEs().getMenu().menuColor() - 1];

		Silla silla = new SillaPlegable(numTrabajo, cliente, acolchada, color);
		fabrica.getBbddMuebles().insertar(silla);
		cliente.getMuebles().add(silla);
		System.out.printf("La Silla Plegable ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	/**
	 * Método para crear una Silla de cocina
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param acolchada Si está o no acolchada
	 */
	private void crearSillaCocina(Cliente cliente, int numTrabajo, boolean acolchada) {
		boolean respaldo = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la silla tenga respaldo? (S/N): ");
		Material material = Material.values()[fabrica.getEs().getMenu().menuMaterial() - 1];

		Silla silla = new SillaCocina(numTrabajo, material, cliente, acolchada, respaldo);
		fabrica.getBbddMuebles().insertar(silla);
		cliente.getMuebles().add(silla);
		System.out.printf("La Silla de Cocina ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	/**
	 * Método para crear una Silla de Oficina
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param acolchada Si está o no acolchada
	 */
	private void crearSillaOficina(Cliente cliente, int numTrabajo, boolean acolchada) {
		boolean reclinable = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la silla sea reclinable? (S/N): ");

		if (fabrica.getEs().getMenu().menuTipoSillaOficina() == Valores.TipoSillaOficina.CON_RUEDAS) {
			crearSillaOficinaConRuedas(cliente, numTrabajo, acolchada, reclinable);
		} else {
			crearSillaOficinaSinRuedas(cliente, numTrabajo, acolchada, reclinable);
		}
	}

	/**
	 * Método para crear una Silla de Oficina con ruedas
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param acolchada Si está o no acolchada
	 * @param reclinable Si es o no reclinable
	 */
	private void crearSillaOficinaConRuedas(Cliente cliente, int numTrabajo, boolean acolchada, boolean reclinable) {
		int numRuedas = fabrica.getEs().getDatos().pedirEntero("¿Cuántas ruedas quiere que tenga? (4-7): ", 4, 7);

		Silla silla = new SillaOficinaConRuedas(numTrabajo, cliente, acolchada, reclinable, numRuedas);
		fabrica.getBbddMuebles().insertar(silla);
		cliente.getMuebles().add(silla);
		System.out.printf("La Silla de Oficina con ruedas ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	/**
	 * Método para crear una Silla de Oficina sin ruedas
	 * @param cliente Cliente propietario
	 * @param numTrabajo número de trabajo
	 * @param acolchada Si está o no acolchada
	 * @param reclinable Si es o no reclinable
	 */
	private void crearSillaOficinaSinRuedas(Cliente cliente, int numTrabajo, boolean acolchada, boolean reclinable) {
		boolean antideslizante = fabrica.getEs().getDatos().pedirBooleano("¿Desea patas antideslizantes? (S/N): ");

		Silla silla = new SillaOficinaSinRuedas(numTrabajo, cliente, acolchada, reclinable, antideslizante);
		fabrica.getBbddMuebles().insertar(silla);
		cliente.getMuebles().add(silla);
		System.out.printf("La Silla de Oficina sin ruedas ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}
}
