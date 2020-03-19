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
    private Integer codigo;     // Código único que utilizaremos para identificar un mueble
    private Estado estado;
    private Material material;
    private Artesano artesano;  // El artesano asignado para su fabricación
    private Cliente cliente;    // El cliente que ha pedido el mueble
    private String notas;       // Las notas de progreso dejadas por el artesano

    public Mueble(Integer codigo, Material material, Cliente cliente) {
        this.codigo = codigo;
        this.estado = Estado.PEDIDO;    // Cuando creamos un mueble nuevo, su estado es PEDIDO
        this.material = material;
        this.cliente = cliente;
        this.notas = "";
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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
