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

    public MesaComedor(int numTrabajo, Cliente cliente, int ancho, int largo, Madera madera, boolean extensible) {
        // Las mesas de comedor solo las fabricamos de Madera
        super(numTrabajo, Material.MADERA, cliente, ancho, largo);
        this.madera = madera;
        this.extensible = extensible;
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
