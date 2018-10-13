#include <stdio.h>
#include <stdlib.h>
#include <math.h>


unsigned int numberOfFlips(unsigned int a, unsigned int b);
unsigned int countOnes(unsigned int number);
unsigned int isOne(unsigned int number, int index);


unsigned int isOne(unsigned int number, int index){
	 unsigned int mask = 0;
	mask = 1<<index;
	if((number & mask)>0){
		return 1;
	}else{
		return 0;
	}
	
}

unsigned int countOnes(unsigned int number){
	double logResult = log(number)/log(2);
	int noOfBits = (int) floor(logResult)+1;
	int check=0;
	int flipCount=0;
	for(int i=0; i<noOfBits; i++){
		check = isOne(number, i);
		if(check==1){
			flipCount++;
	}
	}
	return flipCount;
}

unsigned int numberOfFlips(unsigned int a, unsigned int b){
	if((a^b)==0){
		return 0;
	}
	
	unsigned int aggregate = a^b;
	int ones = countOnes(aggregate);
	return ones;	
}

/*
main(){
	int a = 12;
	int b = 1;
	printf("\na: %d b: %d", a,b);
	int num = numberOfFlips(a,b);
	printf("\nNumber of flips: %d", num);
}
*/