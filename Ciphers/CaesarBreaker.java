package Ciphers;


/**
 * Write a description of CaesarBreaker here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class CaesarBreaker {
    public int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] frequencies){
        int maxValue = 0;
        int maxI = -1;
        for (int i = 0; i < frequencies.length; i++){
            if (i == 0){
                maxValue = frequencies[i];
                maxI = i;
            } else if (frequencies[i] > maxValue){
                maxValue = frequencies[i];
                maxI = i;
            }
        }
        return maxI;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int decryptionKey = maxDex - 4;
        if (maxDex < 4){
            decryptionKey = 26 - (4 - maxDex);        
        }
        System.out.println("Decryption key: " + (26 - decryptionKey));
        return cc.encrypt(encrypted, 26 - decryptionKey);
    }
    
    public void testDecrypt(String encrypted){
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    public String decryptTwoKeys(String encrypted){
        String messageOne = "";
        String messageTwo = "";
        for (int i = 0; i < encrypted.length(); i++){
            if (i % 2 == 0){
                messageOne += encrypted.charAt(i);
            } else if (i % 2 != 0){
                messageTwo += encrypted.charAt(i);
            }
        }
        
        String messageOneDecrypted = decrypt(messageOne);
        String messageTwoDecrypted = decrypt(messageTwo);
        
        String finalStringDecrypted = "";
        int i = 0;
        String flag = "one";
        while (i < messageOneDecrypted.length() || i < messageTwoDecrypted.length()){
            if (flag.equals("one") && i < messageOneDecrypted.length()){
                finalStringDecrypted += messageOneDecrypted.charAt(i);
                flag = "two";
            } else if (flag.equals("one") && i >= messageOneDecrypted.length()){
                flag = "two";
            }
            if (flag.equals("two") && i < messageTwoDecrypted.length()){
                finalStringDecrypted += messageTwoDecrypted.charAt(i);
                flag = "one";
            } else if (flag.equals("two") && i >= messageTwoDecrypted.length()){
                flag = "one";
            }
            i += 1;
        }
        return finalStringDecrypted;
    }
}
