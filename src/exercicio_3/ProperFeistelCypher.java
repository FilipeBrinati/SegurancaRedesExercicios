//Nome: Filipe Brinati Furtado
//Matrícula: 201865563C

package exercicio_3;

import java.math.BigInteger;

public class ProperFeistelCypher {

    // Chave de 256 bits
    private static final BigInteger KEY = new BigInteger("0123456789ABCDEF0123456789ABCDEF", 16);

    // Primeira S-box do DES
    private static final int[] SBOX = {
        14, 0,  4,  15, 13, 7,  1,  4,  2,  14, 15, 2,  11, 13, 8,  1,
        3,  10, 10, 6,  6,  12, 12, 11, 5,  9,  9,  5,  0,  3,  7,  8,
        4,  15, 1,  12, 14, 8,  8,  2,  13, 4,  6,  9,  2,  1,  11, 7,
        15, 5,  12, 11, 9,  3,  7,  14, 3,  10, 10, 0,  5,  6,  0,  13
    };

    // Função de mistura (F-function)
    private static int f(int halfBlock, int roundKey) {
        int expandedHalfBlock = expand(halfBlock);
        int xored = expandedHalfBlock ^ roundKey;
        int output = 0;
        for (int i = 0; i < 8; i++) {
            output |= (SBOX[(xored >>> (i * 6)) & 0x3F] << (i * 4));
        }
        return permute(output);
    }

    // Expansão
    private static int expand(int halfBlock) {
        return ((halfBlock & 0x00000001) << 23) |
                ((halfBlock & 0x00000002) << 19) |
                ((halfBlock & 0x00000004) << 15) |
                ((halfBlock & 0x00000008) << 11) |
                ((halfBlock & 0x00000010) << 7) |
                ((halfBlock & 0x00000020) << 3) |
                ((halfBlock & 0x00000040) >>> 1) |
                ((halfBlock & 0x00000080) >>> 5);
    }

    // Permutação final
    private static int permute(int input) {
        return ((input & 0x00008000) >>> 15) |
                ((input & 0x00004000) >>> 11) |
                ((input & 0x00002000) >>> 7) |
                ((input & 0x00001000) >>> 3) |
                ((input & 0x00000800) << 1) |
                ((input & 0x00000400) << 5) |
                ((input & 0x00000200) << 9) |
                ((input & 0x00000100) << 13) |
                ((input & 0x00000080) << 17) |
                ((input & 0x00000040) << 21) |
                ((input & 0x00000020) << 25) |
                ((input & 0x00000010) << 29) |
                ((input & 0x00000008) << 13) |
                ((input & 0x00000004) << 25) |
                ((input & 0x00000002) << 9) |
                ((input & 0x00000001) << 21);
    }

    // Algoritmo de Feistel
    public static int feistel(int block, int numRounds) {
        int left = block >>> 16;
        int right = block & 0xFFFF;

        for (int i = 0; i < numRounds; i++) {
            int temp = left;
            left = right;
            right = temp ^ f(right, getKeyForRound(i));
            System.out.println("Round " + (i + 1) + ": Texto criptografado: " + Integer.toHexString((left << 16) | right));
        }

        return (left << 16) | right;
    }

    // Geração da chave para cada rodada
    private static int getKeyForRound(int round) {
        return KEY.shiftRight((round % 8) * 4).intValue() & 0x0F;
    }

    public static void main(String[] args) {
        int plainText = 0x12345678;
        int encryptedText = feistel(plainText, 16);
        System.out.println("Texto criptografado final: " + Integer.toHexString(encryptedText));
    }
}