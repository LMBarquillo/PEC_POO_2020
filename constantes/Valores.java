package constantes;


/**
 * Interfaz Valores. Define una serie de constantes para valores fijos
 * 
 * @author Luis Miguel Barquillo Romero
 */
public interface Valores
{
    /**
     * Definici�n de los valores del men� principal
     */
    interface Principal {
        int MUEBLES = 1;
        int CLIENTES = 2;
        int EMPLEADOS = 3;
        int SALIR = 4;
    }

    /**
     * Definici�n de los valores del men� principal de gesti�n de muebles
     */
    interface PrincipalMuebles {
        int JEFE = 1;
        int ARTESANOS = 2;
        int VOLVER = 3;
    }

    /**
     * Definici�n de los valores del men� de gesti�n de muebles para uso del jefe
     */
    interface JefeMuebles {
        int RECEPCIONAR = 1;
        int ASIGNAR = 2;
        int INSPECCIONAR = 3;
        int VOLVER = 4;
    }

    /**
     * Definici�n de los valores del men� de gesti�n de muebles para uso de los artesanos
     */
    interface ArtesanoMuebles {
        int VER_TRABAJOS = 1;
        int CAMBIAR_ESTADO = 2;
        int VOLVER = 3;
    }

    /**
     * Definici�n de los valores del men� de gesti�n de usuarios
     */
    interface GestionUsuarios {
        int ALTA = 1;
        int BAJA = 2;
        int MODIFICACION = 3;
        int VOLVER = 4;
    }

    /**
     * Definici�n de los valores del men� de gesti�n de clientes
     */
    interface GestionClientes {
        int ALTA = 1;
        int BAJA = 2;
        int MODIFICACION = 3;
        int COMUNICAR_PRECIO = 4;
        int AVISAR_ENTREGA = 5;
        int VOLVER = 6;
    }

    interface TipoMuebles {
        int MESA = 1;
        int SILLA = 2;
    }

    interface TipoMesa {
        int COMEDOR = 1;
        int CAFE = 2;
        int DORMITORIO = 3;
    }

    interface TipoMesaCafe {
        int MADERA = 1;
        int CRISTAL = 2;
    }

    interface TipoSilla {
        int PLEGABLE = 1;
        int COCINA = 2;
        int OFICINA = 3;
    }

    interface TipoSillaOficina {
        int CON_RUEDAS = 1;
        int SIN_RUEDAS = 2;
    }
}