#include <stdio.h>
#include <stdlib.h>
#include <math.h>

/**
 * COSC326 Jan 2017
 * harmonious_numbers.c
 * @author Johnny Flame Lee
 * @ID 83924
 *
 * Reference:
 * Finding divisors in O(sqrt(n)) time:
 * http://www.geeksforgeeks.org/find-divisors-natural-number-set-1/
 * 
 **/



static int sum_divisors(int n){
  int i;
  int sum = 0;
  /*
    printf("input: %d", n);
    printf("\n");
  */
  for(i = 2; i <=sqrt(n) + 1;i++){
    if (n % i == 0){
      if (n/i == i){
	sum += i;	
      }else{
	sum += i;
	sum += n/i;
      }
    }
  }

  /*TODO:
    ??? offset with magic number, need to fix */
  sum += 1;
  /*  printf("output: %d", sum);
      printf("\n");
  */
  return sum;
}


/*
  Current problem:

  1. determine if an amicable pair exist, if not, then terminate the loop

*/


int main(){


  int min = 20000000;
  int i;
  int last_result = 0;

  for(i = 2;i < min;i++){
    int tmp = sum_divisors(i);
    if(sum_divisors(tmp) == i && tmp != i && last_result != i){
      printf("%d %d",i,tmp);
      printf("\n");
      last_result = tmp;
    
    }
  }


  return 1;
}


