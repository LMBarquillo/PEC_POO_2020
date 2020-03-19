package entidades;

import constantes.Material;
import constantes.Turno;

import java.util.Date;

/**
 * Clase ArtesanoEnPlantilla. Define los artesanos que se encuentran contratados en plantilla.
 * @author Luis Miguel Barquillo Romero
 */
public class ArtesanoEnPlantilla extends Artesano
{
    private Turno turno;

	public ArtesanoEnPlantilla(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, Date antiguedad, double salario, Material especialidad, Turno turno) {
		super(nombre, nif, direccion, codigoPostal, localidad, telefono, antiguedad, salario, especialidad);
		this.turno = turno;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
}
