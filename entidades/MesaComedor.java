package entidades;

import constantes.Madera;
import constantes.Material;

/**
 * Clase MesaComedor. Define una mesa del tipo Comedor
 * @author Luis Miguel Barquillo
 */
public class MesaComedor extends Mesa
{
    private Madera madera;
    private boolean extensible;

    public MesaComedor() {
        // Las mesas de tipo Comedor solo son de madera
        this.setMaterial(Material.MADERA);
    }

    public Madera getMadera() {
        return madera;
    }

    public void setMadera(Madera madera) {
        this.madera = madera;
    }

    public boolean isExtensible() {
        return extensible;
    }

    public void setExtensible(boolean extensible) {
        this.extensible = extensible;
    }

    @Override public String toString() {
        // el super.toString ya nos dice que es una mesa y cuáles son sus medidas.
        return super.toString() + " tipo Comedor, de " + this.getMadera().toString() +
                (this.isExtensible() ? " extensible" : " no extensible");
    }
}
