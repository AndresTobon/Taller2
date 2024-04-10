package retojava;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CAMILO
 */
public class RetoJava {
    public static void main(String[] args) {
        String prueba1 = "ylaiguanatomabacafetomabacafealahoradelte";
        System.out.println("El substring más largo sin repetir en la prueba 1 de caracteres es: " + substringMasLargoSinRepetir(prueba1));

        String prueba2 = "aacaa12h3jdku3aa";
        System.out.println("El substring más largo sin repetir en la prueba 2 de caracteres es: " + substringMasLargoSinRepetir(prueba2));
       
        String digitos = "394";
        List<String> combinaciones = generarCombinaciones(digitos);
        System.out.println(combinaciones);
    }
       public static String substringMasLargoSinRepetir(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        int n = s.length();
        int resultadoInicio = 0; // Inicio del substring resultante
        int resultadoLongitud = 0; // Longitud del substring resultante

        // Este arreglo guarda la última posición conocida de cada caracter
        // Inicialmente, llenamos este arreglo con -1 para indicar que no hemos visto los caracteres aún
        int[] ultimaPos = new int[128]; // Considerando ASCII básico
        for (int i = 0; i < 128; i++) {
            ultimaPos[i] = -1;
        }

        int inicio = 0; // Inicio de la ventana actual

        for (int i = 0; i < n; i++) {
            inicio = Math.max(inicio, ultimaPos[s.charAt(i)] + 1); // Ajusta el inicio si el caracter actual ya fue visto
            // Si el nuevo substring es mayor que el resultante anterior, actualiza los valores
            if (i - inicio + 1 > resultadoLongitud) {
                resultadoInicio = inicio;
                resultadoLongitud = i - inicio + 1;
            }
            ultimaPos[s.charAt(i)] = i; // Actualiza la última posición del caracter actual
        }

        return s.substring(resultadoInicio, resultadoInicio + resultadoLongitud);
    }
        public static List<String> generarCombinaciones(String digitos) {
        if (digitos == null || digitos.isEmpty()) return List.of(); // Retorno lista vacía si la entrada no es válida
        
        // Mapeo de los dígitos a sus letras correspondientes
        String[] MAPEO = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        List<String> resultados = new ArrayList<>();
        resultados.add(""); // Inicializar con una cadena vacía para el bucle inicial
        
        for (int i = 0; i < digitos.length(); i++) {
            List<String> temp = new ArrayList<>();
            String letras = MAPEO[digitos.charAt(i) - '0'];
            for (String str : resultados) {
                for (char c : letras.toCharArray()) {
                    temp.add(str + c);
                }
            }
            resultados = temp; // Actualizar resultados con las combinaciones actuales
        }
        
        return resultados;
    }
}
