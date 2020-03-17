import constantes.Valores;

/**
 * Clase GestionMuebles. Gestiona los menús de muebles y operaciones contra BBDDMuebles
 * 
 * @author Luis Miguel Barquillo
 */
public class GestionMuebles
{
    private Fabrica fabrica;

    public GestionMuebles(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    public void gestionPrincipalMuebles() {
        int opcion = fabrica.getMenu().menuPrincipalMuebles();
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
        int opcion = fabrica.getMenu().menuJefeMuebles();
        switch (opcion) {
            case Valores.JefeMuebles.RECEPCIONAR:
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
        int opcion = fabrica.getMenu().menuArtesanoMuebles();
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

    }
}
