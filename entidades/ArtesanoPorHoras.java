package entidades;

import constantes.Material;

import java.util.Date;

/**
 * Clase ArtesanoPorHoras. Define los Artesanos que trabajan por horas.
 * @author Luis Miguel Barquillo Romero
 */
public class ArtesanoPorHoras extends Artesano
{
    private int numHoras;

    public ArtesanoPorHoras(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, Date antiguedad, double salario, Material especialidad, int numHoras) {
        super(nombre, nif, direccion, codigoPostal, localidad, telefono, antiguedad, salario, especialidad);
        this.numHoras = numHoras;
    }

    public int getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }
}
