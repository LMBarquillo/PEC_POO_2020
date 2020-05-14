package interfaz;

import constantes.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase Menu. Muestra menús con opciones en consola y devuelve el resultado.
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
     * Lee una opción numérica del teclado, desde 1 hasta max
     * @param max Máximo valor aceptado
     * @return Valor leído
     */
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

    /**
     * Detecta si una string es número y está dentro del rango [1,max]
     * @param s cadena de entrada
     * @param max Máximo valor admitido
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
     * Muestra menú principal
     * @return número de opciones
     */
    private int mostrarMenuPrincipal() {
        mostrarCabecera();
        System.out.println("- 1. Gestión de muebles                          -");
        System.out.println("- 2. Gestión de clientes                         -");
        System.out.println("- 3. Gestión de empleados                        -");
        System.out.println("- 4. Salir del programa                          -");
        mostrarPie();
        return 4;
    }

    /**
     * Muestra menú principal de muebles
     * @return número de opciones
     */
    private int mostrarMenuPrincipalMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Acciones de jefatura                        -");
        System.out.println("- 2. Acciones de fabricación                     -");
        System.out.println("- 3. Volver al menú anterior                     -");
        mostrarPie();
        return 3;
    }

    /**
     * Muestra menú de jefatura para gestión de muebles
     * @return número de opciones
     */
    private int mostrarMenuJefeMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Recepcionar un pedido                       -");
        System.out.println("- 2. Asignar pedido a un artesano                -");
        System.out.println("- 3. Inspeccionar progreso de un pedido          -");
        System.out.println("- 4. Mostrar las fichas de un artesano           -");
        System.out.println("- 5. Volver al menú anterior                     -");
        mostrarPie();
        return 5;
    }

    /**
     * Muestra menú de artesanos para gestión de muebles
     * @return número de opciones
     */
    private int mostrarMenuArtesanoMuebles() {
        mostrarCabecera();
        System.out.println("- 1. Ver mis trabajos en progreso                -");
        System.out.println("- 2. Cambiar estado de un trabajo                -");
        System.out.println("- 3. Añadir anotación a un trabajo               -");
        System.out.println("- 4. Listado de piezas disponibles               -");
        System.out.println("- 5. Volver al menú anterior                     -");
        mostrarPie();
        return 5;
    }

    /**
     * Muestra menú de gestión de empleados
     * @return número de opciones
     */
    private int mostrarMenuGestionEmpleados() {
        mostrarCabecera();
        System.out.println("- 1. Alta de nuevo empleado                      -");
        System.out.println("- 2. Baja de empleado                            -");
        System.out.println("- 3. Modificar un empleado                       -");
        System.out.println("- 4. Listado de empleados                        -");
        System.out.println("- 5. Volver al menú anterior                     -");
        mostrarPie();
        return 5;
    }

    /**
     * Muestra menú de gestión de clientes
     * @return número de opciones
     */
    private int mostrarMenuGestionClientes() {
        mostrarCabecera();
        System.out.println("- 1. Alta de nuevo cliente                       -");
        System.out.println("- 2. Baja de cliente                             -");
        System.out.println("- 3. Modificar un cliente                        -");
        System.out.println("- 4. Listado de clientes                         -");
        System.out.println("- 5. Comunicación de precios de pedidos          -");
        System.out.println("- 6. Listar pedidos finalizados                  -");
        System.out.println("- 7. Avisar de pedido listo para entrega         -");
        System.out.println("- 8. Volver al menú anterior                     -");
        mostrarPie();
        return 8;
    }

    /**
     * Muestra menú de selección de tipo de mueble
     * @return número de opciones
     */
    private int mostrarMenuTipoMueble() {
        System.out.println("Tipo de mueble: ");
        System.out.println(" - 1. Mesa");
        System.out.println(" - 2. Silla");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    /**
     * Muestra menú de selección de tipo de mesa
     * @return número de opciones
     */
    private int mostrarMenuTipoMesa() {
        System.out.println("Tipo de mesa: ");
        System.out.println(" - 1. Mesa de Comedor");
        System.out.println(" - 2. Mesa de Café");
        System.out.println(" - 3. Mesa de Dormitorio");
        System.out.print("Elija el tipo deseado: ");
        return 3;
    }

    /**
     * Muestra menú de selección de tipo de mesa de café
     * @return número de opciones
     */
    private int mostrarMenuTipoMesaCafe() {
        System.out.println("Tipo de mesa de café: ");
        System.out.println(" - 1. Mesa de Café de Madera");
        System.out.println(" - 2. Mesa de Café de Cristal");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    /**
     * Muestra menú de selección de tipo de silla
     * @return número de opciones
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
     * Muestra menú de selección de tipo de silla de oficina
     * @return número de opciones
     */
    private int mostrarMenuTipoSillaOficina() {
        System.out.println("Tipo de silla de oficina: ");
        System.out.println(" - 1. Con ruedas");
        System.out.println(" - 2. Sin ruedas");
        System.out.print("Elija el tipo deseado: ");
        return 2;
    }

    /**
     * Muestra menú de selección de tipo de empleado
     * @return número de opciones
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
     * Muestra menú de selección de tipos de madera
     * @return número de opciones
     */
    private int mostrarMenuMadera() {
        System.out.println("¿Qué tipo de madera desea?");
        return mostrarValores(Madera.values());
    }

    /**
     * Muestra menú de selección de formas
     * @return número de opciones
     */
    private int mostrarMenuForma() {
        System.out.println("¿Qué forma desea que tenga?");
        return mostrarValores(Forma.values());
    }

    /**
     * Muestra menú de selección de colores
     * @return número de opciones
     */
    private int mostrarMenuColor() {
        System.out.println("¿Qué color desea?");
        return mostrarValores(Color.values());
    }

    /**
     * Muestra menú de selección de material
     * @return número de opciones
     */
    private int mostrarMenuMaterial() {
        System.out.println("¿Qué tipo de material desea?");
        return mostrarValores(Material.values());
    }

    /**
     * Muestra menú de selección de estados
     * @return número de opciones
     */
    private int mostrarMenuEstado() {
        System.out.println("¿Qué estado desea ponerle?");
        return mostrarValores(Estado.values());
    }

    /**
     * Muestra menú de selección genérico a partir de una colección de valores
     * generalmente obtenidos a partir de los tipos enumeradores
     * @return número de opciones
     */
    private int mostrarMenuListado(String encabezado, Object[] valores) {
        System.out.println(encabezado);
        return mostrarValores(valores);
    }

    /**
     * Muestra los valores de un array y solicita elección
     * @param values valores a mostrar
     * @return cantidad de valores mostrados
     */
    public int mostrarValores(Object[] values) {
        for(int i=0; i<values.length; i++){
            System.out.printf(" - %d. %s\n", i+1, values[i].toString());
        }
        System.out.print("Seleccione una opción: ");
        return values.length;
    }

    /**
     * Encabezado de los menús
     */
    private void mostrarCabecera() {
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("-          Gestor de mobiliario v.1.0.           -");
        System.out.println("--------------------------------------------------");
    }

    /**
     * Pie de los menús
     */
    private void mostrarPie() {
        System.out.println("--------------------------------------------------");
        System.out.print("- Escoja una opción: ");
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
