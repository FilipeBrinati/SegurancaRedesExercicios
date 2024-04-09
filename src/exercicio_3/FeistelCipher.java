package exercicio_3;

import java.security.SecureRandom;
import java.util.Arrays;

public class FeistelCipher {

    // Função de substituição (f) com S-Box
    private static int f(int right, int roundKey, int[][] sBox) {
        int sBoxRowIndex = (right >> 12) & 0xF; // Pegue os 4 bits mais significativos de 'right'
        int sBoxColIndex = right & 0xF; // Pegue os 4 bits menos significativos de 'right'
        return sBox[sBoxRowIndex][sBoxColIndex] ^ roundKey; // Aplica a substituição da S-Box e depois XOR com a chave da rodada
    }

    // Função principal para encriptar usando a cifra de Feistel
    public static int encrypt(int plaintext, int[] roundKeys, int[][] sBox) {
        int left = (plaintext >> 16) & 0xFFFF; // 16 bits mais significativos
        int right = plaintext & 0xFFFF; // 16 bits menos significativos

        for (int i = 0; i < 16; i++) {
            int temp = right;
            right = left ^ f(right, roundKeys[i], sBox); // Chama a função 'f' com a S-Box
            left = temp;

            System.out.println("Iteração " + (i+1) + " - Left Key: " + Integer.toHexString(left) + ", Right Key: " + Integer.toHexString(right));
        }

        // Intercala os bits após as 16 rodadas
        return (right << 16) | left;
    }

    // Função para imprimir a S-Box
    private static void printSBox(int[][] sBox) {
        System.out.println("S-Box:");
        for (int[] row : sBox) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    // Função para gerar uma S-Box aleatória de forma segura
    private static int[][] generateRandomSBox() {
        SecureRandom secureRandom = new SecureRandom();
        int[][] sBox = new int[16][16]; // Modificado para 16x16
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                sBox[i][j] = secureRandom.nextInt(65536); // 16 bits
            }
        }
        return sBox;
    }
    
    // Função para gerar uma chave mestra aleatória de forma segura
    private static int[] generateRandomMasterKey() {
        SecureRandom secureRandom = new SecureRandom();
        int[] masterKey = new int[16];
        for (int i = 0; i < 16; i++) {
            masterKey[i] = secureRandom.nextInt(65536); // 16 bits
        }
        return masterKey;
    }

    public static void main(String[] args) {
        // Gerar chave mestra aleatória
        int[] roundKeys = generateRandomMasterKey();
        System.out.println("Chave Mestra: " + Arrays.toString(roundKeys));
        
    	// Chave de 16 rodadas (usada para testar)
        //int[] roundKeys = {0x0123, 0x4567, 0x89AB, 0xCDEF, 0xFEDC, 0xBA98, 0x7654, 0x3210, 0x1357, 0x9BDF, 0x2468, 0xACF0, 0xECA3, 0x9512, 0x8437, 0xB6D8};

    	
        // Mensagem de exemplo para ser encriptada (32 bits)
        int plaintext = 0x1234ABCD;

        // Gerar S-Box aleatória
        int[][] sBox = generateRandomSBox();

        // Imprimir S-Box
        printSBox(sBox);

        // Encripta a mensagem
        int ciphertext = encrypt(plaintext, roundKeys, sBox);

        // Exibe os resultados
        System.out.println("Mensagem Original: " + Integer.toHexString(plaintext));
        System.out.println("Mensagem Encriptada: " + Integer.toHexString(ciphertext));
    }
}
