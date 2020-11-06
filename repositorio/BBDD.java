package repositorio;

import java.util.List;

/**
 * Interfaz BBDD. Define los métodos que debe tener una clase para almacenar objetos.
 * 
 * @author Luis Miguel Barquillo
 */

public interface BBDD<T, E>
{
    /**
     * Comprueba si existe un objeto
     * @param objeto a comprobar
     * @return true or false
     */
    boolean existe(E objeto);

    /**
     * Obtiene un objeto a partir de su ID
     * @param id del objeto
     * @return objeto
     */
    E obtener(T id);

    /**
     * Devuelve una lista de objetos de la BBDD
     * @return
     */
    List<E> listar();

    /**
     * Inserta un objeto en la BBDD
     * @param objeto
     * @return objeto insertado
     */
    E insertar(E objeto);

    /**
     * Modifica un objeto en la BBDD
     * @param id del objeto que queremos modificar
     * @param objeto nuevo
     * @return objeto modificado
     */
    E guardar(T id, E objeto);

    /**
     * Elimina un objeto de la BBDD
     * @param id del objeto
     * @return objeto eliminado
     */
    E eliminar(T id);
}
