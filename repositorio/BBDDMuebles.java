package repositorio;

import entidades.Mueble;

import java.util.List;

/**
 * Clase BBDDMuebles. Crea un sistema de almacenamiento para muebles
 * 
 * @author Luis Miguel Barquillo
 */
public class BBDDMuebles implements BBDD<Mueble>
{
    @Override public boolean existe(Mueble objeto) {
        return false;
    }

    @Override public Mueble obtener(Integer id) {
        return null;
    }

    @Override public List<Mueble> listar() {
        return null;
    }

    @Override public Mueble insertar(Mueble objeto) {
        return null;
    }

    @Override public Mueble guardar(Mueble objeto) {
        return null;
    }

    @Override public Integer eliminar(Mueble objeto) {
        return null;
    }
}
