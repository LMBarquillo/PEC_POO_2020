package entidades;


/**
 * Clase Pieza. Define las piezas necesarias para crear muebles
 * @author Luis Miguel Barquillo
 */
public class Pieza
{
    private String referencia;
    private String descripcion;

    public Pieza(String referencia, String descripcion) {
        this.referencia = referencia;
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override public String toString() {
        return "(" + referencia + ") - " + descripcion;

    }
}
