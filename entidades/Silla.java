package entidades;

import constantes.Material;
import excepciones.MaterialNoPermitidoException;

/**
 * Clase Silla. Define un objeto silla que hereda de la superclase Mueble
 * @author Luis Miguel Barquillo
 */
public class Silla extends Mueble
{
    private boolean acolchada;

    public boolean isAcolchada() {
        return acolchada;
    }

    public void setAcolchada(boolean acolchada) {
        this.acolchada = acolchada;
    }

    @Override protected void setMaterial(Material material) throws MaterialNoPermitidoException {
        // Una silla de cristal es potencialmente peligrosa. Evitaremos que se fabrique.
        if(material == Material.CRISTAL) {
            throw new MaterialNoPermitidoException();
        }
        super.setMaterial(material);
    }

    @Override public String toString() {
        return "Silla " + (isAcolchada() ? "acolchada" : "rígida");
    }
}
