package entidades;

import constantes.Madera;
import constantes.Material;

/**
 * Clase MesaDormitorio. Define una mesa de tipo Dormitorio
 * @author Luis Miguel Barquillo
 */
public class MesaDormitorio extends Mesa
{
    private Madera madera;
    private int cajones;

    public MesaDormitorio() {
        // Las mesas de Dormitorio solo son de madera.
        this.setMaterial(Material.MADERA);
    }

    public Madera getMadera() {
        return madera;
    }

    public void setMadera(Madera madera) {
        this.madera = madera;
    }

    public int getCajones() {
        return cajones;
    }

    public void setCajones(int cajones) {
        this.cajones = cajones;
    }

    @Override public String toString() {
        return super.toString() + " tipo Dormitorio, de " + this.getMadera().toString() +
                " con " + this.getCajones() + " cajones";
    }
}
