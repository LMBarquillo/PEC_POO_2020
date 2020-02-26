package entidades;

import java.util.Date;

/**
 * Clase Empleado. Define los objetos de Personas empleados
 * @author Luis Miguel Barquillo
 */
public class Empleado extends Persona
{
    private Date antiguedad;

    public Date getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Date antiguedad) {
        this.antiguedad = antiguedad;
    }
}
