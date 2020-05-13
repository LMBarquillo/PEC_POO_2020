package entidades;

import constantes.Estado;
import constantes.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Mueble. Clase padre de todos los tipos de mueble.
 * @author: Luis Miguel Barquillo
 */
public class Mueble
{
    private int numTrabajo; // Número de trabajo: Código único que utilizaremos para identificar un mueble
    private Estado estado;
    private Material material;
    private Artesano artesano;          // El artesano asignado para su fabricación
    private Cliente cliente;            // El cliente que ha pedido el mueble
    private List<String> notas;         // Las notas de progreso dejadas por el artesano
    private Double precio;              // Inicialmente es nulo hasta que se comunica al cliente, por eso usamos la clase envoltorio
    private List<Pieza> piezas;         // Las piezas necesarias para fabricar el mueble

    public Mueble(int numTrabajo, Material material, Cliente cliente) {
        this.numTrabajo = numTrabajo;
        this.estado = Estado.PEDIDO;    // Cuando creamos un mueble nuevo, su estado es PEDIDO
        this.material = material;
        this.cliente = cliente;
        this.notas = new ArrayList<>();
    }

    public int getNumTrabajo() {
        return numTrabajo;
    }

    public void setNumTrabajo(int numTrabajo) {
        this.numTrabajo = numTrabajo;
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

    public List<String> getNotas() {
        return notas;
    }

    public void addNota(String nota) {
        notas.add(nota);
    }

    public boolean hasArtesano() {
        return getArtesano() != null;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setNotas(List<String> notas) {
        this.notas = notas;
    }

    public List<Pieza> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<Pieza> piezas) {
        this.piezas = piezas;
    }
}
