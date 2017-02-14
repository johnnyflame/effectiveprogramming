#include <math.h>
#include <stdio.h>



float heron_area(float a, float c) {
//  printf ("\na+a+c: %f", a + a + c);
  float s = (a+a+c)/2.0f;
  printf("\ns: %f ",s);
  return (s-a)*sqrtf(s*(s-c));
}
float baseht_area(float a, float c) {
  float d = c/(2.0f*a);
  return sqrtf(1.0f - d*d)*a*c*0.5f;
}

int main(){

  float c,a;

  c = 1.00;
  a = 1.00;

  for(int i = 0; i < 20;i++){

    printf("\n\ta:%f \tc:%f",a,c);
    printf("\n%d\tHeron area: %e \tBase height area: %e",i,
           heron_area(a,c),baseht_area(a,c));

    a *= 10; 
  }
  printf("\n");
  return 0;
}
