package entidades;

import constantes.Material;

/**
 * Clase SillaOficinaConRuedas. Describe las sillas oficinas con ruedas
 * @author Luis Miguel Barquillo
 */
public class SillaOficinaConRuedas extends SillaOficina
{
    private int numRuedas;

    public SillaOficinaConRuedas(int numTrabajo, Cliente cliente, boolean acolchada, boolean reclinable, int numRuedas) {
        // Las sillas de Oficina solo las hacemos metálicas.
        super(numTrabajo, Material.METAL, cliente, acolchada, reclinable);
        this.numRuedas = numRuedas;
    }

    public int getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(int numRuedas) {
        this.numRuedas = numRuedas;
    }

    @Override public String toString() {
        return super.toString() + " con " + this.getNumRuedas() + " ruedas";
    }
}
