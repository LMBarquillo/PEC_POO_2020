package constantes;


/**
 * Clase enumeradora Estado. Define el estado de fabricación de los muebles.
 * @author Luis Miguel Barquillo
 */
public enum Estado
{
    PEDIDO("Pedido"), FABRICANDO("En fabricación"), DETENIDO("Fabricación detenida"), FINALIZADO("Finalizado"), ENTREGADO("Entregado");

    private String estado;

    Estado(String estado) {
        this.estado = estado;
    }

    @Override public String toString() {
        return this.estado;
    }
}
