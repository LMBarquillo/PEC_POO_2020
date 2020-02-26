package entidades;

import constantes.Madera;
import constantes.Material;
import excepciones.MaterialNoPermitidoException;

/**
 * Clase MesaDormitorio. Define una mesa de tipo Dormitorio
 * @author Luis Miguel Barquillo
 */
public class MesaDormitorio extends Mesa
{
    private Madera madera;
    private int cajones;

    public MesaDormitorio() throws MaterialNoPermitidoException {
        // Las mesas de Dormitorio solo las fabricamos de madera.
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
