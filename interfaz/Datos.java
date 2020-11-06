package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase Datos. Solicita introducción de datos por teclado y gestiona la entrada.
 * 
 * @author Luis Miguel Barquillo
 */
public class Datos
{
    private final String TRUE = "S";
    private final String FALSE = "N";

    private final BufferedReader br;

    public Datos(BufferedReader br) {
        this.br = br;
    }

    /**
     * Pedir una cadena de texto por teclado
     * @param solicitud Solicitud que se muestra al usuario
     * @return Devuelve la cadena introducida
     */
    public String pedirString(String solicitud) {
        return pedirString(solicitud, false);
    }

    /**
     * Pedir una cadena de texto por teclado (Sobrecarga)
     * @param solicitud Solicitud que se muestra al usuario
     * @param opcional Indica si es opcional y puede dejarlo en blanco
     * @return Devuelve la cadena introducida
     */
    public String pedirString(String solicitud, boolean opcional) {
        try {
            String lectura;
            do {
                System.out.print(solicitud);
                lectura = br.readLine();
            } while(!opcional && lectura.length() == 0);

            return lectura;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Pedir un número entero por teclado
     * @param solicitud Solicitud que se muestra al usuario
     * @return Devuelve el número introducido
     */
    public Integer pedirEntero(String solicitud) {
        return pedirEntero(solicitud, false, 0, Integer.MAX_VALUE);
    }

    /**
     * Pedir un número entero por teclado (Sobrecarga)
     * @param solicitud Solicitud que se muestra al usuario
     * @param opcional Indica si es opcional y puede dejarlo en blanco
     * @return Devuelve el número introducido
     */
    public Integer pedirEntero(String solicitud, boolean opcional) {
        return pedirEntero(solicitud, opcional, 0, Integer.MAX_VALUE);
    }

    /**
     * Pedir un número entero por teclado (Sobrecarga 2)
     * @param solicitud Solicitud que se muestra al usuario
     * @param min Valor mínimo admitido
     * @param max Valor máximo admitido
     * @return Devuelve el número introducido
     */
    public Integer pedirEntero(String solicitud, int min, int max) {
        return pedirEntero(solicitud, false, min, max);
    }

    /**
     * Pedir un número entero por teclado (Sobrecarga 3)
     * @param solicitud Solicitud que se muestra al usuario
     * @param opcional Indica si es opcional y puede dejarlo en blanco
     * @param min Valor mínimo admitido
     * @param max Valor máximo admitido
     * @return Devuelve el número introducido
     */
    public Integer pedirEntero(String solicitud, boolean opcional, int min, int max) {
        try {
            String entero;
            do {
                System.out.print(solicitud);
                entero = br.readLine();
            } while(!opcional && (!esEntero(entero) || !cumpleRango(entero, min, max)));

            return esEntero(entero) ? Integer.parseInt(entero) : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Pedir un número decimal por teclado
     * @param solicitud Solicitud que se muestra al usuario
     * @return Devuelve el número introducido
     */
    public Double pedirDecimal(String solicitud) {
        return pedirDecimal(solicitud, false);
    }

    /* Pedir un número decimal por teclado (Sobrecarga)
     * @param solicitud Solicitud que se muestra al usuario
     * @param opcional Indica si es opcional y puede dejarlo en blanco
     * @return Devuelve el número introducido
     */
    public Double pedirDecimal(String solicitud, boolean opcional) {
        try {
            String decimal;
            do {
                System.out.print(solicitud);
                decimal = br.readLine();
            } while(!opcional && !esDecimal(decimal));

            return esDecimal(decimal) ? Double.parseDouble(decimal) : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Pedir un valor booleano por teclado
     * @param solicitud Solicitud que se muestra al usuario
     * @return Devuelve el número introducido
     */
    public Boolean pedirBooleano(String solicitud) {
        try {
            String booleano;
            do {
                System.out.print(solicitud);
                booleano = br.readLine();
            } while(!esSN(booleano));

            return booleano.toUpperCase().equals(TRUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Pedir una fecha por teclado
     * @param solicitud Solicitud que se muestra al usuario
     * @return Devuelve la fecha introducida
     */
    public Date pedirFecha(String solicitud) {
        return pedirFecha(solicitud, false);
    }

    /**
     * Pedir una fecha por teclado (Sobrecarga)
     * @param solicitud Solicitud que se muestra al usuario
     * @param opcional Indica si es opcional y puede dejarlo en blanco
     * @return Devuelve la fecha introducida
     */
    public Date pedirFecha(String solicitud, boolean opcional) {
        try {
            String fecha;
            do {
                System.out.print(solicitud);
                fecha = br.readLine();
            } while(!opcional && !esFecha(fecha));

            return esFecha(fecha) ? new SimpleDateFormat("dd/MM/yyyy").parse(fecha) : null;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Analiza si una cadena contiene un número y está entre un rango determinado
     * @param s Cadena a analizar
     * @param min Mínimo valor
     * @param max Máximo valor
     * @return Devuelve si la cadena cumple el rango
     */
    private boolean cumpleRango(String s, int min, int max) {
        int value = Integer.parseInt(s);
        return min <= value && value <= max;
    }

    /**
     * Comprueba si una cadena contiene un número entero
     * @param s Cadena a comprobar
     * @return Devuelve si es un número entero
     */
    private boolean esEntero(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Comprueba si una cadena contiene un número decimal
     * @param s Cadena a comprobar
     * @return Devuelve si es un número decimal
     */
    private boolean esDecimal(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Comprueba si una cadena contiene una fecha
     * @param s Cadena a comprobar
     * @return Devuelve si es una fecha
     */
    private boolean esFecha(String s) {
        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Comprueba si una cadena es una letra del conjunto ("S","s","N","n")
     * @param s Cadena a comprobar
     * @return Devuelve si pertenece al conjunto indicado
     */
    private boolean esSN(String s) {
        return s.length() == 1 && (s.toUpperCase().equals(TRUE) || s.toUpperCase().equals(FALSE));
    }

}
