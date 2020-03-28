package interfaz;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase Datos. Solicita introducción de datos por teclado y gestiona la entrada.
 * 
 * @author Luis Miguel Barquillo
 */
public class Datos
{
    private final String TRUE = "S";
    private final String FALSE = "N";

    private BufferedReader br;

    public Datos(BufferedReader br) {
        this.br = br;
    }

    public String pedirString(String solicitud) {
        return pedirString(solicitud, false);
    }

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

    public Integer pedirEntero(String solicitud) {
        return pedirEntero(solicitud, 0, Integer.MAX_VALUE);
    }

    public Integer pedirEntero(String solicitud, int min, int max) {
        try {
            String entero;
            do {
                System.out.print(solicitud);
                entero = br.readLine();
            } while(!esEntero(entero) || !cumpleRango(entero, min, max));

            return Integer.parseInt(entero);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double pedirDecimal(String solicitud) {
        try {
            String decimal;
            do {
                System.out.print(solicitud);
                decimal = br.readLine();
            } while(!esDecimal(decimal));

            return Double.parseDouble(decimal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    private boolean cumpleRango(String s, int min, int max) {
        int value = Integer.parseInt(s);
        return min <= value && value <= max;
    }

    private boolean esEntero(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }

    private boolean esDecimal(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }

    private boolean esSN(String s) {
        return s.length() == 1 && (s.toUpperCase().equals(TRUE) || s.toUpperCase().equals(FALSE));
    }

}
