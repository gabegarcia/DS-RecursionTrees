package RecursionTrees;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator implements Serializable {

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
	
	public static int combinations = 0, nPennies = 0, nNickels = 0, nDimes = 0, nQuarters = 0;
	
	private static List<String> combos; 
	
    public static int calculateChange(int cents) {
        // TODO:
        // Implement a recursive solution following the given documentation.
    	//int coinValues[] = {1,5,10,25};    	
    	    	
    	combos = new ArrayList<>();//Using ArrayList because add is O(1) and contains is O(n).
    	
    	//System.out.println("combos.size() before call to myChange: " + combos.size());
    	myChange(cents, nQuarters, nDimes, nNickels, cents);
    	//System.out.println("combos.size() after call to myChange: " + combos.size());
    	return combos.size();
    	    	
    }
    
    /* Testing sample code from link that Professor posted in the Module.
    public static int myChange(int coinValues[], int length, int cents) {
    	int count;
    	
    	int vPenny = 1, vNickle = 5, vDime = 10, vQuarter = 25; //values of coins
    	    	
    	if (cents == 0) return 1;
    	
    	if (cents < 0) return 0;
    	
    	if(length <=0 && cents >=1) return 0;
    	
    	//System.out.println("cents-coinValues[length -1]: " + cents + " - " + coinValues[length -1] +" = "+(cents-coinValues[length -1]));
    	return myChange(coinValues, length - 1, cents) + myChange(coinValues,length, cents-coinValues[length -1]);
    	    	
    }*/

    private static void myChange(int cents, int nQuarters, int nDimes, int nNickels, int nPennies) {
    	final int QUARTER = 25, DIME = 10, NICKEL = 5, PENNY = 1;
    	
    	/*if(nQuarters * QUARTER + nDimes * DIME + nNickels * NICKEL + nPennies * PENNY == cents) {
    		combinations++;
    		
    	}*/
    	int total = (nQuarters * QUARTER + nDimes * DIME + nNickels * NICKEL + nPennies * PENNY);
    	//System.out.println("total: " + total);
    	
    	if(nQuarters * QUARTER + nDimes * DIME + nNickels * NICKEL + nPennies * PENNY > cents) {
    		return;
    	}
    	
    	String combination = "[" + nQuarters  + ", " + nDimes + ", " + nNickels + ", " + nPennies + "]";
    	
    	if(!combos.contains(combination)) {
    		combos.add(combination);
    	}
    	//System.out.println(combination);
    	
    	if(nPennies >= 5) {
    		myChange(cents, nQuarters, nDimes, nNickels + 1, nPennies - 5);
    	}
    	
    	if(nPennies >= 10) {
    		myChange(cents, nQuarters, nDimes +1, nNickels, nPennies -10);
    	}
    	
    	if(nPennies >=25) {
    		myChange(cents, nQuarters + 1, nDimes, nNickels, nPennies - 25);
    	}
    }
    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.
    	
    	calculateChange(cents);
    	String combo ="";
    	try {
    		
    		//open a file
    		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CoinCombinations.txt"));
    			//iterate through combos
	    		for(int i = 0; i < combos.size(); i++){
	    		combo = combos.get(i);
	    		    		
	    		System.out.println(combo);//print indices
	    		
	    		out.writeObject(combo); //write indices to a file
	    		}
			out.close();//close file
   		
	    	}
    	catch (Exception ex) {
			ex.printStackTrace();
			//System.exit(1);
		}
    	
    }

} // End of class ChangeCalculator
