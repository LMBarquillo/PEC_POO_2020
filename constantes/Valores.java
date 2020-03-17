package constantes;


/**
 * Interfaz Valores. Define una serie de constantes para valores fijos
 * 
 * @author Luis Miguel Barquillo Romero
 */
public interface Valores
{
    /**
     * Definición de los valores del menú principal
     */
    interface Principal {
        int MUEBLES = 1;
        int CLIENTES = 2;
        int EMPLEADOS = 3;
        int SALIR = 4;
    }

    /**
     * Definición de los valores del menú principal de gestión de muebles
     */
    interface PrincipalMuebles {
        int JEFE = 1;
        int ARTESANOS = 2;
        int VOLVER = 3;
    }

    /**
     * Definición de los valores del menú de gestión de muebles para uso del jefe
     */
    interface JefeMuebles {
        int RECEPCIONAR = 1;
        int ASIGNAR = 2;
        int INSPECCIONAR = 3;
        int VOLVER = 4;
    }

    /**
     * Definición de los valores del menú de gestión de muebles para uso de los artesanos
     */
    interface ArtesanoMuebles {
        int VER_TRABAJOS = 1;
        int CAMBIAR_ESTADO = 2;
        int VOLVER = 3;
    }

    /**
     * Definición de los valores del menú de gestión de usuarios
     */
    interface GestionUsuarios {
        int ALTA = 1;
        int BAJA = 2;
        int MODIFICACION = 3;
        int VOLVER = 4;
    }

    /**
     * Definición de los valores del menú de gestión de clientes
     */
    interface GestionClientes {
        int ALTA = 1;
        int BAJA = 2;
        int MODIFICACION = 3;
        int COMUNICAR_PRECIO = 4;
        int AVISAR_ENTREGA = 5;
        int VOLVER = 6;
    }
}
