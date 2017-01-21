#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#define LOWER_BOUND 2000000

/**
 * COSC326 Jan 2017
 * harmonious_numbers.c
 * @version C89
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
  return sum;
}



int main(){
  int i;
  clock_t start,end; 
  start = clock();
  
  for(i = 2;i <= LOWER_BOUND;i++){
    int tmp = sum_divisors(i);
    if(tmp > i && sum_divisors(tmp) == i){
      printf("%d %d",i,tmp);
      printf("\n");    
    }
  }

  
  end = clock();
  fprintf(stderr, "%f\n", (end - start)/(double)CLOCKS_PER_SEC);
  
  return 1;
}


