import constantes.*;
import entidades.*;
import interfaz.EntradaSalida;
import repositorio.BBDDMuebles;
import repositorio.BBDDPersonas;
import repositorio.BBDDPiezas;

import java.util.Arrays;
import java.util.Date;

/**
 * Clase Fabrica. Clase principal del programa, conteniendo el m�todo main
 * 
 * @author Luis Miguel Barquillo Romero
 * @since 19/02/2020
 * @version 1.0.0
 */
public class Fabrica
{
    private final EntradaSalida es;
    private final BBDDMuebles bbddMuebles;
    private final BBDDPersonas bbddPersonas;
    private final BBDDPiezas bbddPiezas;
    private final GestionMuebles gestionMuebles;
    private final GestionPersonas gestionPersonas;

    /**
     * Constructor. Inicializa nuestras propiedades.
     */
    public Fabrica() {
        es = new EntradaSalida();
        bbddMuebles = new BBDDMuebles();
        bbddPersonas = new BBDDPersonas();
        bbddPiezas = new BBDDPiezas();
        gestionMuebles = new GestionMuebles(this);
        gestionPersonas = new GestionPersonas(this);

        insertarDatosMockup();     // Insertar datos de ejemplo para tests
    }

    /**
     * El main del programa. Aqu� empieza todo
     * @param args
     */
    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica();
        fabrica.principal();
    }

    /**
     * M�todo principal. Es llamdo desde main para comenzar la ejecuci�n del programa.
     * Esta t�cnica nos permite usar m�todos y propiedades de la propia clase sin necesidad de ponerlos static.
     */
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
                gestionPersonas.gestionEmpleados();
                break;
            case Valores.Principal.SALIR:
                es.getMenu().mostrarDespedida();
                System.exit(0);
        }
    }

    /* GETTER Y SETTERS */

    public EntradaSalida getEs() {
        return es;
    }

    public GestionMuebles getGestionMuebles() {
        return gestionMuebles;
    }

    public GestionPersonas getGestionPersonas() {
        return gestionPersonas;
    }

    public BBDDMuebles getBbddMuebles() {
        return bbddMuebles;
    }

    public BBDDPersonas getBbddPersonas() {
        return bbddPersonas;
    }

    public BBDDPiezas getBbddPiezas() {
        return bbddPiezas;
    }

    /**
     * M�todo para la inserci�n de datos de prueba para testear la aplicaci�n
     */
    public void insertarDatosMockup() {
        bbddPiezas.insertar(new Pieza("TMAD","Tablero madera"));
        bbddPiezas.insertar(new Pieza("PMAD","Pata madera (Set 4 piezas)"));
        bbddPiezas.insertar(new Pieza("TCRI","Tablero cristal"));
        bbddPiezas.insertar(new Pieza("RMET","Revistero met�lico"));
        bbddPiezas.insertar(new Pieza("TLAT","Tirador de lat�n (Set 5 piezas)"));
        bbddPiezas.insertar(new Pieza("RACO","Revestimiento para acolchado"));
        bbddPiezas.insertar(new Pieza("EACO","Espuma de acolchado"));
        bbddPiezas.insertar(new Pieza("AMET","Armaz�n met�lico"));
        bbddPiezas.insertar(new Pieza("RSIL","Rueda para sillas (Set 5 piezas)"));
        bbddPiezas.insertar(new Pieza("TANT","Taco antideslizante (Set 5 piezas)"));

        Cliente cliente1 = new ClientePersona("Manuel P�rez", "04326587R", "Calle del pez, 2", "28080", "Madrid", "658945236", "manuperez@gmail.com", "manu2864");
        Cliente cliente2 = new ClienteEmpresa("Bar Paco","B45956836", "Calle del R�o", "45600", "Talavera", "925683641", "barpaco@gmail.com", "Paco");
        bbddPersonas.insertar(cliente1);
        bbddPersonas.insertar(cliente2);
        bbddPersonas.insertar(new Jefe("Arturo Jim�nez", "04128391F", "Avda. Castellana, 3", "28001", "Madrid", "695362574",new Date(1299888000000L), 2500D, 100));
        bbddPersonas.insertar(new Comercial("Pablo L�pez", "11653268G", "Paseo del Puerto, 23", "46001", "Valencia", "963526541", new Date(1398902400000L), 1250D, 30));
        bbddPersonas.insertar(new ArtesanoEnPlantilla("Juan Ant�nez", "28351246A", "Calle del Ej�rcito, 3", "45001", "Toledo", "925215432", new Date(1506816000000L), 1100D, Material.MADERA, Turno.MATINAL));
        Artesano artesano = new ArtesanoPorHoras("Pedro Romero", "06352645L", "Calle Prado, 2","45600", "Talavera", "642356412", new Date(1519862400000L), 900D, Material.MADERA, 6);
        bbddPersonas.insertar(artesano);
        Silla silla = new SillaCocina(1, Material.MADERA, cliente1, true, true);
        silla.setPiezas(getBbddPiezas().obtener(Arrays.asList("TMAD", "PMAD", "RACO", "EACO")));
        silla.setArtesano(artesano);
        Mesa mesa = new MesaComedor(2, cliente1, 100, 120, Madera.ABEDUL, true);
        mesa.setPiezas(getBbddPiezas().obtener(Arrays.asList("TMAD", "PMAD")));
        mesa.setArtesano(artesano);
        mesa.setEstado(Estado.DETENIDO);    // Falta de piezas
        cliente1.getMuebles().add(silla);
        cliente1.getMuebles().add(mesa);
        bbddMuebles.insertar(silla);
        bbddMuebles.insertar(mesa);
        bbddMuebles.insertar(new MesaComedor(2, cliente2, 100, 160, Madera.NOGAL, true));
        bbddMuebles.insertar(new SillaOficinaConRuedas(3, cliente2, true, true, 5));

    }
}
