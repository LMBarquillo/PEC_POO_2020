package constantes;


/**
 * Clase enumeradora Categor�a. Representa la categor�a laboral de los empleados.
 */
public enum Categoria
{
    JEFE("Jefe"),
    COMERCIAL("Comercial"),
    ARTESANO("Artesano");

    private final String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    @Override public String toString() {
        return this.categoria;
    }
}
