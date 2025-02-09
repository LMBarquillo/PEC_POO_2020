package entidades;

import constantes.Material;

/**
 * Clase SillaCocina. Describe las sillas de Cocina
 * @author Luis Miguel Barquillo
 */
public class SillaCocina extends Silla
{
    private boolean respaldo;

    public SillaCocina(int numTrabajo, Material material, Cliente cliente, boolean acolchada, boolean respaldo) {
        super(numTrabajo, material, cliente, acolchada);
        this.respaldo = respaldo;
    }

    public boolean isRespaldo() {
        return respaldo;
    }

    public void setRespaldo(boolean respaldo) {
        this.respaldo = respaldo;
    }

    @Override public String toString() {
        return super.toString() + " de cocina" + (isRespaldo() ? " con " : " sin ") + "respaldo";
    }
}
