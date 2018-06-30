#include <stdio.h>
#include <stdlib.h>
/**
 * <b>Next Number:</b> Given a positive integer, print the next smallest and the
 * next largest number that have the same number of 1 bits in their binary
 * representation.
 * 
 * Answer Written in C
 *
 * @author Oluwole Oyetoke {@literal <}oluwoleoyetoke {@literal @}
 * gmail.com{@literal >}
 */
unsigned int turnOffBit(unsigned int number, int index);
unsigned int turnOnBit(unsigned int number, int index);
unsigned int isOne(unsigned int number, int index);
unsigned int nextLargest(unsigned int number);
unsigned int nextSmallest(unsigned int number);

unsigned int turnOffBit(unsigned int number, int index){
	unsigned int mask = 0;
	mask = ~(1<<index);
	number = number & mask;
	return number;
}


unsigned int turnOnBit(unsigned int number, int index){
    unsigned int mask = 0;
	mask = 1<<index;
	number = number | mask;
	return number;	
}

unsigned int isOne(unsigned int number, int index){
	 unsigned int mask = 0;
	mask = 1<<index;
	if((number & mask)>0){
		return 1;
	}else{
		return 0;
	}
	
}


unsigned int nextLargest(unsigned int number){
	if(number>253 || number<0){
		printf("\nOnly 8 bit in consideration");
		return -1;
	}
	int numberOfBits = 8;
	int oneCheck = 0;
	int zerosCount=0;
	int onesCount=0;
	unsigned int mask=0b00000000;
	unsigned int mask2=0b00000000;
	int space=0;
	int count=0;
	for(int i=0; i<numberOfBits; i++){
		
		oneCheck = isOne(number, i);
		if(oneCheck==0){
		zerosCount++;	
		}else{
			onesCount++;
		}
		
		if(onesCount>=1 && oneCheck==0){
			number = turnOnBit(number, i); //turn on this bit
			number = turnOffBit(number, i-1); //turn off previous bit
			
			//rearrange
			space = count-1;
		    mask = (0b11111111>>space)<<space;
			number = number & mask;
			mask = 0b11111111>>(numberOfBits-(onesCount-1));
			number = number | mask;
			break;
		}
		count++;
	}
	printf("\nNext Larger Number is: %d", number);
	return number;
}


unsigned int nextSmallest(unsigned int number){
	if(number>255 || number<0){
		printf("\nOnly 8 bit in consideration");
		return -1;
	}
	int numberOfBits = 8;
	int oneCheck = 0;
	int zerosCount=0;
	int onesCount=0;
	unsigned int mask=0;
	int space=0;
	int count=0;
	for(int i=0; i<numberOfBits; i++){
		
		oneCheck = isOne(number, i);
		if(oneCheck==0){
			zerosCount++;	
		}else{
			onesCount++;
		}
		if(zerosCount>=1 && oneCheck==1){
			number = 	turnOffBit(number, i); //turn off this bit
			number = turnOnBit(number, i-1); //turn on this bit 
			
			//rearrange
			space = count-1;
		    mask = (0b11111111>>space)<<space;
			number = number & mask;
			mask = (0b11111111>>(numberOfBits-(onesCount-1)))<<(zerosCount-1);
			number = number | mask;
			break;
		}
		count++;
	}
	printf("\nNext Smaller Number is: %d", number);
	return number;
}
/*
main(){
	int input = 32;
	printf("\nInput: %d", input);
	nextLargest(input);
	nextSmallest(input);	
}
*/