package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase Menu. Muestra menús con opciones en consola y devuelve el resultado.
 * 
 * @author Luis Miguel Barquillo
 */
public class Menu
{
    public int menuPrincipal() {
        return leerOpcion(mostrarMenuPrincipal());
    }

    private int leerOpcion(int max) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String lectura;
            do {
                lectura = br.readLine();
                if(incorrecto(lectura, max)) {
                    System.out.printf("- Por favor, escoja una opción entre 1 y %d: ", max);
                }
            } while(incorrecto(lectura, max));

            br.close();
            return opcion(lectura);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private boolean incorrecto(String s, int max) {
        return opcion(s) < 1 || opcion(s) > max;
    }

    private int opcion(String s) {
        try {
            return Integer.parseInt(s);
        } catch(NumberFormatException ex) {
            return 0;
        }
    }

    private int mostrarMenuPrincipal() {
        mostrarCabecera();
        System.out.println("- 1. Gestión de muebles                     -");
        System.out.println("- 2. Gestión de clientes                    -");
        System.out.println("- 3. Gestión de empleados                   -");
        System.out.println("- 4. Salir del programa                     -");
        mostrarPie();
        return 4;
    }

    private void mostrarCabecera() {
        System.out.println("---------------------------------------------");
        System.out.println("-       Gestor de mobiliario v.1.0.         -");
        System.out.println("---------------------------------------------");
    }
    private void mostrarPie() {
        System.out.println("---------------------------------------------");
        System.out.print("- Escoja una opción: ");
    }
}
