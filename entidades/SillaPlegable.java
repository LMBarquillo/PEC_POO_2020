package entidades;

import constantes.Color;
import constantes.Material;

/**
 * Clase SillaPlegable. Describe las sillas de tipo Plegable
 * @author Luis Miguel Barquillo
 */
public class SillaPlegable extends Silla
{
    private Color color;

    public SillaPlegable(int numTrabajo, Cliente cliente, boolean acolchada, Color color) {
        // Las sillas plegables solo las fabricamos en pl�stico
        super(numTrabajo, Material.PLASTICO, cliente, acolchada);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override public String toString() {
        return super.toString() + " de pl�stico de color " + this.getColor().toString();
    }
}
