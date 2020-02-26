package constantes;

/**
 * Clase enumeradora Forma. Define las posibles formas de algunos muebles
 * @author Luis Miguel Barquillo
 */
public enum Forma {
	CUADRADO("Cuadrada"), RECTANGULO("Rectangular"), CIRCULO("Redonda");

	public String forma;

	Forma(String forma) {
		this.forma = forma;
	}

    @Override public String toString() {
        return this.forma;
    }
}
