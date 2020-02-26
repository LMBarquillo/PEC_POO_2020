package entidades;


/**
 * Clase SillaOficinaConRuedas. Describe las sillas oficinas con ruedas
 * @author Luis Miguel Barquillo
 */
public class SillaOficinaConRuedas extends SillaOficina
{
    private int numRuedas;

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
