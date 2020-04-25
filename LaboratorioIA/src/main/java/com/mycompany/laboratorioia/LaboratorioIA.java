/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.laboratorioia;

import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
import de.daslaboratorium.machinelearning.classifier.Classifier;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author eecon
 */
public class LaboratorioIA {

    static String[] conectores = {"yo", "tú", "usted", "él", "nosotros", "vosotros", "ustedes", "ellos", "mí", "ti", "ella", "nosotras", "vosotras",
        "ello", "ellas", "conmigo", "contigo", "sí", "me", "te", "se", "lo", "nos", "os", "los", "la", "le", "las", "les", "mío", "tuyo", "suyo",
        "nuestro", "vuestro", "mía", "tuya", "suya", "nuestra", "vuestra", "míos", "tuyos", "suyos", "nuestros", "vuestros", "mías", "tuyas", "suyas",
        "nuestras", "vuestras", "el", "este", "ese", "aquel", "esta", "esa", "aquella", "esto", "eso", "aquello", "estos", "esos", "aquellos", "estas",
        "esas", "aquellas", "un", "una", "unos", "unas", "a", "ante", "bajo", "cabe", "con", "contra", "de", "desde", "durante", "en", "entre", "hacia",
        "hasta", "mediante", "para", "por", "según", "sin", "sobre", "tras", "versus", "y", "vía", "e", "i", "ni", "no", "sino", "también", "tanto",
        "como", "así", "mismo", "pero", "mas", "mientras", "que", "o", "u", "ya", "sea", "porque", "aunque", "luego", "dado", "visto",
        "puesto", "pues", "si", "aun", "cuando", "salvo", "conque", "embargo", "tal", "ahí", "al"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Classifier<String, String> bayes = new BayesClassifier<String, String>();
        Scanner lectura = new Scanner(System.in);
        String[] caracteristicas;
        String cadena = "";

        System.out.println("Hola soy Panchito, el mejor bot predictivo.");
        do {
            System.out.println("Ingresa una frase:");
            cadena = lectura.nextLine();

            if (cadena.isEmpty()) {
                break;
            }

            caracteristicas = cadena.split(" ");
            System.out.println("Ingresa una etiqueta para la frase:");
            cadena = lectura.nextLine();
            bayes.learn(cadena, Arrays.asList(filtrarPalabras(caracteristicas)));

            System.out.println("\nPara dejar de asignar frases ingresa una cadena vacia\n");
        } while (!cadena.isEmpty());

        do {
            System.out.println("Ingresa una frase para evaluar:");
            cadena = lectura.nextLine();

            if (cadena.isEmpty()) {
                break;
            }

            caracteristicas = cadena.split(" ");
            System.out.println("Categoria: " + bayes.classify(Arrays.asList(caracteristicas)).getCategory()
                    + "\nProbabilidad: " + bayes.classify(Arrays.asList(caracteristicas)).getProbability());

            System.out.println("\nPara dejar de asignar frases ingresa una cadena vacia\n");
        } while (true);

    }

    static private String[] filtrarPalabras(String[] caracteristicas) {
        String[] resultado = new String[obtenerNumPalabrasAUsar(caracteristicas)];
        int posicion = 0;
        for (int i = 0; i < caracteristicas.length; i++) {
            if (!Arrays.asList(conectores).contains(caracteristicas[i])) {
                resultado[posicion] = caracteristicas[i];
                posicion++;
            }
        }

        return resultado;
    }

    static private int obtenerNumPalabrasAUsar(String[] caracteristicas) {
        int cuenta = caracteristicas.length;
        for (int i = 0; i < caracteristicas.length; i++) {
            if (Arrays.asList(conectores).contains(caracteristicas[i])) {
                cuenta--;
            }
        }
        return cuenta;
    }

}
