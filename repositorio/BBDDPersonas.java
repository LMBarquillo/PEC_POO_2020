package repositorio;

import entidades.Persona;

import java.util.List;

/**
 * Clase BBDDPersonas. Crea un sistema de almacenamiento para personas
 *
 * @author Luis Miguel Barquillo
 */
public class BBDDPersonas implements BBDD<Persona>
{
    @Override public boolean existe(Persona objeto) {
        return false;
    }

    @Override public Persona obtener(Integer id) {
        return null;
    }

    @Override public List<Persona> listar() {
        return null;
    }

    @Override public Persona insertar(Persona objeto) {
        return null;
    }

    @Override public Persona guardar(Persona objeto) {
        return null;
    }

    @Override public Integer eliminar(Persona objeto) {
        return null;
    }
}
