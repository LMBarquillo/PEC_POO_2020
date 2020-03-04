import interfaz.Menu;

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

    public static void main(String[] args) {
        new Fabrica().start();
    }

    public void start() {
        menu = new Menu();
        menu.menuPrincipal();
    }
}
