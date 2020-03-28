package repositorio;

import entidades.Persona;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase BBDDPersonas. Crea un sistema de almacenamiento para personas
 *
 * @author Luis Miguel Barquillo
 */
public class BBDDPersonas implements BBDD<Persona> {
    private Map<String, Persona> personas;

    public BBDDPersonas() {
        this.personas = new HashMap<>();
    }

    public boolean existe(String nif) {
        return personas.containsKey(nif);
    }

    @Override public boolean existe(Persona persona) {
        return existe(persona.getNif());
    }

    @Override public Persona obtener(String nif) {
        return personas.get(nif);
    }

    @Override public List<Persona> listar() {
        return new ArrayList<>(personas.values());
    }

    @Override public Persona insertar(Persona persona) {
        if(!existe(persona)) {
            personas.put(persona.getNif(), persona);
            return persona;
        } else {
            return null;
        }
    }

    @Override public Persona guardar(String nif, Persona persona) {
        if(existe(nif)) {
            personas.put(nif, persona);
            return persona;
        } else {
            return null;
        }
    }

    @Override public Persona eliminar(String nif) {
        if(existe(nif)) {
            Persona eliminada = personas.get(nif);
            personas.remove(nif);
            return eliminada;
        } else {
            return null;
        }
    }
}
