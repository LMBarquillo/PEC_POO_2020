package entidades;

/**
 * Clase Silla. Define un objeto silla que hereda de la superclase Mueble
 * @author Luis Miguel Barquillo
 */
public class Silla extends Mueble
{
    private boolean acolchada;

    public boolean isAcolchada() {
        return acolchada;
    }

    public void setAcolchada(boolean acolchada) {
        this.acolchada = acolchada;
    }
}
