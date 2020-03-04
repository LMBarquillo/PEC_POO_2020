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
    private BufferedReader br;

    public Menu() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public int menuPrincipal() {
        return leerOpcion(mostrarMenuPrincipal());
    }

    public int menuPrincipalMuebles() {
        return leerOpcion(mostrarMenuPrincipalMuebles());
    }

    public int menuJefeMuebles() {
        return leerOpcion(mostrarMenuJefeMuebles());
    }

    public int menuArtesanoMuebles() {
        return leerOpcion(mostrarMenuArtesanoMuebles());
    }

    private int leerOpcion(int max) {
        try {
            String lectura;
            do {
                lectura = br.readLine();
                if(incorrecto(lectura, max)) {
                    System.out.printf("- Por favor, escoja una opción entre 1 y %d: ", max);
                }
            } while(incorrecto(lectura, max));

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
        System.out.println("- 1. Gestión de muebles                          -");
        System.out.println("- 2. Gestión de clientes                         -");
        System.out.println("- 3. Gestión de empleados                        -");
        System.out.println("- 4. Salir del programa                          -");
        mostrarPie();
        return 4;
    }

    private int mostrarMenuPrincipalMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Acciones de jefatura                        -");
        System.out.println("- 2. Acciones de fabricación                     -");
        System.out.println("- 3. Volver al menú anterior                     -");
        mostrarPie();
        return 3;
    }

    private int mostrarMenuJefeMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Recepcionar un pedido                       -");
        System.out.println("- 2. Asignar pedido a un artesano                -");
        System.out.println("- 3. Inspeccionar progreso de un pedido          -");
        System.out.println("- 4. Volver al menú anterior                     -");
        mostrarPie();
        return 4;
    }

    private int mostrarMenuArtesanoMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Ver mis trabajos por estado determinado     -");
        System.out.println("- 2. Cambiar estado de un trabajo                -");
        System.out.println("- 3. Volver al menú anterior                     -");
        mostrarPie();
        return 3;
    }

    private void mostrarCabecera() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("-          Gestor de mobiliario v.1.0.           -");
        System.out.println("--------------------------------------------------");
    }

    private void mostrarPie() {
        System.out.println("--------------------------------------------------");
        System.out.print("- Escoja una opción: ");
    }

    public void mostrarDespedida() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("-     Gracias por usar Gestor de mobiliario      -");
        System.out.println("--------------------------------------------------");

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
