import constantes.Madera;
import constantes.Material;
import constantes.Turno;
import constantes.Valores;
import entidades.*;
import interfaz.EntradaSalida;
import repositorio.BBDDMuebles;
import repositorio.BBDDPersonas;

import java.util.Date;

/**
 * Clase Fabrica. Clase principal del programa, conteniendo el método main
 * 
 * @author Luis Miguel Barquillo Romero
 * @since 19/02/2020
 * @version 1.0.0
 */
public class Fabrica
{
    private EntradaSalida es;
    private BBDDMuebles bbddMuebles;
    private BBDDPersonas bbddPersonas;
    private GestionMuebles gestionMuebles;
    private GestionPersonas gestionPersonas;

    public Fabrica() {
        es = new EntradaSalida();
        bbddMuebles = new BBDDMuebles();
        bbddPersonas = new BBDDPersonas();
        gestionMuebles = new GestionMuebles(this);
        gestionPersonas = new GestionPersonas(this);

        /* DATOS HARDCODED PARA TESTEAR LA APLICACIÓN */
        Cliente cliente1 = new ClientePersona("Manuel Pérez", "04326587R", "Calle del pez, 2", "28080", "Madrid", "658945236", "manuperez@gmail.com", "manu2864");
        Cliente cliente2 = new ClienteEmpresa("Bar Paco","B45956836", "Calle del Río", "45600", "Talavera", "925683641", "barpaco@gmail.com", "Paco");
        bbddPersonas.insertar(cliente1);
        bbddPersonas.insertar(cliente2);
        bbddPersonas.insertar(new Jefe("Arturo Jiménez", "04128391F", "Avda. Castellana, 3", "28001", "Madrid", "695362574",new Date(1299888000000L), 2500D, 100));
        bbddPersonas.insertar(new Comercial("Pablo López", "11653268G", "Paseo del Puerto, 23", "46001", "Valencia", "963526541", new Date(1398902400000L), 1250D, 30));
        bbddPersonas.insertar(new ArtesanoEnPlantilla("Juan Antúnez", "28351246A", "Calle del Ejército, 3", "45001", "Toledo", "925215432", new Date(1506816000000L), 1100D, Material.MADERA, Turno.MATINAL));
        bbddPersonas.insertar(new ArtesanoPorHoras("Pedro Romero", "06352645L", "Calle Prado, 2","45600", "Talavera", "642356412", new Date(1519862400000L), 900D, Material.MADERA, 6));
        bbddMuebles.insertar(new SillaCocina("SCMC1", Material.MADERA, cliente1, false, true));
        bbddMuebles.insertar(new MesaComedor("MCNC2", cliente2, 100, 160, Madera.NOGAL, true));
        bbddMuebles.insertar(new SillaOficinaConRuedas("SOCRC2", cliente2, true, true, 5));
    }

    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica();

        fabrica.principal();
    }

    public void principal() {
        int opcion = es.getMenu().menuPrincipal();
        switch (opcion) {
            case Valores.Principal.MUEBLES:
                gestionMuebles.gestionPrincipalMuebles();
                break;
            case Valores.Principal.CLIENTES:
                gestionPersonas.gestionClientes();
                break;
            case Valores.Principal.EMPLEADOS:
                gestionPersonas.gestionUsuarios();
                break;
            case Valores.Principal.SALIR:
                es.getMenu().mostrarDespedida();
                System.exit(0);
        }
    }

    public EntradaSalida getEs() {
        return es;
    }

    public void setEs(EntradaSalida es) {
        this.es = es;
    }

    public GestionMuebles getGestionMuebles() {
        return gestionMuebles;
    }

    public void setGestionMuebles(GestionMuebles gestionMuebles) {
        this.gestionMuebles = gestionMuebles;
    }

    public GestionPersonas getGestionPersonas() {
        return gestionPersonas;
    }

    public void setGestionPersonas(GestionPersonas gestionPersonas) {
        this.gestionPersonas = gestionPersonas;
    }

    public BBDDMuebles getBbddMuebles() {
        return bbddMuebles;
    }

    public void setBbddMuebles(BBDDMuebles bbddMuebles) {
        this.bbddMuebles = bbddMuebles;
    }

    public BBDDPersonas getBbddPersonas() {
        return bbddPersonas;
    }

    public void setBbddPersonas(BBDDPersonas bbddPersonas) {
        this.bbddPersonas = bbddPersonas;
    }
}
