# Cracking the Coding Interview (6th Edition) - Solutions
This repositiory contains my personal Java solutions to questions in Gayle McDowell's [Cracking the Coding Interview 6th edition](https://www.amazon.com/Cracking-Coding-Interview-Programming-Questions/dp/0984782850/ref=sr_1_1?s=books&ie=UTF8&qid=1539140733&sr=1-1&keywords=cracking+the+coding+interview+6th) book and some other algorithm/data structure questions from [GeeksforGeeks dot org](https://www.geeksforgeeks.org/)

## CHAPTER 1 - Arrays and Strings
### Data Structure (Implemented)
* Hashmap
### Questions
* ***Question 1:*** Implement an algorithm to determine if a string has all unique characters.
  * Use a character counter array (e.g if characters are just ACSCII i.e 256 possible characters)
  * Use a 26 bit variable (e.g if character range is just a-z) 
  * Use a hash map
* ***Question 2:*** Given two strings, write a method to decide if one is a permutation of the other.
  * Sort both strings and search linearly for uniformity
  * Use character count
  * Find all possible palindromes of string a, check if it matches sting b (Not a recommended solution)
* ***Question 3:*** Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at the end to hold the additional characters.
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
### Data Structure (Implemented)
* Singly Linked List
### Questions
* ***Question 1:*** Write code to remove duplicates from an unsorted linked list.
  * Use Hashmap
* ***Question 2:*** Implement an algorithm to find the kth to last element of a singly linked list.
* ***Question 3:*** Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.
  * Shift subsequent node to current node, until end of list is reached
* ***Question 4:*** Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x.
  * Iterate through list, add lesser elements to the head and bigger or equal elements to tail. Link head and tail properly at the end
* ***Question 5:*** You have two numbers represented by a linked list. Write a function that adds the two numbers and returns the sum as a linked list.
  * Pad both list to make sure they are same length before starting the add operation 
* ***Question 6:*** Implement a function to check if a linked list is a palindrome.
  * Use a stack
  * 0(N) time: iterate to the middle of the list, Create two pointers at this stage. Make pointer a race back towards head while pointer b race forwards towards tail. At each hop of the race, they should always have the same content if the list is a true palindrome. Take not of slight modification needed for even length palindromes. 
* ***Question 7:*** Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
node.
  * Pad both linked list to same size. Begin iteration from the head of both list. If intersection exists, the two pointers for list a and list b will eventually meet.
* ***Question 8:*** Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
  * Use runner technique i.e pointer b running 2x as fast as pointer a. to determine. At first intersection revert pointer a back to head of list, begin incrementing pointer a and b 1 step per time. The point of next intersection is the begining of the loop

## CHAPTER 3 - Stacks and Queues
### Data Structure (Implemented)
* Stack
* Queue
* Queue (made from stacks)
* Set of Stacks
### Questions
* ***Question 1:*** Describe how you could use a single array to implement three stacks.
  * Create array which is equal in size to the size of all 3 stacks. Use three variables to keep track of current top index of each stack. decline push request if current top index of the requested stack >= MAX_STACK_SIZE+STACK_START_INDEX  
* ***Question 2:*** How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element? Push, pop and min should all operate in 0(1) time.
  * Use a separate stack in the data structure to keep track of minimum values
* ***Question 3:*** Create a stack of plates data structure
* ***Question 4:*** Implement a MyQueue class which implements a queue using two stacks.
  * Make push request into stack to first pop-out the entire stack and then the new value placed at the buttom of the stack. With this, when a poll() request comes in, simply pop the top of the stack. The reverse implementation of this will also work 
* ***Question 5:*** Write a program to sort a stack such that the smallest items are on the top
  * Use a 1 other temporary stack to help with the sort operation
* ***Question 6:*** Implement animal shelter data structure

## CHAPTER 4 - Trees and Graphs
### Data Structure (Implemented)
* Binary Search Tree (Bst)
* Avl Trees
* Graph
* Tries
* Max Heap
* Min Heap
### Algorithms
* Depth First Search (DFS)
* Breadth First Search (BFS)
* Greedy Algorithm
* Dijkstra
* Topological Sort
### Questions
* ***Question 1:*** Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
  * Use DFS
  * Use BFS (Faster and better) 
* ***Question 2:*** Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.
  * Use Binar traversal on the array. At every iteration, add the picot element tot the bst.
* ***Question 3:*** Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
  * Use an array of linked lists. Traverse through tree (pre-order, in-order or post order). During traversal, keep track of the level of the tree current iteration is at. Use this level to add to the linked list at index level of the created array of linked list. E.g when at level 3, always add all encountered nodes to index 3 of the array.
* ***Question 4:*** Implement a function to check if a binary tree is balanced. For the purposes of this question,
  * Recursive bottom up approach
* ***Question 5:*** Implement a function to check if a binary tree is a binary search tree.
  * store bst in array using in-order traversal. If it is thuly a bst, then the generated array from the in-order traversal will be sorted. This is because in-order traversal of a Bst results in a sorted list. 
* ***Question 6:*** Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree. You may assume that each node has a link to its parent.
  * If right subtree of node is not NULL, then successor lies in right subtree. Go to right subtree and return the node with minimum value. If right subtree of node is NULL, then successor is one of the ancestors. Travel up using the parent pointer until you see a node which is left child of it’s parent. The parent of such a node is the successor.
* ***Question 7:*** You are given a list of projects and a list of dependencies. All of a project's dependencies
must be built before the project is. Find a build order that will allow the projects to be built.
  * Use topological sort
* ***Question 8:*** Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree
  * Begin traversal from the root of the tree. If Bst, ancestor is found at the node where node a and node b lie on the opposite sides of eac other, with respect to the current node. 
* ***Question 9:*** Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
  * Recursive bottomup approach
* ***Question 10:*** Create an algorithm to determine if Tree T2 is a subtree of Tree Tl
  * Use hashing technique
* ***Question 11:*** Implement a binary method getRandomNode() in your binary tree data structure which returns a random node from the tree. With all nodes having equal probability of being selected
  * Recursive bottomup approach
* ***Question 12:*** Paths with sum
  
  
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
