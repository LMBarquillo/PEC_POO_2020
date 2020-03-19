package constantes;

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

    private String color;

    Color(String color) {
        this.color = color;
    }

    @Override public String toString() {
        return this.color;
    }
}
