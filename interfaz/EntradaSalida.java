package interfaz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Clase EntradaSalida (IN OUT). Contiene subclases que se encargarán de mostrar
 * menús o solicitudes de datos por pantalla y recopilar los datos introducidos.
 * 
 * @author Luis Miguel Barquillo
 */
public class EntradaSalida
{
    private Menu menu;
    private Datos datos;

    public EntradaSalida() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        this.menu = new Menu(br);
        this.datos = new Datos(br);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }
}
