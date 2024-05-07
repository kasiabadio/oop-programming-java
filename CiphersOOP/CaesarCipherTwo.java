package CiphersOOP;


/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class CaesarCipherTwo {
    private String alphabetU;
    private String shiftedAlphabetUeven;
    private String shiftedAlphabetUodd;
    private String alphabetL;
    private String shiftedAlphabetLeven;
    private String shiftedAlphabetLodd;
    private int keyP1;
    private int keyP2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetUeven = alphabetU.substring(key1) + alphabetU.substring(0, key1);
        shiftedAlphabetUodd = alphabetU.substring(key2) + alphabetU.substring(0, key2);
        
        alphabetL = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabetLeven = alphabetL.substring(key1) + alphabetL.substring(0, key1);
        shiftedAlphabetLodd = alphabetL.substring(key2) + alphabetL.substring(0, key2);
        
        keyP1 = key1;
        keyP2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int indexUpper = alphabetU.indexOf(currChar);
            if (indexUpper != -1){
                if (i % 2 == 0){
                    char newChar = shiftedAlphabetUeven.charAt(indexUpper);
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar = shiftedAlphabetUodd.charAt(indexUpper);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int indexLower = alphabetL.indexOf(currChar);
                if (indexLower != -1){
                    if (i % 2 == 0){
                        char newChar = shiftedAlphabetLeven.charAt(indexLower);
                        encrypted.setCharAt(i, newChar);
                    } else {
                        char newChar = shiftedAlphabetLodd.charAt(indexLower);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        String messageOne = "";
        String messageTwo = "";
        for (int i = 0; i < input.length(); i++){
            if (i % 2 == 0){
                messageOne += input.charAt(i);;
            } else if (i % 2 != 0){
                messageTwo += input.charAt(i);
            }
        }
        
        CaesarCipher cc1 = new CaesarCipher(26 - keyP1);
        String messageOneDecrypted = cc1.encrypt(messageOne);
        
        CaesarCipher cc2 = new CaesarCipher(26 - keyP2);
        String messageTwoDecrypted = cc2.encrypt(messageTwo);
        
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
