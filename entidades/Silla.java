package entidades;

import constantes.Material;

/**
 * Clase Silla. Define un objeto silla que hereda de la superclase Mueble
 * @author Luis Miguel Barquillo
 */
public class Silla extends Mueble
{
    private boolean acolchada;

    public Silla(int numTrabajo, Material material, Cliente cliente, boolean acolchada) {
        super(numTrabajo, material, cliente);
        this.acolchada = acolchada;
    }

    public boolean isAcolchada() {
        return acolchada;
    }

    public void setAcolchada(boolean acolchada) {
        this.acolchada = acolchada;
    }

    @Override public String toString() {
        return "Silla " + (isAcolchada() ? "acolchada" : "rígida");
    }
}
