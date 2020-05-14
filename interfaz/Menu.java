package interfaz;

import constantes.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase Menu. Muestra men�s con opciones en consola y devuelve el resultado.
 * 
 * @author Luis Miguel Barquillo
 */
public class Menu
{
    private final BufferedReader br;

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

    public int menuGestionEmpleados() {
        return leerOpcion(mostrarMenuGestionEmpleados());
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

    public int menuTipoEmpleado() {
        return leerOpcion(mostrarMenuTipoEmpleado());
    }

    public int menuMadera() {
        return leerOpcion(mostrarMenuMadera());
    }

    public int menuForma() {
        return leerOpcion(mostrarMenuForma());
    }

    public int menuColor() {
        return leerOpcion(mostrarMenuColor());
    }

    public int menuMaterial() {
        return leerOpcion(mostrarMenuMaterial());
    }

    public int menuEstado() {
        return leerOpcion(mostrarMenuEstado());
    }

    public int menuListado(String encabezado, Object[] listado) {
        return leerOpcion(mostrarMenuListado(encabezado, listado));
    }

    /**
     * Lee una opci�n num�rica del teclado, desde 1 hasta max
     * @param max M�ximo valor aceptado
     * @return Valor le�do
     */
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

    /**
     * Detecta si una string es n�mero y est� dentro del rango [1,max]
     * @param s cadena de entrada
     * @param max M�ximo valor admitido
     * @return si es incorrecto
     */
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

    /**
     * Muestra men� principal
     * @return n�mero de opciones
     */
    private int mostrarMenuPrincipal() {
        mostrarCabecera();
        System.out.println("- 1. Gesti�n de muebles                          -");
        System.out.println("- 2. Gesti�n de clientes                         -");
        System.out.println("- 3. Gesti�n de empleados                        -");
        System.out.println("- 4. Salir del programa                          -");
        mostrarPie();
        return 4;
    }

    /**
     * Muestra men� principal de muebles
     * @return n�mero de opciones
     */
    private int mostrarMenuPrincipalMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Acciones de jefatura                        -");
        System.out.println("- 2. Acciones de fabricaci�n                     -");
        System.out.println("- 3. Volver al men� anterior                     -");
        mostrarPie();
        return 3;
    }

    /**
     * Muestra men� de jefatura para gesti�n de muebles
     * @return n�mero de opciones
     */
    private int mostrarMenuJefeMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Recepcionar un pedido                       -");
        System.out.println("- 2. Asignar pedido a un artesano                -");
        System.out.println("- 3. Inspeccionar progreso de un pedido          -");
        System.out.println("- 4. Mostrar las fichas de un artesano           -");
        System.out.println("- 5. Volver al men� anterior                     -");
        mostrarPie();
        return 5;
    }

    /**
     * Muestra men� de artesanos para gesti�n de muebles
     * @return n�mero de opciones
     */
    private int mostrarMenuArtesanoMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Ver mis trabajos en progreso                -");
        System.out.println("- 2. Cambiar estado de un trabajo                -");
        System.out.println("- 3. A�adir anotaci�n a un trabajo               -");
        System.out.println("- 4. Listado de piezas disponibles               -");
        System.out.println("- 5. Volver al men� anterior                     -");
        mostrarPie();
        return 5;
    }

    /**
     * Muestra men� de gesti�n de empleados
     * @return n�mero de opciones
     */
    private int mostrarMenuGestionEmpleados() {
        mostrarCabecera();
        System.out.println("- 1. Alta de nuevo empleado                      -");
        System.out.println("- 2. Baja de empleado                            -");
        System.out.println("- 3. Modificar un empleado                       -");
        System.out.println("- 4. Listado de empleados                        -");
        System.out.println("- 5. Volver al men� anterior                     -");
        mostrarPie();
        return 5;
    }

    /**
     * Muestra men� de gesti�n de clientes
     * @return n�mero de opciones
     */
    private int mostrarMenuGestionClientes() {
        mostrarCabecera();
        System.out.println("- 1. Alta de nuevo cliente                       -");
        System.out.println("- 2. Baja de cliente                             -");
        System.out.println("- 3. Modificar un cliente                        -");
        System.out.println("- 4. Listado de clientes                         -");
        System.out.println("- 5. Comunicaci�n de precios de pedidos          -");
        System.out.println("- 6. Listar pedidos finalizados                  -");
        System.out.println("- 7. Avisar de pedido listo para entrega         -");
        System.out.println("- 8. Volver al men� anterior                     -");
        mostrarPie();
        return 8;
    }

    /**
     * Muestra men� de selecci�n de tipo de mueble
     * @return n�mero de opciones
     */
    private int mostrarMenuTipoMueble() {
        System.out.println("Tipo de mueble: ");
        System.out.println(" - 1. Mesa");
        System.out.println(" - 2. Silla");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    /**
     * Muestra men� de selecci�n de tipo de mesa
     * @return n�mero de opciones
     */
    private int mostrarMenuTipoMesa() {
        System.out.println("Tipo de mesa: ");
        System.out.println(" - 1. Mesa de Comedor");
        System.out.println(" - 2. Mesa de Caf�");
        System.out.println(" - 3. Mesa de Dormitorio");
        System.out.print("Elija el tipo deseado: ");
        return 3;
    }

    /**
     * Muestra men� de selecci�n de tipo de mesa de caf�
     * @return n�mero de opciones
     */
    private int mostrarMenuTipoMesaCafe() {
        System.out.println("Tipo de mesa de caf�: ");
        System.out.println(" - 1. Mesa de Caf� de Madera");
        System.out.println(" - 2. Mesa de Caf� de Cristal");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    /**
     * Muestra men� de selecci�n de tipo de silla
     * @return n�mero de opciones
     */
    private int mostrarMenuTipoSilla() {
        System.out.println("Tipo de silla: ");
        System.out.println(" - 1. Silla Plegable");
        System.out.println(" - 2. Silla de Cocina");
        System.out.println(" - 3. Silla de Oficina");
        System.out.print("Elija el tipo deseado: ");
        return 3;
    }

    /**
     * Muestra men� de selecci�n de tipo de silla de oficina
     * @return n�mero de opciones
     */
    private int mostrarMenuTipoSillaOficina() {
        System.out.println("Tipo de silla de oficina: ");
        System.out.println(" - 1. Con ruedas");
        System.out.println(" - 2. Sin ruedas");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    /**
     * Muestra men� de selecci�n de tipo de empleado
     * @return n�mero de opciones
     */
    private int mostrarMenuTipoEmpleado() {
        System.out.println("Tipo de empleado: ");
        System.out.println(" - 1. Jefe");
        System.out.println(" - 2. Comercial");
        System.out.println(" - 3. Artesano");
        System.out.print("Elija el tipo deseado: ");
        return 3;
    }

    /**
     * Muestra men� de selecci�n de tipos de madera
     * @return n�mero de opciones
     */
    private int mostrarMenuMadera() {
        System.out.println("�Qu� tipo de madera desea?");
        return mostrarValores(Madera.values());
    }

    /**
     * Muestra men� de selecci�n de formas
     * @return n�mero de opciones
     */
    private int mostrarMenuForma() {
        System.out.println("�Qu� forma desea que tenga?");
        return mostrarValores(Forma.values());
    }

    /**
     * Muestra men� de selecci�n de colores
     * @return n�mero de opciones
     */
    private int mostrarMenuColor() {
        System.out.println("�Qu� color desea?");
        return mostrarValores(Color.values());
    }

    /**
     * Muestra men� de selecci�n de material
     * @return n�mero de opciones
     */
    private int mostrarMenuMaterial() {
        System.out.println("�Qu� tipo de material desea?");
        return mostrarValores(Material.values());
    }

    /**
     * Muestra men� de selecci�n de estados
     * @return n�mero de opciones
     */
    private int mostrarMenuEstado() {
        System.out.println("�Qu� estado desea ponerle?");
        return mostrarValores(Estado.values());
    }

    /**
     * Muestra men� de selecci�n gen�rico a partir de una colecci�n de valores
     * generalmente obtenidos a partir de los tipos enumeradores
     * @return n�mero de opciones
     */
    private int mostrarMenuListado(String encabezado, Object[] valores) {
        System.out.println(encabezado);
        return mostrarValores(valores);
    }

    /**
     * Muestra los valores de un array y solicita elecci�n
     * @param values valores a mostrar
     * @return cantidad de valores mostrados
     */
    public int mostrarValores(Object[] values) {
        for(int i=0; i<values.length; i++){
            System.out.printf(" - %d. %s\n", i+1, values[i].toString());
        }
        System.out.print("Seleccione una opci�n: ");
        return values.length;
    }

    /**
     * Encabezado de los men�s
     */
    private void mostrarCabecera() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("-          Gestor de mobiliario v.1.0.           -");
        System.out.println("--------------------------------------------------");
    }

    /**
     * Pie de los men�s
     */
    private void mostrarPie() {
        System.out.println("--------------------------------------------------");
        System.out.print("- Escoja una opci�n: ");
    }

    /**
     * Despedida. Se usa para cerrar el BufferedReader de lectura de teclado.
     */
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
