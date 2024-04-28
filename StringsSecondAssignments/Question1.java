package StringsSecondAssignments;


/**
 * Write a description of Question1 here.
 * 
 * @author Katarzyna Badio
 * @version 1.0.0
 */
public class Question1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            if (index + 3 >= input.length()){
                break;
            }
            System.out.println("Index: " + index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            System.out.println("Index after updating: " + index);
            
        }
    }
    public void test() {
        findAbc("abcabcabcabca");
    }
}
