<<< ex7.1 >>>

VOWELS

package ex7;
import java.util.Scanner;
//Custom Exception class
class NoVowelException extends Exception {
public NoVowelException(String message) {
   super(message);
}
}
public class VowelChecker {
// Method to check if the string contains vowels
public static void checkVowels(String input) throws NoVowelException {
   // Check if the input string contains at least one vowel
   if (!input.toLowerCase().matches(".*[aeiou].*")) {
       throw new NoVowelException("The string does not contain any vowels.");
   } else {
       System.out.println("The string contains vowels.");
   }
}
public static void main(String[] args) {
   Scanner scanner = new Scanner(System.in); // Scanner for user input
   // Prompt user to enter a string
   System.out.print("Enter a string to check for vowels: ");
   String userInput = scanner.nextLine();
   // Try to check the string for vowels and catch any exceptions
   try {
       checkVowels(userInput);
   } catch (NoVowelException e) {
       System.out.println("Exception: " + e.getMessage());
   } finally {
       scanner.close(); // Close the scanner to prevent resource leaks
   }
}
}
