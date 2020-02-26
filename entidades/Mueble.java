package entidades;

import constantes.Material;
import excepciones.MaterialNoPermitidoException;

/**
 * Clase Mueble. Clase padre de todos los tipos de mueble.
 * @author: Luis Miguel Barquillo
 */
public class Mueble
{
    public static class Estado {
        public static final String ESTADO_PROGRESO = "En fabricación";
        public static final String ESTADO_DETENIDO = "Fabricación detenida";
        public static final String ESTADO_FINALIZADO = "Finalizado";
        public static final String ESTADO_ENTREGADO = "Entregado";
    }

    private String estado;
    private Material material;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Material getMaterial() {
        return material;
    }

    protected void setMaterial(Material material) throws MaterialNoPermitidoException {
        this.material = material;
    }
}
