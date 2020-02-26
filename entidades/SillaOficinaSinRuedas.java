package entidades;


/**
 * Clase SillaOficinaSinRuedas. Describe las sillas de oficina estáticas
 * @author Luis Miguel Barquillo
 */
public class SillaOficinaSinRuedas extends SillaOficina
{
    private boolean antideslizante;

    public boolean isAntideslizante() {
        return antideslizante;
    }

    public void setAntideslizante(boolean antideslizante) {
        this.antideslizante = antideslizante;
    }

    @Override public String toString() {
        return super.toString() + (isAntideslizante() ? " antideslizante" : "");
    }
}
