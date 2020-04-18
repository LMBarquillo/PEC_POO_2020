package constantes;


/**
 * Clase enumeradora Estado. Define el estado de fabricaci�n de los muebles.
 * @author Luis Miguel Barquillo
 */
public enum Estado
{
    PEDIDO("Pedido"), FABRICANDO("En fabricaci�n"), DETENIDO("Fabricaci�n detenida"), FINALIZADO("Finalizado");

    private String estado;

    Estado(String estado) {
        this.estado = estado;
    }

    @Override public String toString() {
        return this.estado;
    }
}
