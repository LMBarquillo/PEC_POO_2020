package entidades;

import constantes.Material;

/**
 * Clase SillaPlegable. Describe las sillas de tipo Plegable
 * @author Luis Miguel Barquillo
 */
public class SillaPlegable extends Silla
{
    public Material materialPatas;

    public SillaPlegable() {
        this.setMaterial(Material.PLASTICO);
    }

    public Material getMaterialPatas() {
        return materialPatas;
    }

    public void setMaterialPatas(Material materialPatas) {
        this.materialPatas = materialPatas;
    }

    @Override public String toString() {
        return "Silla Plegable de " + this.getMaterial().toString() + " con patas de " + this.getMaterialPatas().toString();
    }
}
