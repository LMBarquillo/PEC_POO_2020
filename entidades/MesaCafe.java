package entidades;


/**
 * Clase MesaCafe. Define una mesa de tipo Café
 * @author Luis Miguel Barquillo
 */
public class MesaCafe extends Mesa
{
    private boolean revistero;

    public boolean isRevistero() {
        return revistero;
    }

    public void setRevistero(boolean revistero) {
        this.revistero = revistero;
    }

    @Override public String toString() {
        return super.toString() + " tipo Café " +
                (isRevistero() ? " con " : " sin ") + "revistero";
    }
}
