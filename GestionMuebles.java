import constantes.Valores;
import entidades.Mueble;
import interfaz.Menu;
import repositorio.BBDD;

/**
 * Clase GestionMuebles. Gestiona los menús de muebles y operaciones contra BBDDMuebles
 * 
 * @author Luis Miguel Barquillo
 */
public class GestionMuebles
{
    private Fabrica fabrica;
    private Menu menu;
    private BBDD<Mueble> bbdd;

    public GestionMuebles(Fabrica fabrica, Menu menu, BBDD<Mueble> bbdd) {
        this.fabrica = fabrica;
        this.menu = menu;
        this.bbdd = bbdd;
    }

    public void gestionPrincipalMuebles() {
        int opcion = menu.menuPrincipalMuebles();
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

    public void gestionJefeMuebles() {
        int opcion = menu.menuJefeMuebles();
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

    public void gestionArtesanosMuebles() {
        int opcion = menu.menuArtesanoMuebles();
        switch (opcion) {
            case Valores.ArtesanoMuebles.VER_TRABAJOS:
                break;
            case Valores.ArtesanoMuebles.CAMBIAR_ESTADO:
                break;
            case Valores.ArtesanoMuebles.VOLVER:
                gestionPrincipalMuebles();
        }
    }
}
