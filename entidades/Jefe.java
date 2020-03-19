package entidades;

import java.util.Date;

/**
 * Clase Jefe. Describe los puestos de responsable de fábrica
 * @author Luis Miguel Barquillo
 */
public class Jefe extends Empleado
{
    private double porcentajeAcciones;

    public Jefe(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, Date antiguedad, double salario, double porcentajeAcciones) {
        super(nombre, nif, direccion, codigoPostal, localidad, telefono, antiguedad, salario);
        this.porcentajeAcciones = porcentajeAcciones;
    }

    public double getPorcentajeAcciones() {
        return porcentajeAcciones;
    }

    public void setPorcentajeAcciones(double porcentajeAcciones) {
        this.porcentajeAcciones = porcentajeAcciones;
    }
}
