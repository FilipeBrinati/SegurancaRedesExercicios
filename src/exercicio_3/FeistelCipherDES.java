//Nome: Filipe Brinati Furtado
//Matrícula: 201865563C

package exercicio_3;

public class FeistelCipherDES {
    
    // Primeira S-Box do DES
    private static final int[][] DES_SBOX = {
        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    };

    // Função de substituição (f) com S-Box
    private static int f(int right, int roundKey, int[][] sBox) {
        int sBoxRowIndex = (right >> 12) & 0xF; // Pegue os 4 bits mais significativos de 'right'
        int sBoxColIndex = right & 0xF; // Pegue os 4 bits menos significativos de 'right'
        
        // Ajusta os índices para garantir que estejam dentro dos limites da matriz S-Box
        sBoxRowIndex = Math.min(sBoxRowIndex, 3);
        sBoxColIndex = Math.min(sBoxColIndex, 15);
        //System.out.println("SBoxRowIndex: " + sBoxRowIndex);
        //System.out.println("SBoxColIndex: " + sBoxColIndex);
        
        return sBox[sBoxRowIndex][sBoxColIndex] ^ roundKey; // Aplica a substituição da S-Box e depois XOR com a chave da rodada
    }

    // Função principal para encriptar usando a cifra de Feistel
    public static int encrypt(int plaintext, int[] roundKeys, int[][] sBox) {
        int left = (plaintext >> 16) & 0xFFFF; // 16 bits mais significativos
        int right = plaintext & 0xFFFF; // 16 bits menos significativos

        for (int i = 0; i < 4; i++) { // Apenas 4 rodadas
            int temp = right;
            right = left ^ f(right, roundKeys[i], sBox); // Chama a função 'f' com a S-Box
            left = temp;
        }

        // Intercala os bits após as 4 rodadas
        return (right << 16) | left;
    }

    public static void main(String[] args) {
        // Chave mestra (para teste)
        int[] roundKeys = {0x0123, 0x4567, 0x89AB, 0xCDEF};

        // Mensagem de exemplo para ser encriptada (32 bits)
        int plaintext = 0x1234ABCD;

        // Utiliza a primeira S-Box do DES
        int[][] sBox = DES_SBOX;

        // Encripta a mensagem
        int ciphertext = encrypt(plaintext, roundKeys, sBox);

        // Exibe os resultados
        System.out.println("Mensagem Original: " + Integer.toHexString(plaintext));
        System.out.println("Mensagem Encriptada: " + Integer.toHexString(ciphertext));
    }
}