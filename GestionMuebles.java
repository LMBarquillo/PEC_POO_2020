import constantes.Madera;
import constantes.Valores;
import entidades.Cliente;
import entidades.MesaComedor;
import entidades.Persona;

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
	    int ancho = fabrica.getEs().getDatos().pedirEntero("Introduzca el ANCHO de la mesa en cm.: ");
	    int largo = fabrica.getEs().getDatos().pedirEntero("Introduzca el LARGO de la mesa en cm.: ");

	    switch (fabrica.getEs().getMenu().menuTipoMesa()) {
            case Valores.TipoMesa.COMEDOR:
                crearMesaComedor(cliente, referencia, ancho, largo);
                break;
            case Valores.TipoMesa.CAFE:
                break;
            case Valores.TipoMesa.DORMITORIO:
                break;
        }
    }

    private void crearMesaComedor(Cliente cliente, String referencia, int ancho, int largo) {
        Madera madera = Madera.values()[fabrica.getEs().getMenu().menuTipoMadera()-1];
        Boolean extensible = fabrica.getEs().getDatos().pedirBooleano("¿Desea que la mesa sea extensible? (S/N): ");

        fabrica.getBbddMuebles().insertar(new MesaComedor(referencia, cliente, ancho, largo, madera, extensible));
        System.out.println("La Mesa de Comedor ha sido insertada correctamente.");
        gestionJefeMuebles();
    }

    private void crearSilla(Cliente cliente, String referencia) {

    }
}
