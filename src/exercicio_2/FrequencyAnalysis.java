//Nome: Filipe Brinati Furtado
//Matrícula: 201865563C

package exercicio_2;
import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalysis {
    // Frequência média de ocorrência de letras em português
    private static final double[] PORTUGUESE_LETTER_FREQUENCY = {
            14.634, 1.043, 3.882, 4.992, 12.570, 1.023, 1.303, 0.781, 6.186, 0.397,
            0.021, 2.779, 4.738, 4.446, 9.735, 2.523, 0.014, 6.530, 6.805, 4.336,
            3.639, 1.575, 0.037, 0.253, 0.006, 0.470
    };

    // Método para calcular a frequência das letras em uma mensagem
    public static Map<Character, Double> calculateLetterFrequency(String message) {
        // Converter a mensagem para minúsculas para facilitar a análise
        message = message.toLowerCase();
        // Mapa para armazenar a frequência de cada letra
        Map<Character, Double> letterFrequency = new HashMap<>();

        // Inicializar o mapa com frequência 0 para todas as letras do alfabeto
        for (char letter = 'a'; letter <= 'z'; letter++) {
            letterFrequency.put(letter, 0.0);
        }

        // Contar a ocorrência de cada letra na mensagem
        for (char letter : message.toCharArray()) {
            if (Character.isLetter(letter)) {
                letterFrequency.put(letter, letterFrequency.get(letter) + 1);
            }
        }

        // Calcular a frequência relativa de cada letra
        int totalLetters = message.replaceAll("[^a-z]", "").length();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            double frequency = letterFrequency.get(letter) / totalLetters * 100;
            letterFrequency.put(letter, frequency);
        }

        return letterFrequency;
    }

    // Método para comparar a frequência de letras de uma mensagem com a frequência média
    public static void compareWithAverageFrequency(Map<Character, Double> letterFrequency) {
        System.out.println("Letra\tFrequência\tFrequência Média");

        char letter = 'a';
        for (double frequency : letterFrequency.values()) {
            System.out.printf("%c\t%.2f%%\t\t%.2f%%\n", letter, frequency, PORTUGUESE_LETTER_FREQUENCY[letter - 'a']);
            letter++;
        }
    }

    public static void main(String[] args) {
        // Mensagem original
        String originalMessage = "Hello, World!";
        // Deslocamento para criptografia
        int shift = 3;
        
        // Criptografar a mensagem original
        String encryptedMessage = CesarCipher.encrypt(originalMessage, shift);
        System.out.println("Mensagem criptografada: " + encryptedMessage);

        // Calcular a frequência das letras na mensagem criptografada
        Map<Character, Double> letterFrequency = calculateLetterFrequency(encryptedMessage);
        // Comparar com a frequência média de letras em português
        compareWithAverageFrequency(letterFrequency);
    }

}
