import constantes.Valores;

/**
 * Clase GestionPersonas. Gestiona los menús de Personas y Clientes y las operaciones contra BBDDPersonas
 *
 * @author Luis Miguel Barquillo
 */
public class GestionPersonas
{
    private Fabrica fabrica;

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
                break;
            case Valores.GestionClientes.BAJA:
                break;
            case Valores.GestionClientes.MODIFICACION:
                break;
            case Valores.GestionClientes.COMUNICAR_PRECIO:
                break;
            case Valores.GestionClientes.AVISAR_ENTREGA:
                break;
            case Valores.GestionClientes.VOLVER:
                fabrica.principal();
        }
    }




}
