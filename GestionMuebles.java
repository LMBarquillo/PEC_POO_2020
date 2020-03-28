import constantes.Forma;
import constantes.Madera;
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
		    if(persona instanceof Cliente) {
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
        String referencia;
        do {
            referencia = fabrica.getEs().getDatos().pedirString("Introduzca la referencia que desea asignar al mueble: ");
            if(fabrica.getBbddMuebles().existe(referencia)) {
                System.out.println("La referencia introducida ya existe. Por favor, elija otra.");
            }
        } while(fabrica.getBbddMuebles().existe(referencia));

        if(fabrica.getEs().getMenu().menuTipoMueble() == Valores.TipoMuebles.MESA) {
            crearMesa(cliente, referencia);
        } else {
            crearSilla(cliente, referencia);
        }
    }

    private void crearMesa(Cliente cliente, String referencia) {
	    int ancho = fabrica.getEs().getDatos().pedirEntero("Introduzca el ANCHO de la mesa en cm. (40-100): ", 40, 100);
	    int largo = fabrica.getEs().getDatos().pedirEntero("Introduzca el LARGO de la mesa en cm. (80-200): ", 80, 200);

	    switch (fabrica.getEs().getMenu().menuTipoMesa()) {
            case Valores.TipoMesa.COMEDOR:
                crearMesaComedor(cliente, referencia, ancho, largo);
                break;
            case Valores.TipoMesa.CAFE:
                crearMesaCafe(cliente, referencia, ancho, largo);
                break;
            case Valores.TipoMesa.DORMITORIO:
                crearMesaDormitorio(cliente, referencia, ancho, largo);
                break;
        }
    }

    private void crearMesaComedor(Cliente cliente, String referencia, int ancho, int largo) {
        Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera()-1];
        boolean extensible = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la mesa sea extensible? (S/N): ");

        fabrica.getBbddMuebles().insertar(new MesaComedor(referencia, cliente, ancho, largo, madera, extensible));
        System.out.println("La Mesa de Comedor ha sido insertada correctamente.");
        gestionJefeMuebles();
    }

    private void crearMesaCafe(Cliente cliente, String referencia, int ancho, int largo) {
        boolean revistero = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la mesa tenga revistero? (S/N): ");
        if(fabrica.getEs().getMenu().menuTipoMesaCafe() == Valores.TipoMesaCafe.MADERA) {
            crearMesaCafeMadera(cliente, referencia, ancho, largo, revistero);
        } else {
            crearMesaCafeCristal(cliente, referencia, ancho, largo, revistero);
        }
    }

    private void crearMesaCafeMadera(Cliente cliente, String referencia, int ancho, int largo, boolean revistero) {
	    Forma forma = Forma.values()[fabrica.getEs().getMenu().menuForma()-1];
	    Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera()-1];

	    fabrica.getBbddMuebles().insertar(new MesaCafeMadera(referencia, cliente, ancho, largo, revistero, forma, madera));
        System.out.println("La Mesa de Café de madera ha sido insertada correctamente.");
        gestionJefeMuebles();
    }

    private void crearMesaCafeCristal(Cliente cliente, String referencia, int ancho, int largo, boolean revistero) {
        boolean labrado = fabrica.getEs().getDatos().pedirBooleano("¿Desea una labrado en el cristal? (S/N): ");

        fabrica.getBbddMuebles().insertar(new MesaCafeCristal(referencia, cliente, ancho, largo, revistero, labrado));
        System.out.println("La Mesa de Café de cristal ha sido insertada correctamente.");
        gestionJefeMuebles();
    }

    private void crearMesaDormitorio(Cliente cliente, String referencia, int ancho, int largo) {
        Madera madera = Madera.values()[fabrica.getEs().getMenu().menuMadera()-1];
        int cajones = fabrica.getEs().getDatos().pedirEntero("Número de cajones (1-5): ", 1, 5);

        fabrica.getBbddMuebles().insertar(new MesaDormitorio(referencia, cliente, ancho, largo, madera, cajones));
        System.out.println("La Mesa de Dormitorio ha sido insertada correctamente.");
        gestionJefeMuebles();
    }

    private void crearSilla(Cliente cliente, String referencia) {

    }
}
