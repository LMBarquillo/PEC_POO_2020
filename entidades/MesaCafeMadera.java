package entidades;

import constantes.Madera;
import constantes.Material;

/**
 * Clase MesaCafeMadera. Describe las mesas de tipo Café Madera
 * @Author Luis Miguel Barquillo
 */
public class MesaCafeMadera extends MesaCafe
{
    private Madera madera;

    public MesaCafeMadera(int numTrabajo, Cliente cliente, int ancho, int largo, boolean revistero, Madera madera) {
        super(numTrabajo, Material.MADERA, cliente, ancho, largo, revistero);
        this.madera = madera;
    }

    public Madera getMadera() {
        return madera;
    }

    public void setMadera(Madera madera) {
        this.madera = madera;
    }

    @Override public String toString() {
        return super.toString() + " de madera de " + madera.toString();
    }
}
