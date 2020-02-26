package constantes;

/**
 * Clase enumeradora Madera. Define los tipos de Maderas para los muebles
 * @author Luis Miguel Barquillo
 */
public enum Madera {
	ROBLE("Roble"), PINO("Pino"), NOGAL("Nogal"), CEREZO("Cerezo"), CASTANO("Castaño"), ABEDUL("Abedul");

    private String tipo;

    Madera(String tipo) {
	    this.tipo = tipo;
    }

    @Override public String toString() {
        return this.tipo;
    }
}
