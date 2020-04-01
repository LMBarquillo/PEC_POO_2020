package entidades;

import constantes.Material;

/**
 * Clase MesaCafeCristal. Define una mesa de tipo Café de Cristal
 * @author Luis Miguel Barquillo
 */
public class MesaCafeCristal extends MesaCafe
{
    public boolean labrado;

    public MesaCafeCristal(int numTrabajo, Cliente cliente, int ancho, int largo, boolean revistero, boolean labrado) {
        super(numTrabajo, Material.CRISTAL, cliente, ancho, largo, revistero);
        this.labrado = labrado;
    }

    public boolean isLabrado() {
        return labrado;
    }

    public void setLabrado(boolean labrado) {
        this.labrado = labrado;
    }

    @Override public String toString() {
        return super.toString() + " de cristal " + (labrado ? "labrado" : "sencillo");
    }
}
