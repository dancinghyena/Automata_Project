
package DFA;

import java.util.Scanner;




public class EvenOneEvenZero {
	private enum States {
	    EVEN_ONE_EVEN_ZERO,
	    ODD_ONE_EVEN_ZERO,
	    EVEN_ONE_ODD_ZERO,
	    ODD_ONE_ODD_ZERO,
	    Invalid

	}
	
    public static void main(String[] args) {
     States state = States.EVEN_ONE_EVEN_ZERO;
     Scanner input = new Scanner(System.in);
     System.out.println("Enter any binary number: ");
     String str = input.nextLine();

     char[] myCharacters = str.toCharArray();

     for(char c : myCharacters){
        switch(c){
            case '0':
                if(state == States.EVEN_ONE_EVEN_ZERO){
                    state = States.EVEN_ONE_ODD_ZERO;
                }
                else if(state == States.ODD_ONE_EVEN_ZERO){
                    state = States.ODD_ONE_ODD_ZERO;
                }
                else if(state == States.EVEN_ONE_ODD_ZERO){
                    state = States.EVEN_ONE_EVEN_ZERO;
                }
                else if(state == States.ODD_ONE_ODD_ZERO){
                    state = States.ODD_ONE_EVEN_ZERO;
                }
                break;
            case '1':
                if(state == States.EVEN_ONE_EVEN_ZERO){
                    state = States.ODD_ONE_EVEN_ZERO;
                }
                else if(state == States.ODD_ONE_EVEN_ZERO){
                    state = States.EVEN_ONE_EVEN_ZERO;
                }
                else if(state == States.EVEN_ONE_ODD_ZERO){
                    state = States.ODD_ONE_ODD_ZERO;
                }
                else if(state == States.ODD_ONE_ODD_ZERO){
                    state = States.EVEN_ONE_ODD_ZERO;
                }
                break;
            default:
            state = States.Invalid;
            break;
        }
     }

     System.out.println("The state is: " + state);
     if(state == States.EVEN_ONE_EVEN_ZERO) {
    	 System.out.println("String accepted");
     }
     else {
    	 System.out.println("String not accepted");
     }

    }
}
