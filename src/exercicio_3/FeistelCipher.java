package exercicio_3;
import java.security.SecureRandom;

public class FeistelCipher {

    // Função de substituição (f)
    private static int f(int right, int roundKey) {
        // Aqui, uma substituição simples é usada, apenas retornando o resultado da operação XOR
        return right ^ roundKey;
    }

    // Função principal para encriptar usando a cifra de Feistel
    public static int encrypt(int plaintext, int[] roundKeys, int[][] sBox) {
        int left = (plaintext >> 16) & 0xFFFF; // 16 bits mais significativos
        int right = plaintext & 0xFFFF; // 16 bits menos significativos

        for (int i = 0; i < 16; i++) {
            int temp = right;
            right = left ^ f(right, roundKeys[i]);
            left = temp;
        }

        // Intercala os bits após as 16 rodadas
        return (right << 16) | left;
    }

    // Função para gerar uma S-Box aleatória de forma segura
    private static int[][] generateRandomSBox() {
        SecureRandom secureRandom = new SecureRandom();
        int[][] sBox = new int[4][16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 16; j++) {
                sBox[i][j] = secureRandom.nextInt(16);
            }
        }
        return sBox;
    }

    public static void main(String[] args) {
        // Chave de 16 rodadas (gerada aleatoriamente ou derivada de uma chave mestra)
        int[] roundKeys = {0x0123, 0x4567, 0x89AB, 0xCDEF, 0xFEDC, 0xBA98, 0x7654, 0x3210,
                           0x1357, 0x9BDF, 0x2468, 0xACF0, 0xECA3, 0x9512, 0x8437, 0xB6D8};

        // Mensagem de exemplo para ser encriptada (32 bits)
        int plaintext = 0x8423DFAC;

        // Gerar S-Box aleatória
        int[][] sBox = generateRandomSBox();

        // Encripta a mensagem
        int ciphertext = encrypt(plaintext, roundKeys, sBox);

        // Exibe os resultados
        System.out.println("Mensagem Original: " + Integer.toHexString(plaintext));
        System.out.println("Mensagem Encriptada: " + Integer.toHexString(ciphertext));
    }
}
