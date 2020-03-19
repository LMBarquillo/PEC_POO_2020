package repositorio;

import java.util.List;

/**
 * Interfaz BBDD. Define los métodos que debe tener una clase para almacenar objetos.
 * 
 * @author Luis Miguel Barquillo
 */

public interface BBDD<E>
{
    boolean existe(E objeto);
    E obtener(String id);
    List<E> listar();
    E insertar(E objeto);
    E guardar(String id, E objeto);
    E eliminar(String id);
}
