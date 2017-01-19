#include <stdio.h>
#include <stdlib.h>


/*
  Harmonious number:

  Task:

  Find all pairs of harmonious numbers such that the smaller one of the pair is
  less than 2,000,000

*/

/*
  Harmonious number definition:

  Sum of proper divisor of one is equal to the other

  Notes and hints:
1.

  1 does not count as a proper divisor of any number
  (Does this mean that all prime numbers should be excluded?)


2.

  Division is more expensive than multiplication (MOD costs more than *)


*/


/*

  General approach:

  1. Have a prime number check function, then use it in the main loop for
  excluding candidates.

  2. Have a function that spits out factors of a given integer.

  

  Brute force method:

  a) A for loop looping from n where n > 3, excluding any primes,
  

  
