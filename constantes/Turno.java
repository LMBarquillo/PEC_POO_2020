package constantes;


/**
 * Clase enumeradora Turno. Define los turnos de los artesanos en plantilla
 * @author Luis Miguel Barquillo Romero
 */
public enum Turno
{
    MATINAL("Matinal"), VESPERTINO("Vespertino"), NOCTURNO("Nocturno");

    private String turno;

    Turno(String turno) {
        this.turno = turno;
    }

    @Override public String toString() {
        return this.turno;
    }
}
