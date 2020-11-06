package constantes;


/**
 * Clase enumeradora condicion. Define las condiciones de los artesanos
 */
public enum Condicion
{
    POR_HORAS("Por horas"),
    EN_PLANTILLA("En plantilla");

    private final String condicion;

    Condicion(String condicion) {
        this.condicion = condicion;
    }

    @Override public String toString() {
        return this.condicion;
    }
}
