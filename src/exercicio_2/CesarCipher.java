package exercicio_2;
public class CesarCipher {
    // Método para criptografar uma mensagem usando a cifra de César
    public static String encrypt(String message, int shift) {
        StringBuilder encryptedMessage = new StringBuilder();

        // Iterar sobre cada caractere da mensagem
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            // Verificar se o caractere é uma letra do alfabeto
            if (Character.isLetter(currentChar)) {
                // Determinar se o caractere é maiúsculo ou minúsculo
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                // Calcular o deslocamento aplicado à letra
                char encryptedChar = (char) ((currentChar - base + shift) % 26 + base);
                // Adicionar a letra criptografada ao resultado
                encryptedMessage.append(encryptedChar);
            } else {
                // Se o caractere não for uma letra do alfabeto, adicioná-lo sem criptografar
            	// So para casos fora do padrão mesmo 
                encryptedMessage.append(currentChar);
            }
        }

        return encryptedMessage.toString();
    }

    public static void main(String[] args) {
        // Exemplo de uso da cifra de César
        String message = "abcdefghijk";
        // Esperado (3): defghijklmn
        int shift = 3;

        // Criptografar a mensagem com um deslocamento de 3
        String encryptedMessage = encrypt(message, shift);
        System.out.println("Mensagem criptografada: " + encryptedMessage);
    }
}
