package entidades;

import constantes.Forma;
import constantes.Material;

/**
 * Clase MesaCafeMadera. Describe las mesas de tipo Café Madera
 * @Author Luis Miguel Barquillo
 */
public class MesaCafeMadera extends MesaCafe
{
    private Forma forma;

    public MesaCafeMadera() {
        this.setMaterial(Material.MADERA);
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    @Override public String toString() {
        return "Mesa de Café de Madera " + forma.toString();
    }
}
