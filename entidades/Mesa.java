package entidades;

import constantes.Material;
import excepciones.MaterialNoPermitidoException;

/**
 * Clase Mesa. Define una mesa genérica.
 * @author Luis Miguel Barquillo
 */
public class Mesa extends Mueble
{
    private int ancho;
    private int largo;

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    @Override protected void setMaterial(Material material) throws MaterialNoPermitidoException {
        // No hacemos mesas de plástico, tenemos una reputación que mantener.
        if(material == Material.PLASTICO) {
            throw new MaterialNoPermitidoException();
        }
        super.setMaterial(material);
    }

    @Override public String toString() {
        return "Mesa " + this.getAncho() + "x" + this.getLargo();
    }
}
