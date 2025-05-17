# Autocomplete with Binary Search (Java Implementation)
Java implementation of a fast autocomplete engine using prefix matching, weighted terms, and binary search. There are three classes: Term, BinarySearchDeluxe, and Autocomplete. 

## Term API 
The Term datatype is an immutable data type representing an autocomplete term—a query string and an associated integer weight. It supports comparing terms by three different orders: lexicographic order by query string, in descending order by weight, and lexicographic order by query string but using only the first r characters. 

### Constructor Summary 
    Term(String query, long weight) - initializes a term with the given query string and weight.
    
### Method Summary (Modifier and Type, Method Name and Argument)
    static Comparator<Term> byReverseWeightOrder() - Compares the two terms in descending order by weight

    static Comparator<Term> byPrefixOrder(int r)  - Compares the two terms in lexicographic order, but using only the first r characters of each query

    int compareTo(Term that) - Compares the two terms in lexicographic order by query

    String toString() - Returns a string representation of this term in the following format: the weight, followed by a tab, followed by the query.

## BinarySearchDeluxe API
This is a different implementation of Binary Search which computes the first and last occurrence of a key in a sorted array

### Method Summary (Modifier and Type, Method Name and Argument)
    
    static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) - Returns the index of the first key in the sorted array a[] that is equal to the search key, or -1 if no such key.
    
    static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) - Returns the index of the last key in the sorted array a[] that is equal to the search key, or -1 if no such key.

## Autocomplete API 
Provides autocomplete functionality for a given set of string and weights, using Term and BinarySearchDeluxe. Sorts the terms in lexicographic order, uses binary search to find the all query strings that start with a given prefix, and sorts the matching terms in descending order by weight. 

### Constructor Summary 
    Autocomplete(Term[] terms) - Initializes the data structure from the given array of terms.
    
### Method Summary 
    Term[] allMatches(String prefix) - Returns all terms that start with the given prefix, in descending order of weight.

    int numberOfMatches(String prefix) - Returns the number of terms that start with the given prefix
