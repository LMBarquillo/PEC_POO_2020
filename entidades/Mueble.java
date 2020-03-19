package entidades;

import constantes.Estado;
import constantes.Material;

/**
 * Clase Mueble. Clase padre de todos los tipos de mueble.
 * @author: Luis Miguel Barquillo
 */
public class Mueble
{
    private String referencia;     // C�digo �nico que utilizaremos para identificar un mueble
    private Estado estado;
    private Material material;
    private Artesano artesano;  // El artesano asignado para su fabricaci�n
    private Cliente cliente;    // El cliente que ha pedido el mueble
    private String notas;       // Las notas de progreso dejadas por el artesano

    public Mueble(String referencia, Material material, Cliente cliente) {
        this.referencia = referencia;
        this.estado = Estado.PEDIDO;    // Cuando creamos un mueble nuevo, su estado es PEDIDO
        this.material = material;
        this.cliente = cliente;
        this.notas = "";
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    protected void setMaterial(Material material) {
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
