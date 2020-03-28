package interfaz;

import constantes.Forma;
import constantes.Madera;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase Menu. Muestra men�s con opciones en consola y devuelve el resultado.
 * 
 * @author Luis Miguel Barquillo
 */
public class Menu
{
    private BufferedReader br;

    public Menu(BufferedReader br) {
        this.br = br;
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

    public int menuGestionUsuarios() {
        return leerOpcion(mostrarMenuGestionUsuarios());
    }

    public int menuGestionClientes() {
        return leerOpcion(mostrarMenuGestionClientes());
    }

    public int menuTipoMueble() {
        return leerOpcion(mostrarMenuTipoMueble());
    }

    public int menuTipoMesa() {
        return leerOpcion(mostrarMenuTipoMesa());
    }

    public int menuTipoMesaCafe() {
        return leerOpcion(mostrarMenuTipoMesaCafe());
    }

    public int menuTipoSilla() {
        return leerOpcion(mostrarMenuTipoSilla());
    }

    public int menuTipoSillaOficina() {
        return leerOpcion(mostrarMenuTipoSillaOficina());
    }

    public int menuMadera() {
        return leerOpcion(mostrarMenuMadera());
    }

    public int menuForma() {
        return leerOpcion(mostrarMenuForma());
    }

    private int leerOpcion(int max) {
        try {
            String lectura;
            do {
                lectura = br.readLine();
                if(incorrecto(lectura, max)) {
                    System.out.printf("- Por favor, escoja una opci�n entre 1 y %d: ", max);
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
            return -1;
        }
    }

    private int mostrarMenuPrincipal() {
        mostrarCabecera();
        System.out.println("- 1. Gesti�n de muebles                          -");
        System.out.println("- 2. Gesti�n de clientes                         -");
        System.out.println("- 3. Gesti�n de empleados                        -");
        System.out.println("- 4. Salir del programa                          -");
        mostrarPie();
        return 4;
    }

    private int mostrarMenuPrincipalMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Acciones de jefatura                        -");
        System.out.println("- 2. Acciones de fabricaci�n                     -");
        System.out.println("- 3. Volver al men� anterior                     -");
        mostrarPie();
        return 3;
    }

    private int mostrarMenuJefeMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Recepcionar un pedido                       -");
        System.out.println("- 2. Asignar pedido a un artesano                -");
        System.out.println("- 3. Inspeccionar progreso de un pedido          -");
        System.out.println("- 4. Volver al men� anterior                     -");
        mostrarPie();
        return 4;
    }

    private int mostrarMenuArtesanoMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Ver mis trabajos por estado determinado     -");
        System.out.println("- 2. Cambiar estado de un trabajo                -");
        System.out.println("- 3. Volver al men� anterior                     -");
        mostrarPie();
        return 3;
    }

    private int mostrarMenuGestionUsuarios() {
        mostrarCabecera();
        System.out.println("- 1. Alta de nuevo usuario                       -");
        System.out.println("- 2. Baja de usuario                             -");
        System.out.println("- 3. Modificar un usuario                        -");
        System.out.println("- 4. Volver al men� anterior                     -");
        mostrarPie();
        return 4;
    }

    private int mostrarMenuGestionClientes() {
        mostrarCabecera();
        System.out.println("- 1. Alta de nuevo cliente                       -");
        System.out.println("- 2. Baja de cliente                             -");
        System.out.println("- 3. Modificar un cliente                        -");
        System.out.println("- 4. Comunicaci�n de precios de pedidos          -");
        System.out.println("- 5. Avisar de pedido listo para entrega         -");
        System.out.println("- 6. Volver al men� anterior                     -");
        mostrarPie();
        return 6;
    }

    private int mostrarMenuTipoMueble() {
        System.out.println("Tipo de mueble: ");
        System.out.println(" - 1. Mesa");
        System.out.println(" - 2. Silla");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    private int mostrarMenuTipoMesa() {
        System.out.println("Tipo de mesa: ");
        System.out.println(" - 1. Mesa de Comedor");
        System.out.println(" - 2. Mesa de Caf�");
        System.out.println(" - 3. Mesa de Dormitorio");
        System.out.print("Elija el tipo deseado: ");
        return 3;
    }

    private int mostrarMenuTipoMesaCafe() {
        System.out.println("Tipo de mesa de caf�: ");
        System.out.println(" - 1. Mesa de Caf� de Madera");
        System.out.println(" - 2. Mesa de Caf� de Cristal");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    private int mostrarMenuTipoSilla() {
        System.out.println("Tipo de silla: ");
        System.out.println(" - 1. Silla Plegable");
        System.out.println(" - 2. Silla de Cocina");
        System.out.println(" - 3. Silla de Oficina");
        System.out.print("Elija el tipo deseado: ");
        return 3;
    }

    private int mostrarMenuTipoSillaOficina() {
        System.out.println("Tipo de silla de oficina: ");
        System.out.println(" - 1. Con ruedas");
        System.out.println(" - 2. Sin ruedas");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    private int mostrarMenuMadera() {
        System.out.println("�Qu� madera quiere para su mueble?");
        return mostrarValores(Madera.values());
    }

    private int mostrarMenuForma() {
        System.out.println("�Qu� forma desea que tenga?");
        return mostrarValores(Forma.values());
    }

    private int mostrarValores(Object[] values) {
        for(int i=0; i<values.length; i++){
            System.out.printf(" - %d. %s\n", i+1, values[i].toString());
        }
        System.out.print("Elija el tipo deseado: ");
        return values.length;
    }

    private void mostrarCabecera() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("-          Gestor de mobiliario v.1.0.           -");
        System.out.println("--------------------------------------------------");
    }

    private void mostrarPie() {
        System.out.println("--------------------------------------------------");
        System.out.print("- Escoja una opci�n: ");
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
