package entidades;

import constantes.Material;

/**
 * Clase SillaOficina. Describe las sillas de tipo Oficina
 * @author Luis Miguel Barquillo
 */
public class SillaOficina extends Silla
{
    private boolean reclinable;

    public SillaOficina(int numTrabajo, Material material, Cliente cliente, boolean acolchada, boolean reclinable) {
        super(numTrabajo, material, cliente, acolchada);
        this.reclinable = reclinable;
    }

    public boolean isReclinable() {
        return reclinable;
    }

    public void setReclinable(boolean reclinable) {
        this.reclinable = reclinable;
    }

    @Override public String toString() {
        return super.toString() + (isReclinable() ? " reclinable " : " estática ");
    }
}
