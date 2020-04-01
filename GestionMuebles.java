import constantes.Color;
import constantes.Madera;
import constantes.Material;
import constantes.Valores;
import entidades.*;

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
				this.recepcionarPedido();
				break;
			case Valores.JefeMuebles.ASIGNAR:
				break;
			case Valores.JefeMuebles.INSPECCIONAR:
				break;
			case Valores.JefeMuebles.VOLVER:
				gestionPrincipalMuebles();
		}
	}

	private void gestionArtesanosMuebles() {
		int opcion = fabrica.getEs().getMenu().menuArtesanoMuebles();
		switch (opcion) {
			case Valores.ArtesanoMuebles.VER_TRABAJOS:
				break;
			case Valores.ArtesanoMuebles.CAMBIAR_ESTADO:
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
