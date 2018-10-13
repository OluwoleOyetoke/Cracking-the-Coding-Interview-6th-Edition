# Cracking the Coding Interview (6th Edition) - Solutions
This repositiory contains my personal Java solutions to questions in Gayle McDowell's [Cracking the Coding Interview book](https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions/dp/0984782850/ref=sr_1_1?s=books&ie=UTF8&qid=1539140733&sr=1-1&keywords=cracking+the+coding+interview+6th) (6th edition) and some other algorithm/data structure questions from [GeeksforGeeks dot org](https://www.geeksforgeeks.org/)

## CHAPTER 1 - Arrays and Strings
### Data Structure Implemented
* Hashmap
### Questions *(& Solution Approach)*
* ***Question 1:*** Implement an algorithm to determine if a string has all unique characters.
  * Use a character counter array (e.g if character range is ACSCII i.e 256 possible characters to count for uniqueness)
  * Use a 26 bit variable (e.g if character range is just a-z) 
  * Use a hash map
* ***Question 2:*** Given two strings, write a method to decide if one is a permutation of the other.
  * Sort both strings and search linearly through the string to assert uniformity
  * Use a character count array
  * Find all possible palindromes of string a, check if it matches sting b *(Not a recommended solution)*
* ***Question 3:*** Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at the end to hold the additional characters.
  * Start replacement operation from the back of the string (right to left)
* ***Question 4:*** Given a string, write a function to check if it is a permutation of a palindrome.
  * Use character count with regards to uniqueness in even and odd length palindrome.
* ***Question 5:*** Given two strings, write a function to check if they are one edit (or zero edits) away.
* ***Question 6:*** Write a function to perform run length coding on a string
  * Iterate through string taking note of the previous char value. onChange(), append previous character and count to StringBuilder and then continue iteration with a refresehd counter for te new character encountered.
* ***Question 7:*** write a method to rotate an NxN matrix by 90 degrees
  * Rotate in place by using 4 temporary integer varables
* ***Question 8:*** Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
* ***Question 9:*** Given two strings, sl and s2, write code to check if s2 is a rotation of sl using only one call to isSubstring
  * Rotation here simply means left or right shift. Just as is done in binary. Use [Reversal algorithm](https://stackoverflow.com/a/31175162/3151251) to acheive rotation

## CHAPTER 2 - Linked Lists
### Data Structure Implemented
* Singly Linked List
### Questions *(& Solution Approach)*
* ***Question 1:*** Write code to remove duplicates from an unsorted linked list.
  * Use hash map
* ***Question 2:*** Implement an algorithm to find the kth to last element of a singly linked list.
* ***Question 3:*** Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.
  * Start from given node, then shift every subsequent node to current node, until end of list is reached
* ***Question 4:*** Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x.
  * Iterate through list, add lesser elements to the head and bigger or equal elements to tail. Link head.nextNode and tail properly at the end
* ***Question 5:*** You have two numbers represented by a linked list. Write a function that adds the two numbers and returns the sum as a linked list.
  * Pad both lists with zeros to make sure they are same length before starting the add operation 
* ***Question 6:*** Implement a function to check if a linked list is a palindrome.
  * *With additional data structure:* Use a stack
  * *Without additional data strucutr 0(N) time:* Get list length, reverse the first half of the list. At arival at the middle of the list, stop reversal algorithm. Create a second pointer pointers at this stage. Make pointer a race back towards the initial head (since these hald of the list has been reversed) while pointer b continues to  race forwards towards the lists initial tail. At each hop of this race, they should always have the same content if the list is a true palindrome. Take note of slight modification needed for even length palindromes. 
* ***Question 7:*** Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
node.
  * Pad both linked list at the head to ensure they are of the same size. Begin iteration from the head of both list. If intersection exists, the two pointers for list a and list b will eventually meet.
* ***Question 8:*** Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
  * *Use runner technique* i.e pointer b running 2x as fast as pointer a. At first intersection revert pointer a back to head of list, and now begin incrementing pointer a and b 1 step per time. The point of next intersection is the begining of the loop. [Interesting discussions about this can be found here](https://stackoverflow.com/questions/1103522/what-is-an-efficient-algorithm-to-find-whether-a-singly-linked-list-is-circular)

## CHAPTER 3 - Stacks and Queues
### Data Structure (Implemented)
* Stack
* Queue
* Queue (made from stacks)
* Set of Stacks
### Questions *(& Solution Approach)*
* ***Question 1:*** Describe how you could use a single array to implement three stacks.
  * Create array whose size is equal to sizeof(stackA, stackB, stackC). Use three integer variables to keep track of each stack's current top index and a nother 3 variables to keep track of each stack's starting index. Decline push request to a stack if current top index of the requested stack >= *MAX_STACK_SIZE + STACK_START_INDEX*  
* ***Question 2:*** How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element? Push, pop and min should all operate in 0(1) time.
  * Use a separate stack in the data structure to keep track of minimum values
* ***Question 3:*** Create a stack of plates data structure
* ***Question 4:*** Implement a MyQueue class which implements a queue using two stacks.
  * Make push request into stack to first pop-out the entire stack and then the new value placed at the buttom of the stack. With this, when a poll() request comes in, simply pop the top of the stack. The reverse implementation of this will also work 
* ***Question 5:*** Write a program to sort a stack such that the smallest items are on the top
  * Use a 1 other temporary stack to help with the sort operation
* ***Question 6:*** Implement animal shelter data structure

## CHAPTER 4 - Trees and Graphs
### Data Structure Implemented
* Binary Search Tree (Bst)
* Avl Trees
* Graph
* Tries
* Max Heap
* Min Heap
### Algorithms Used
* Depth First Search (DFS)
* Breadth First Search (BFS)
* Greedy Algorithm
* Dijkstra
* Topological Sort
* Connected Component Analysis
### Questions *(& Solution Approach)*
* ***Question 1:*** Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
  * Use DFS
  * Use BFS (Faster and better) 
* ***Question 2:*** Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.
  * Use Binary traversal on the array. At every iteration, add the pivot element to the bst. At the end, the generatd tree will be of minimal height
* ***Question 3:*** Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
  * Use an array of linked lists. Traverse through tree (pre-order, in-order or post order). During traversal, keep track of the level of the tree current iteration is at. Use this level to add to the linked list at *index(level)* of the created array of linked list. E.g when at level 3, always add all encountered nodes to the linked list at index 3 of the array.
* ***Question 4:*** Implement a function to check if a binary tree is balanced. For the purposes of this question,
  * Recursive bottom up approach
* ***Question 5:*** Implement a function to check if a binary tree is a binary search tree.
  * Store bst in array using in-order traversal. If it is truly a bst, then the generated array from the in-order traversal will be sorted. This is because in-order traversal of a bst results in a sorted list. 
  * Other smarter solutions to this question exist
* ***Question 6:*** Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree. You may assume that each node has a link to its parent.
  * If right subtree of node is not NULL, then successor lies in right subtree. Go to right subtree and return the node with minimum value. 
  * If right subtree of node is NULL, then successor is one of the ancestors. Travel up using the parent pointer until you see a node which is left child of it’s parent. The parent of such a node is the successor.
* ***Question 7:*** You are given a list of projects and a list of dependencies. All of a project's dependencies
must be built before the project is. Find a build order that will allow the projects to be built.
  * Use topological sort
* ***Question 8:*** Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree
  * Begin traversal from the root of the tree. If tree is a bst, ancestor is found at the node where node a and node b lie on the opposite sides of eac other, with respect to the current node. If tree is not a bst, another kind of recursive approach can be used.
* ***Question 9:*** Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
  * Deep recursive bottom up approach
* ***Question 10:*** Create an algorithm to determine if Tree T2 is a subtree of Tree Tl
  * Use hashing technique
* ***Question 11:*** Implement a binary method getRandomNode() in your binary tree data structure which returns a random node from the tree. With all nodes having equal probability of being selected
* ***Question 12:*** Paths with sum
  
  
## CHAPTER 5 - Bit Manipulation
### Useful Binary Manipulation Techniques
* Bit set
* Bit clear
* Bit Shift

### Questions *(& Solution Approach)*
* ***Question 1:*** Write a method to insert binary a of length N between index i and j of binary b
* ***Question 2:*** Given a real number between O and 1 (e.g., 0.72) print the binary representation
```javascript
  while(true){
  multiply currentIterationValue by 2 to get newValue. 
  if (newValue >=1){
   add 1 to  your result string builder
   subract 1 from newValue to get nextIterationValue.
  } else {
  add 0 to your result string builder
  let nextIterationValue = same newValue gotten above
  }
  
  if(nextIterationValue=0  || result string builder length  > 32){
    Stop iteration
  }else{
    currentIterationValue = nextIterationValue
    Repeat the iteration with this currentIterationValue 
  }
}
```
* ***Question 3:*** You have an integer and you can flip exactly one bit from a 0 to a 1. Write code tofind the length of the longest sequence of ls you could create.
  * Iterate through, flip as you iterate. Maintain count as iteration progresses. Reset count if more than one flip is done. At the end, pick highest count encountered during the iteration process.
* ***Question 4:*** Given a positive integer, print the next smallest and the next largest number that have the same number of 1 bits in their binary representation.
  * [Simplified explanation here](https://martinm2w.wordpress.com/2012/06/03/5-3-bits-next-smallest-and-largest-have-the-same-no-of-1s/)
* ***Question 5:*** Explain what the following code does: ( ( n & ( n-1)) == 0).
* ***Question 6:*** Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
  * Leverage binary XOR
* ***Question 7:*** Write a program to swap odd and even bits in an integer with as few instructions as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
  * Leverage the unique binary pattern generated by these two masks: 0xAAAAAAAA and 0x55555555
* ***Question 8:*** Drawline

## CHAPTER 6 - Math and Logic Puzzles
### Useful Math Concepts
* Prime Numbers
* Probability
  * Mutually Exclusive
  * Mutually Non-Exclusive
* Power of 2 and Log(base 2)
### Questions
* ***Question 1:*** The Heavy Pill.
* ***Question 2:*** Basketball
* ***Question 3:*** Dominos
* ***Question 4:*** Ants on a Triangle
* ***Question 5:*** Jugs of Water
* ***Question 6:*** Blue Eyed Island
* ***Question 7:*** The Apocalypse
* ***Question 8:*** The Egg Drop Problem
* ***Question 9:*** 100 Lockers
* ***Question 10:*** Poisson
  
  
## CHAPTER 7 - Object Oriented Design
### Design Patterns Implemented
* Builder Pattern
* Singleton
* Factory Pattern
* Abstract Factory Pattern
* Model - View - Controller (MVC)
* Strategy Pattern
* Observer Pattern
* Object Pool Pattern
### Design Documentation Utilized
* Class Diagram 
* Activty Diagram
* Sequence Diagram
* Object Diagram
* Usecase DiagramClass 
### Questions
* ***Question 1:*** Call Center: Imagine you have a call center with three levels of employees: respondent, manager,
and director. An incoming telephone call must be first allocated to a respondent who is free. Implement a method dispatchCall() which assigns a call to the first available employee. Hierarchy respondent -> manager -> director
  * Use observer pattern to make all three employes listen into the call system and receive alert when there is an incoming call
* ***Question 4:*** Design a parking lot using object-oriented principles.
* ***Other:*** Use Java concurrency util package to make a design for concurrent task execution in a server
### Solution Approach
* Think about all the objects that will be interacting (class)
* Decide the actions that each of this objects will be taking (methods)
* Determine the level of visibility of each of these objects (encapsulation, composition, aggregation, inheritance)
* Determine the right data structure to use and check if any unique algorithm needs to be implemented
* Ensure proper communication between objects using the appropriate design pattern
* Document design using the proper design documentation techniques (class diagram, sequence diagram etc)

## CHAPTER 8 - Recursion and Dynamic Programming
### Questions *(& Solution Approach)*
* ***Question 1:*** A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time. Implement a method to count how many possible ways the child can run up the
stairs.
  * *Recursive approach:* Perform all possible hops at each step in stair case. Return when the top of the staircase is reached. Discard if the top of the staircase is overshot.
* ***Question 2:*** a robot sitting on the upper left corner of grid with r rows and c columns. The robot can only move in two directions. Design an algorithm to find a path for the robot from the top left to the bottom right.
  * From the starting cell, make all feasible moves allowed by the robot. Recursively continue to make all feasible move on every cell land on. Stop when the bottom right corner is reached. 
* ***Question 3:*** Magic Index
* ***Question 4:*** Write a method to return all subsets of a set
  * Considering every member of the set as a binary member which could be present (1) or not present (0), we will be abel to find all members of the set by assuming the set to be a binary of length *setLength* and finding all possible permutations of this binary
* ***Question 5:*** Write a recursive function to multiply two positive integers without using the operator. You can use addition, subtraction, and bit shifting, but you should minimize the number of those operations.
  * Use [Russian Peasantly Method](https://stackoverflow.com/a/50639778/3151251)
* ***Question 6:*** Towers of Hanoi
  * The stack in the middle is simple meant for transmission  and temporary storage
* ***Question 7:*** Write a method to compute all permutations of a string of unique characters.
  * Backtracking
* ***Question 8:*** Write a method to compute all permutations of a string whose characters are not necessarily unique. The list of permutations should not have duplicates.
  * Backtrack, but before any iteration, check if current character is present ahead (in the string). If present, do not permute it
* ***Question 9:*** Implement an algorithm to print all valid (e.g., properly opened and closed) combinations of n pairs of parentheses.
  * Assume '(' = -1 and ')' = +1. If we start iterating through the string from the left to right and adding up the equivalent integer value of the brackets, at no point will the cumulative sum be > 0. Also, at the end of the string, the cummulative sum must be zero, else, parenthesis are not balanced. E.g. (()) = -1-1+1+1 = 0
* ***Question 10:*** Paint Fill
* ***Question 11:*** Coins: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent), write code to calculate the number of ways of representing n cents.
  * Recursively find all possible cents combinations that will lead to the cents n
* ***Question 12:*** Eight Queens: Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board
so that none of them share the same row, column, or diagonal. In this case, "diagonal" means all
diagonals, not just the two that bisect the board.
  * Recursively check for suitable spots to place queen. Check if it is okay to place queen in current spot by checking if there is no other queen on the same column, row or diagonal to it. To check that there is no other queen diagonal to it, calculate that non of the distance between the columns of the other queens and the current column equals the distance between the current row and the row of the other existing queen.
* ***Question 13:*** Stack of Boxes
* ***Question 14:*** Boolean Evaluation

## CHAPTER 9 - System Design and Scalability
### Important Distributed Design Concepts
* Distribution & Connectivity
  * Horizontal and vertical scaling
  * Load balancing
  * Aynchronuous processing
* Data Management
  * Database partitioning (Vertical, hash-based, directory-based)
  * Database denormalization
  * Caching
  * Map reduce
  * Compression
* Service Considerations
  * Read vs Write Heavy?
  * Availability (Real-time vs Pre-processed) 
  * Highly vs Moderately secure

### Questions
* ***Question 1:*** Stock Data
* ***Question 2:*** Social Network
* ***Question 3:*** Web Crawler
* ***Question 4:*** Duplicate URLs
* ***Question 5:*** Cache
* ***Question 6:*** Sales Rank
* ***Question 7:*** Personal Financial Manager
* ***Question 8:*** Pastebin
* ***Other:*** Build a search engine sing the MESI cache coherence protocol
  * Make cache interaction asynchronous

## CHAPTER 10 - Sorting and Searching
### Algorithms Implemented
* Bubble Sort
* Selection Sort
* Insertion Sort
* merge Sort
* Quick Sort
* Binary Search

### Questions *(& Solution Approach)*
* ***Question 1:*** You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a method to merge B into A in sorted order.
  * Merge Array a and b together by starting with the buffer space at the back
* ***Question 2:*** Write a method to sort an array of strings so that all the anagrams are next to each other.
  * Use a custom implemented comparator which helps to bring anagrams in an array closer to each other
* ***Question 3:*** Search in Rotated Array
  * Use some kind of adapted binary search
* ***Question 4:*** Sorted Search, No Size:
* ***Question 5:*** Given a sorted array of strings that is interspersed with empty strings, write a method to find the location of a given string.
* ***Question 6:*** Imagine you have a 20 GB file with one string per line. Explain how you would sort the file.
* ***Question 7:*** Given an input file with four billion non-negative integers, provide an algorithm to generate an integer that is not contained in the file. Assume you have 1 GB of memory available for this task.
* ***Other 1:*** Rotate and array in place
  * Rotate array by reversing its content in a particular order using the [Reversal Algorithm](https://stackoverflow.com/a/31175162/3151251)
* ***Other 2:*** Given a string, rearrange it in decreasing order by the characters frequency and in lexicographical order if their frequency is equal.
  * Use a hash map to track chracter vs number of occurence. Convert hash map to entry set. place entry set in list. sort list using a custom comparator. Print out list based on number of occurence registered in the hash map
  * Use a tree map
  
## CHAPTER 11 - Testing
###Frameworks Utilized
* JUnit

### Questions *(& Solution Approach)*
* ***Question 1:*** Mistake
* ***Question 2:*** Random Crashes
* ***Question 3:*** ChessTest
* ***Question 4:*** How would you load test a webpage without using any test tools?
* ***Question 5:*** How would you test a pen?
  * Do not assume any functionality about the pen. Rather ask questions about the main features the pen was design to have and with these features, come up with the test cases
* ***Question 6:*** How would you test an ATM in a distributed banking system?

## CHAPTER 12 - C and C++

## CHAPTER 13 - Java

## CHAPTER 14 - Databases

## CHAPTER 15 - Threads and Locks

## CHAPTER 16 - Moderate

## CHAPTER 17 - Hard

## Others
### Questions *(& Solution Approach)*
* ***Browser History:*** Create a LRU cache to print web history based on recently visited sites
  * Use combination of hash map and a doubly linked list
* ***Dijkstra:*** Find shortest path between two cities
  * Use priority queue to implement dijstra algorithm
* ***First Non-repeating Character:*** Find the first non repeating character in an array
  * Use hash map to track occurrence
  * Assuming characters are only ascii based, use character count array to track occurrence
* ***Unique Islands:*** In a square matrix where 1 represents islands and 0 non-islands, find number of connected/ stand alone islands in the matrix
  *  Run a kind of Bfs on the graph to find number of connected components
* ***Matrix:*** Rotate a matrix in place, print a matrix diagonally, print a matrix in spiral order
* ***Maximum Sub-Array:***  Write an efficient program to find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum.
  * Use [Kadane's algorithm](https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm)
* ***Palindrome:*** Find the longest palindromic substring in a string (if any)
  * Use the Manacher's algorithm as explained in some Fred [Akalina](https://www.akalin.com/longest-palindrome-linear-time) and [Prismo Skills](https://prismoskills.appspot.com/lessons/Dynamic_Programming/Chapter_29_-_Longest_Palindrome_In_String.jsp) articles
  * Naive implementation whereby we treate every character as a palindromic centre and try to find how long this palindrome possibly is. Keep track of the result of each attempt and take note of the largest returned value of them all. This is the longest palindrome
* ***Primes:*** Find all the prime numbers between n and m
  * Use [sieve of eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes)
* ***Rearrange String:*** Form the largest possible number from the array of number.
  * Use a custom comparator
* ***K-Smallest:***  Find the k smallest number from an array of numbers
  * Use a min heap. delete min to get the next min on top of the heap
* ***Substring:*** Find longest common substring in a string
  * Use dynamic programing
* ***Subsequence:*** Find the longest common subsequence between two strings
  * Use dynamic programing
* ***Twos Sum:***  * Given an array of integers, return indices of the two numbers such that they add up to a specific target. You may assume that each input would have exactly one solution, and you may not use the same element twice.
  * Use hash map
