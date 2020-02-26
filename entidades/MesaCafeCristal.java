package entidades;


import constantes.Material;

/**
 * Clase MesaCafeCristal. Define una mesa de tipo Café de Cristal
 * @author Luis Miguel Barquillo
 */
public class MesaCafeCristal extends MesaCafe
{
    public boolean labrado;

    public MesaCafeCristal() {
        this.setMaterial(Material.CRISTAL);
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
