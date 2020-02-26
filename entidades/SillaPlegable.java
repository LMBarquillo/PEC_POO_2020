package entidades;

import constantes.Material;

/**
 * Clase SillaPlegable. Describe las sillas de tipo Plegable
 * @author Luis Miguel Barquillo
 */
public class SillaPlegable extends Silla
{


    public SillaPlegable() {
        this.setMaterial(Material.PLASTICO);
    }

    @Override public String toString() {
        return "Silla Plegable";
    }
}
