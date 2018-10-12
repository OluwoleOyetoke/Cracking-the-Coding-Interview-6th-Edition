# Cracking the Coding Interview (6th Edition) - Solutions
This repositiory contains my personal solutions to questions in Gayle McDowell's [Cracking the Coding Interview 6th edition](https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions/dp/0984782850/ref=sr_1_1?s=books&ie=UTF8&qid=1539140733&sr=1-1&keywords=cracking+the+coding+interview+6th) book and some other algorithm/data structure questions from [GeeksforGeeks dot org](https://www.geeksforgeeks.org/)

## CHAPTER 1 - Arrays and Strings
* ***Question 1:*** Implement an algorithm to determine if a string has all unique characters.
  * Use a character counter array or a hash map
* ***Question 2:*** Given two strings, write a method to decide if one is a permutation of the other.
  * Sort both strings and search linearly for uniformity
  * Use character count
  * Find all possible palindromes of string a, check if it matches sting b (Not a recommended solution)
* ***Question 3:*** Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at the end to hold the additional characters,
  * Start replacement from the back of the string (right to left)
* ***Question 4:*** Given a string, write a function to check if it is a permutation of a palindrome.
  * Use character count with regards to uniqueness in even and odd length palindrome.
* ***Question 5:*** Given two strings, write a function to check if they are one edit (or zero edits) away.
* ***Question 6:*** Write a function to perform run length coding on a string
  * Iterate through string taking note of the previous char. onChange, append currentcount and restart new count.
* ***Question 7:*** write a method to rotate an NxN matrix by 90 degrees
* ***Question 8:*** Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
* ***Question 9:*** Given two strings, sl and s2, write code to check if s2 is a rotation of sl using only one call to isSubstring
  * Rotation here simply means left or right shift. Just as is done in binary
## CHAPTER 2 - Linked Lists

## CHAPTER 3 - Stacks and Queues

## CHAPTER 4 - Trees and Graphs

## CHAPTER 5 - Bit Manipulation

## CHAPTER 6 - Math and Logic Puzzles

## CHAPTER 7 - Object Oriented Design

## CHAPTER 8 - Recursion and Dynamic Programming

## CHAPTER 9 - System Design and Scalability

## CHAPTER 10 - Sorting and Searching

## CHAPTER 11 - Testing

## CHAPTER 12 - C and C++

## CHAPTER 13 - Java

## CHAPTER 14 - Databases

## CHAPTER 15 - Threads and Locks

## CHAPTER 16 - Moderate

## CHAPTER 17 - Hard

## Others
