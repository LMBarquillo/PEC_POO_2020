package entidades;

import constantes.Categoria;

import java.util.Date;

/**
 * Clase Empleado. Define los objetos de Personas empleados
 * @author Luis Miguel Barquillo
 */
public abstract class Empleado extends Persona
{
    private Date antiguedad;
    private double salario;

    public Empleado(String nombre, String nif, String direccion, String codigoPostal, String localidad, String telefono, Date antiguedad, double salario) {
        super(nombre, nif, direccion, codigoPostal, localidad, telefono);
        this.antiguedad = antiguedad;
        this.salario = salario;
    }

    public Date getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Date antiguedad) {
        this.antiguedad = antiguedad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public abstract Categoria categoria();

    @Override public boolean esEmpleado() {
        return true;
    }

    @Override public boolean esCliente() {
        return false;
    }
}
