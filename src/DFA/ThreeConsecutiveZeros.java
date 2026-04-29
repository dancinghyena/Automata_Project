

package DFA;

import java.util.Scanner;




public class ThreeConsecutiveZeros {
	private enum States {
	    ZERO_ZERO,
	    ONE_ZERO,
	    TWO_ZERO,
	    THREE_ZERO,
	    ONE_FOUND,
	    Invalid
	    
	}
	
    public static void main(String[] args) {
     States state = States.ZERO_ZERO;
     Scanner input = new Scanner(System.in);
     System.out.println("Enter any binary number: ");
     String str = input.nextLine();

     char[] myCharacters = str.toCharArray();

     for(char c : myCharacters){
        switch(c){
            case '0':
                if(state == States.ZERO_ZERO){
                    state = States.ONE_ZERO;
                }
                else if(state == States.ONE_ZERO){
                    state = States.TWO_ZERO;
                }
                else if(state == States.TWO_ZERO){
                    state = States.THREE_ZERO;
                }
                else if(state == States.THREE_ZERO){
                    state = States.THREE_ZERO;
                }
                else if(state == States.ONE_FOUND){
                    state = States.ONE_ZERO;
                }
                break;
            case '1':
                if(state == States.ZERO_ZERO){
                    state = States.ZERO_ZERO;
                }
                else if(state == States.ONE_ZERO){
                    state = States.ZERO_ZERO;
                }
                else if(state == States.TWO_ZERO){
                    state = States.ZERO_ZERO;
                }
                else if(state == States.THREE_ZERO){
                    state = States.ONE_FOUND;
                }
                else if(state == States.ONE_FOUND){
                    state = States.ONE_FOUND;
                }
                break;
            default:
            state = States.Invalid;
            break;
        }
     }

     
     if(state == States.ONE_FOUND) {
    	 System.out.println("String accepted");
     }
     else {
    	 System.out.println("String not accepted");
     }

    }
}
