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
public class BBDDMuebles implements BBDD<Mueble>
{
    private Map<String, Mueble> muebles;

    public BBDDMuebles() {
        this.muebles = new HashMap<>();
    }

    public boolean existe(String referencia) {
        return muebles.containsKey(referencia);
    }

    @Override public boolean existe(Mueble mueble) {
        return existe(mueble.getReferencia());
    }

    @Override public Mueble obtener(String referencia) {
        return muebles.get(referencia);
    }

    @Override public List<Mueble> listar() {
        return new ArrayList<>(muebles.values());
    }

    @Override public Mueble insertar(Mueble mueble) {
        if(!existe(mueble.getReferencia())) {
            muebles.put(mueble.getReferencia(), mueble);
            return mueble;
        } else {
            return null;
        }
    }

    @Override public Mueble guardar(String referencia, Mueble mueble) {
        if(existe(referencia)) {
            muebles.put(referencia, mueble);
            return mueble;
        } else {
            return null;
        }
    }

    @Override public Mueble eliminar(String referencia) {
        if(existe(referencia)) {
            Mueble eliminado = muebles.get(referencia);
            muebles.remove(referencia);
            return eliminado;
        } else {
            return null;
        }
    }
}
