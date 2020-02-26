package entidades;

import constantes.Material;

/**
 * Clase Mesa. Define una mesa genérica.
 * @author Luis Miguel Barquillo
 */
public class Mesa extends Mueble
{
    private int ancho;
    private int largo;

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }
}
