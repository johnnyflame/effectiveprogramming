


/**
 * Midpoint.java
 *
 * Test cases the midpoint function.
 *
 */

 
public class Midpoint{

    static final int INT_MAX = 2147483647;
    static final int INT_MIN = -2147483648;
    

    public static void main (String [] args){

	System.out.println(midpoint(INT_MAX,INT_MAX));

    }
    
    // Can we assume that x and y will be ints? 
    public static strictfp int midpoint(int x, int y){
	long temp = x + y;
	System.out.println("Temp: " + temp);
	return (int) temp / 2;
    }



    public static strictfp int min (int x, int y){
	return x < y ? x : y;
    }


    public static strictfp int max (int x, int y){
	return x > y ? x : y;
    }

    public static void testSymmetric (int lowerbound, int upperbound){
	for (int i = lowerbound;i < upperbound;i++){
	    for(int j = lowerbound;j < upperbound;j++){
		int a = midpoint(i,j);
		int b = midpoint(j,i);
		assert a == b;
	    }
	}
    }   
}




/*

Tasks:

1. Write the function. Make sure it is bullet-proof
2. Write test cases. Make sure to test on sufficiently large input.
3. Write the report explaining what goes wrong.


strictfp definition and usage:

Strictfp ensures that you get exactly the same results from your floating point calculations on every platform. 
If you don't use strictfp, the JVM implementation is free to use extra precision where available.

Basically, strictfp ensures that floating point arithmetic meets the standard of the IEEE 754, and is designed the make
the operation portable. It achieves this by letting JVM do the calculation, as opposed to the native hardware(cpu) perform
the calculation. This could lead to greater precision and reliability, at the cost of runtime speed. 

*/
