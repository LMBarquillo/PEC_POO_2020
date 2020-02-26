package constantes;


/**
 * Clase enumeradora Material. Define los tipos de Materiales para los muebles *
 * @author Luis Miguel Barquillo
 */
public enum Material
{
    // Si definimos un constructor en el enum, podemos asignar valores para luego sobrescribir el toString()
    PLASTICO("Pl�stico"), MADERA("Madera"), METAL("Metal"), CRISTAL("Cristal");

    private String material;

    // En los constructores de tipo enum, omitimos el modificador de ámbito, pues siempre es private y sería redundante
    Material(String material) {
        this.material = material;
    }

    @Override public String toString() {
        return material;
    }
}
