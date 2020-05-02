package constantes;

/**
 * Clase enumeradora Color. Representa los distintos colores para algunos muebles.
 */
public enum Color
{
    ROJO("Rojo"),
    AZUL("Azul"),
    VERDE("Verde"),
    NEGRO("Negro"),
    BLANCO("Blanco"),
    AMARILLO("Amarillo"),
    MARRON("Marrón"),
    VIOLETA("Violeta");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    @Override public String toString() {
        return this.color;
    }
}
