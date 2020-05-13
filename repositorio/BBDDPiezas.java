package repositorio;

import entidades.Pieza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase BBDDPiezas. Crea un sistema de almacenamiento para piezas
 *
 * @author Luis Miguel Barquillo
 */
public class BBDDPiezas implements BBDD<String, Pieza>
{
    private final Map<String, Pieza> piezas;

    public BBDDPiezas() {
        this.piezas = new HashMap<>();
    }

    public boolean existe(String referencia) {
        return piezas.containsKey(referencia);
    }

    @Override public boolean existe(Pieza pieza) {
        return existe(pieza.getReferencia());
    }

    @Override public Pieza obtener(String referencia) {
        return piezas.get(referencia);
    }

    public List<Pieza> obtener(List<String> referencias) {
        List<Pieza> piezas = new ArrayList<>();
        for(String r : referencias) {
            if(existe(r)) piezas.add(obtener(r));
        }
        return piezas;
    }

    @Override public List<Pieza> listar() {
        return new ArrayList<>(piezas.values());
    }

    @Override public Pieza insertar(Pieza pieza) {
        if(!existe(pieza.getReferencia())) {
            piezas.put(pieza.getReferencia(), pieza);
            return pieza;
        } else {
            return null;
        }
    }

    @Override public Pieza guardar(String referencia, Pieza pieza) {
        if(existe(referencia)) {
            piezas.put(referencia, pieza);
            return pieza;
        } else {
            return null;
        }
    }

    @Override public Pieza eliminar(String referencia) {
        if(existe(referencia)) {
            Pieza eliminada = piezas.get(referencia);
            piezas.remove(referencia);
            return eliminada;
        } else {
            return null;
        }
    }
}
