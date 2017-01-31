import java.lang.*;
import java.util.Random;


/**
 * Midpoint.java
 * 
 * @author Johnny Flame Lee, Frida Israelsson
 *
 */

 
public class Midpoint{

    static final int INT_MAX = 2147483647;
    static final int INT_MIN = -2147483648;
    

    public static void main (String [] args){
	testCases();
    }

    public static strictfp int midpoint(int x, int y){
	int a = min(x,y);
	int b = max(x,y);
	int result;

	if (a == b) return a;
	if(a < 0 && Math.abs(a) == b) return 0;
	
	result = a/2 + b/2;

	if (a % 2 != 0 && b % 2 != 0)
	    if(a > 0 && b >  0) result += 1;
	    else if(a > 0 && b < 0 || a < 0 && b > 0){
		result = result;
	    }
	    else{
		result -= 1;
	    }
	
	return result;
    }



    public static strictfp int min (int x, int y){
	return x <= y ? x : y;
    }


    public static strictfp int max (int x, int y){
	return x >= y ? x : y;
    }

    /**
     * Test cases
     * 
     * Test around borders and 0, then do a series of random inputs.
     */
    
    public static void testCases (){
	int input1,input2;
	Random rand = new Random();

	assert midpoint(INT_MAX,INT_MAX) == INT_MAX;
	assert midpoint(INT_MIN,INT_MIN) == INT_MIN;
	assert midpoint(INT_MAX,-INT_MAX) == 0;
	assert midpoint(INT_MAX,0) == INT_MAX/2;
	
	
	for(int i = 0;i < 100000; i++){
	    input1 = rand.nextInt();
	    input2 = rand.nextInt();
	    
	    assert midpoint(0,0) == 0;
	    assert midpoint(input1,input2) == midpoint(input2,input1);	    
	    if (input1 < input2){
		int result = midpoint(input1,input2);
		assert (input1 <= result && result <= input2); 	
	    }
	    assert midpoint(input1,input1) == input1;
	    assert midpoint(input1,-input1) == 0;
	    assert midpoint(input1,0) == input1/2;
	    assert midpoint(input1,input2) == -midpoint(-input1,-input2);
	
	}
    
    }
}
