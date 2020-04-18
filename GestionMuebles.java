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
	private Fabrica fabrica;

	public GestionMuebles(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	public void gestionPrincipalMuebles() {
		int opcion = fabrica.getEs().getMenu().menuPrincipalMuebles();
		switch (opcion) {
			case Valores.PrincipalMuebles.JEFE:
				gestionJefeMuebles();
				break;
			case Valores.PrincipalMuebles.ARTESANOS:
				gestionArtesanosMuebles();
				break;
			case Valores.PrincipalMuebles.VOLVER:
				fabrica.principal();
		}
	}

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

	private void gestionArtesanosMuebles() {
		String nif = fabrica.getEs().getDatos().pedirString("Por favor, introduce tu NIF: ");
		if(esArtesano(nif)) {
			menuGestionArtesanos(nif);
		} else {
			System.out.println("El nif introducido no pertenece a un artesano.");
		}
		gestionPrincipalMuebles();
	}

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

	private void recepcionarPedido() {
		String nifCliente = fabrica.getEs().getDatos().pedirString("Introduzca el NIF del cliente: ");
		if (fabrica.getBbddPersonas().existe(nifCliente)) {
			Persona persona = fabrica.getBbddPersonas().obtener(nifCliente);
			if (persona instanceof Cliente) {
				crearMueble((Cliente) persona);
			} else {
				System.out.println("El nif introducido no pertenece a un cliente.");
			}
		} else {
			System.out.println("El cliente no se encuentra. Debe darlo de alta antes de crear un pedido.");
		}
		gestionJefeMuebles();
	}

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
					System.out.printf("  %d - %s\n", mueble.getNumTrabajo(), mueble.toString());
				}
			} else {
				System.out.println("Actualmente no tienes ningún mueble asignado.");
			}

		menuGestionArtesanos(nif);
	}

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

	private void cambiarEstado(Mueble mueble) {
		Estado estado = Estado.values()[fabrica.getEs().getMenu().menuEstado()-1];
		mueble.setEstado(estado);
		System.out.printf("El trabajo número %d ha pasado al estado %s", mueble.getNumTrabajo(), estado.toString());
		gestionArtesanosMuebles();
	}

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

	private boolean esArtesano(String nif) {
		return fabrica.getBbddPersonas().existe(nif) && (fabrica.getBbddPersonas().obtener(nif) instanceof Artesano);
	}

	private void crearMueble(Cliente cliente) {
		int numTrabajo = fabrica.getBbddMuebles().nuevoNumeroTrabajo();

		if (fabrica.getEs().getMenu().menuTipoMueble() == Valores.TipoMuebles.MESA) {
			crearMesa(cliente, numTrabajo);
		} else {
			crearSilla(cliente, numTrabajo);
		}
	}

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

	private void crearMesaComedor(Cliente cliente, int numTrabajo, int ancho, int largo) {
		Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera() - 1];
		boolean extensible = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la mesa sea extensible? (S/N): ");

		fabrica.getBbddMuebles().insertar(new MesaComedor(numTrabajo, cliente, ancho, largo, madera, extensible));
		System.out.printf("La Mesa de Comedor ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	private void crearMesaCafe(Cliente cliente, int numTrabajo, int ancho, int largo) {
		boolean revistero = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la mesa tenga revistero? (S/N): ");
		if (fabrica.getEs().getMenu().menuTipoMesaCafe() == Valores.TipoMesaCafe.MADERA) {
			crearMesaCafeMadera(cliente, numTrabajo, ancho, largo, revistero);
		} else {
			crearMesaCafeCristal(cliente, numTrabajo, ancho, largo, revistero);
		}
	}

	private void crearMesaCafeMadera(Cliente cliente, int numTrabajo, int ancho, int largo, boolean revistero) {
		Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera() - 1];

		fabrica.getBbddMuebles().insertar(new MesaCafeMadera(numTrabajo, cliente, ancho, largo, revistero, madera));
		System.out.printf("La Mesa de Café de madera ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	private void crearMesaCafeCristal(Cliente cliente, int numTrabajo, int ancho, int largo, boolean revistero) {
		boolean labrado = fabrica.getEs().getDatos().pedirBooleano("¿Desea una labrado en el cristal? (S/N): ");

		fabrica.getBbddMuebles().insertar(new MesaCafeCristal(numTrabajo, cliente, ancho, largo, revistero, labrado));
		System.out.printf("La Mesa de Café de cristal ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	private void crearMesaDormitorio(Cliente cliente, int numTrabajo, int ancho, int largo) {
		Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera() - 1];
		int cajones = fabrica.getEs().getDatos().pedirEntero("Número de cajones (1-5): ", 1, 5);

		fabrica.getBbddMuebles().insertar(new MesaDormitorio(numTrabajo, cliente, ancho, largo, madera, cajones));
		System.out.printf("La Mesa de Dormitorio ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

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

	private void crearSillaPlegable(Cliente cliente, int numTrabajo, boolean acolchada) {
		Color color = Color.values()[fabrica.getEs().getMenu().menuColor() - 1];

		fabrica.getBbddMuebles().insertar(new SillaPlegable(numTrabajo, cliente, acolchada, color));
		System.out.printf("La Silla Plegable ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	private void crearSillaCocina(Cliente cliente, int numTrabajo, boolean acolchada) {
		boolean respaldo = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la silla tenga respaldo? (S/N): ");
		Material material = Material.values()[fabrica.getEs().getMenu().menuMaterial() - 1];

		fabrica.getBbddMuebles().insertar(new SillaCocina(numTrabajo, material, cliente, acolchada, respaldo));
		System.out.printf("La Silla de Cocina ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	private void crearSillaOficina(Cliente cliente, int numTrabajo, boolean acolchada) {
		boolean reclinable = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la silla sea reclinable? (S/N): ");

		if (fabrica.getEs().getMenu().menuTipoSillaOficina() == Valores.TipoSillaOficina.CON_RUEDAS) {
			crearSillaOficinaConRuedas(cliente, numTrabajo, acolchada, reclinable);
		} else {
			crearSillaOficinaSinRuedas(cliente, numTrabajo, acolchada, reclinable);
		}
	}

	private void crearSillaOficinaConRuedas(Cliente cliente, int numTrabajo, boolean acolchada, boolean reclinable) {
		int numRuedas = fabrica.getEs().getDatos().pedirEntero("¿Cuántas ruedas quiere que tenga? (4-7): ", 4, 7);

		fabrica.getBbddMuebles().insertar(new SillaOficinaConRuedas(numTrabajo, cliente, acolchada, reclinable, numRuedas));
		System.out.printf("La Silla de Oficina con ruedas ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}

	private void crearSillaOficinaSinRuedas(Cliente cliente, int numTrabajo, boolean acolchada, boolean reclinable) {
		boolean antideslizante = fabrica.getEs().getDatos().pedirBooleano("¿Desea patas antideslizantes? (S/N): ");

		fabrica.getBbddMuebles().insertar(new SillaOficinaSinRuedas(numTrabajo, cliente, acolchada, reclinable, antideslizante));
		System.out.printf("La Silla de Oficina sin ruedas ha sido insertada correctamente, con número de trabajo: %d ", numTrabajo);
		gestionJefeMuebles();
	}
}
