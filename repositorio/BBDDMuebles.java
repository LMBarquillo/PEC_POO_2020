package repositorio;

import entidades.Mueble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase BBDDMuebles. Crea un sistema de almacenamiento para muebles
 * 
 * @author Luis Miguel Barquillo
 */
public class BBDDMuebles implements BBDD<Integer, Mueble>
{
    private Map<Integer, Mueble> muebles;

    public BBDDMuebles() {
        this.muebles = new HashMap<>();
    }

    public boolean existe(Integer numTrabajo) {
        return muebles.containsKey(numTrabajo);
    }

    @Override public boolean existe(Mueble mueble) {
        return existe(mueble.getNumTrabajo());
    }

    @Override public Mueble obtener(Integer numTrabajo) {
        return muebles.get(numTrabajo);
    }

    @Override public List<Mueble> listar() {
        return new ArrayList<>(muebles.values());
    }

    @Override public Mueble insertar(Mueble mueble) {
        if(!existe(mueble.getNumTrabajo())) {
            muebles.put(mueble.getNumTrabajo(), mueble);
            return mueble;
        } else {
            return null;
        }
    }

    @Override public Mueble guardar(Integer numTrabajo, Mueble mueble) {
        if(existe(numTrabajo)) {
            muebles.put(numTrabajo, mueble);
            return mueble;
        } else {
            return null;
        }
    }

    @Override public Mueble eliminar(Integer numTrabajo) {
        if(existe(numTrabajo)) {
            Mueble eliminado = muebles.get(numTrabajo);
            muebles.remove(numTrabajo);
            return eliminado;
        } else {
            return null;
        }
    }

    /**
     * Obtiene el máximo número de trabajo y le suma 1
     * @return número para nuevo trabajo
     */
    public int nuevoNumeroTrabajo() {
        int max = 0;
        for(Integer i : muebles.keySet()) {
            if(i > max) max = i;
        }
        return max+1;
    }

    /*
     * Demostración del tema 5 del libro.
     * Este método es igual que el anterior, pero usando streams y lambdas.
     * Nótese que se reduce considerablemente la posiblidad de error.
     */
    public int nuevoNumeroTrabajoStream() {
        return muebles.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }
}
