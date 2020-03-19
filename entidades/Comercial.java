package entidades;

import java.util.Date;

/**
 * Clase comercial. Describe los empleados responsables de las ventas y contacto a clientes
 * @author Luis Miguel Barquillo Romero
 */
public class Comercial extends Empleado
{
    private double porcentajeComision;

    public Comercial(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, Date antiguedad, double salario, double porcentajeComision) {
        super(nombre, nif, direccion, codigoPostal, localidad, telefono, antiguedad, salario);
        this.porcentajeComision = porcentajeComision;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }
}
