package entidades;

import constantes.Forma;
import constantes.Madera;
import constantes.Material;

/**
 * Clase MesaCafeMadera. Describe las mesas de tipo Café Madera
 * @Author Luis Miguel Barquillo
 */
public class MesaCafeMadera extends MesaCafe
{
    private Forma forma;
    private Madera madera;

    public MesaCafeMadera(String referencia, Cliente cliente, int ancho, int largo, boolean revistero, Forma forma, Madera madera) {
        super(referencia, Material.MADERA, cliente, ancho, largo, revistero);
        this.forma = forma;
        this.madera = madera;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
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
