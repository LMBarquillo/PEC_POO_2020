package entidades;

import constantes.Material;

/**
 * Clase MesaCafe. Define una mesa de tipo Café
 * @author Luis Miguel Barquillo
 */
public class MesaCafe extends Mesa
{
    private boolean revistero;

    public MesaCafe(int numTrabajo, Material material, Cliente cliente, int ancho, int largo, boolean revistero) {
        super(numTrabajo, material, cliente, ancho, largo);
        this.revistero = revistero;
    }

    public boolean isRevistero() {
        return revistero;
    }

    public void setRevistero(boolean revistero) {
        this.revistero = revistero;
    }

    @Override public String toString() {
        return super.toString() + " tipo Café, " +
                (isRevistero() ? " con " : " sin ") + "revistero ";
    }
}
