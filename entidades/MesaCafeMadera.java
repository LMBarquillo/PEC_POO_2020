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

    public MesaCafeMadera() {
        this.setMaterial(Material.MADERA);
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
