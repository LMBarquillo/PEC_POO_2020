package entidades;

import constantes.Material;
import excepciones.MaterialNoPermitidoException;

/**
 * Clase SillaPlegable. Describe las sillas de tipo Plegable
 * @author Luis Miguel Barquillo
 */
public class SillaPlegable extends Silla
{
    private Material materialPatas;

    public SillaPlegable() throws MaterialNoPermitidoException {
        this.setMaterial(Material.PLASTICO);
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
