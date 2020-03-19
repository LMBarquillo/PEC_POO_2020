package entidades;

import constantes.Material;

/**
 * Clase SillaPlegable. Describe las sillas de tipo Plegable
 * @author Luis Miguel Barquillo
 */
public class SillaPlegable extends Silla
{
    private Material materialPatas;

    public SillaPlegable(String referencia, Cliente cliente, boolean acolchada, Material materialPatas) {
        // Las sillas plegables sólo las fabricamos en plástico
        super(referencia, Material.PLASTICO, cliente, acolchada);
        this.materialPatas = materialPatas;
    }

    public Material getMaterialPatas() {
        return materialPatas;
    }

    public void setMaterialPatas(Material materialPatas) {
        this.materialPatas = materialPatas;
    }

    @Override public String toString() {
        return super.toString() + " de plástico con patas de " + this.getMaterialPatas().toString();
    }
}
