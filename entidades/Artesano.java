package entidades;

import constantes.Categoria;
import constantes.Condicion;
import constantes.Material;

import java.util.Date;

/**
 * Clase Artesano. Define los empleados que fabrican los muebles
 * @author Luis Miguel Barquillo Romero
 */
public abstract class Artesano extends Empleado
{
    private Material especialidad;

	public Artesano(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, Date antiguedad, double salario, Material especialidad) {
		super(nombre, nif, direccion, codigoPostal, localidad, telefono, antiguedad, salario);
		this.especialidad = especialidad;
	}

	public Material getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Material especialidad) {
		this.especialidad = especialidad;
	}

	public abstract Condicion condicion();

	@Override public Categoria categoria() {
		return Categoria.ARTESANO;
	}
}
