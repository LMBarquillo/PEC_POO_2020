import constantes.Valores;
import interfaz.Menu;
import repositorio.BBDDMuebles;

/**
 * Clase Fabrica. Clase principal del programa, conteniendo el método main
 * 
 * @author Luis Miguel Barquillo Romero
 * @since 19/02/2020
 * @version 1.0.0
 */
public class Fabrica
{
    private Menu menu;
    private GestionMuebles gestionMuebles;

    public Fabrica() {
        menu = new Menu();
        BBDDMuebles bbddMuebles = new BBDDMuebles();
        gestionMuebles = new GestionMuebles(this, menu, bbddMuebles);
    }

    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica();

        fabrica.principal();
    }

    public void principal() {
        int opcion = menu.menuPrincipal();
        switch (opcion) {
            case Valores.Principal.MUEBLES:
                gestionMuebles.gestionPrincipalMuebles();
                break;
            case Valores.Principal.CLIENTES:
                // break;
            case Valores.Principal.EMPLEADOS:
                // break;
            case Valores.Principal.SALIR:
                menu.mostrarDespedida();
                System.exit(0);
        }
    }
}
