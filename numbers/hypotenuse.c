#include <math.h>
#include <stdio.h>


float hyp(float x, float y) {
  return sqrtf(x*x + y*y);
}

int main(void) {
  float x = 3.0f, y = 4.0f, z = 5.0f;
  
  for (int i = 0; i < 25; i++) {
    float e = fabsf(hyp(x, y) - z)/z;
    
    printf("%2d %e\n", i, e);
    
    x /= 10.0f, y /= 10.0f, z /= 10.0f;
  }
  return 0;
}
