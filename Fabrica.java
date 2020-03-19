import constantes.Valores;
import interfaz.Menu;
import repositorio.BBDDMuebles;
import repositorio.BBDDPersonas;

/**
 * Clase Fabrica. Clase principal del programa, conteniendo el método main
 * 
 * @author Luis Miguel Barquillo Romero
 * @since 19/02/2020
 * @version 1.0.0
 */
public class Fabrica
{
    private Menu menu;
    private BBDDMuebles bbddMuebles;
    private BBDDPersonas bbddPersonas;
    private GestionMuebles gestionMuebles;
    private GestionPersonas gestionPersonas;

    public Fabrica() {
        menu = new Menu();
        bbddMuebles = new BBDDMuebles();
        gestionMuebles = new GestionMuebles(this);
        gestionPersonas = new GestionPersonas(this);

        /** DATOS INTRODUCIDOS PARA TESTEAR LA APLICACIÓN **/
        //bbddPersonas.insertar(new ClientePersona("Manuel Pérez","04326587R","Calle del pez, 2","28080","Madrid","658945236","manuperez@gmail.com","manu2864"));
    }

    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica();

        fabrica.principal();
    }

    public void principal() {
        int opcion = menu.menuPrincipal();
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
                menu.mostrarDespedida();
                System.exit(0);
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
