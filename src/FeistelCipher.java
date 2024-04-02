import java.math.BigInteger;

public class FeistelCipher {

    // Número de rodadas
    private static final int NUM_ROUNDS = 16;

    // Chave de criptografia
    private static final BigInteger KEY = new BigInteger("12345678901234567890");

    // Função de Feistel
    public static BigInteger feistelFunction(BigInteger block, BigInteger key) {
        return block.xor(key);
    }

    // Rodada da cifra de Feistel
    public static BigInteger feistelRound(BigInteger block, int round) {
        BigInteger left = block.shiftRight(32);
        BigInteger right = block.and(new BigInteger("FFFFFFFF", 16));

        BigInteger newLeft = right;
        BigInteger newRight = left.xor(feistelFunction(right, KEY));

        if (round < NUM_ROUNDS) {
            return newRight.shiftLeft(32).or(newLeft);
        } else {
            return newLeft.shiftLeft(32).or(newRight);
        }
    }

    // Função de criptografia
    public static BigInteger encrypt(BigInteger plaintext) {
        BigInteger block = plaintext;
        for (int i = 0; i < NUM_ROUNDS; i++) {
            block = feistelRound(block, i);
        }
        return block;
    }

    // Função de descriptografia
    public static BigInteger decrypt(BigInteger ciphertext) {
        BigInteger block = ciphertext;
        for (int i = NUM_ROUNDS - 1; i >= 0; i--) {
            block = feistelRound(block, i);
        }
        return block;
    }

    public static void main(String[] args) {
        BigInteger plaintext = new BigInteger("1421234123429592");
        System.out.println("Plaintext: " + plaintext);

        // Criptografar
        BigInteger ciphertext = encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        // Descriptografar
        BigInteger decrypted = decrypt(ciphertext);
        System.out.println("Decrypted: " + decrypted);
    }
}
