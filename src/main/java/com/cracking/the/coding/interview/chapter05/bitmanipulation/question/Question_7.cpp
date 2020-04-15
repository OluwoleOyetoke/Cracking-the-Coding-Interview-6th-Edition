#include <stdio.h>
#include <stdlib.h>
#include <math.h>


int swapOddEvenBits(int x);

int swapOddEvenBits(int x) {
    return ( ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1) );
}

/*
main(){
	int input = 1;
	printf("\nInput: %d", input);
	int swapped = swapOddEvenBits(input);
	printf("\nSwaped: %d", swapped);
}
*/