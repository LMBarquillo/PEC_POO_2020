package entidades;

import constantes.Estado;
import constantes.Material;
import excepciones.MaterialNoPermitidoException;

/**
 * Clase Mueble. Clase padre de todos los tipos de mueble.
 * @author: Luis Miguel Barquillo
 */
public class Mueble
{
    private Estado estado;
    private Material material;
    private Artesano artesano;  // El artesano asignado para su fabricación
    private Cliente cliente;    // El cliente que ha pedido el mueble
    private String notas;       // Las notas de progreso dejadas por el artesano

    public Mueble() {
        this.estado = Estado.PEDIDO;    // Cuando creamos un mueble nuevo, su estado es PEDIDO
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Material getMaterial() {
        return material;
    }

    protected void setMaterial(Material material) throws MaterialNoPermitidoException {
        this.material = material;
    }

    public Artesano getArtesano() {
        return artesano;
    }

    public void setArtesano(Artesano artesano) {
        this.artesano = artesano;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
