import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {

    // an array of terms that indicate the items that Autocomplete
    // can choose from
    private Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        // check that array is not null
        if (terms == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }
        int termsLength = terms.length;
        this.terms = new Term[termsLength];

        for (int i = 0; i < termsLength; i++) {
            this.terms[i] = terms[i];
            // check that array entries are not null
            if (terms[i] == null) {
                throw new IllegalArgumentException("Invalid Argument");
            }
        }

        // sorts the array
        Arrays.sort(this.terms);
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {

        // illegal argument thrower
        exceptionThrower(prefix);

        // this will be the search key
        Term prefixTerm = new Term(prefix, 0);

        int prefixLength = prefix.length();

        Comparator<Term> comparatorPrefixOrder = Term.byPrefixOrder(prefixLength);

        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm,
                                                         comparatorPrefixOrder);
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm,
                                                       comparatorPrefixOrder);
        int numOfMatchingTerms;

        // if there are terms with the prefixTerm, then the difference between
        // the first and last words with the prefix term + 1 will be the number
        // of matching terms
        // it is sufficient to consider firstIndex only because the existence of
        // firstIndex signifies lastIndex also exists
        if (firstIndex != -1) {
            numOfMatchingTerms = lastIndex - firstIndex + 1;
        }
        // this accounts for the case that there are no terms with the
        // prefixTerm
        else {
            numOfMatchingTerms = 0;
        }


        Term[] matchingTerms = new Term[numOfMatchingTerms];

        for (int i = 0; i < matchingTerms.length; i++) {
            matchingTerms[i] = terms[firstIndex + i];
        }

        Arrays.sort(matchingTerms, Term.byReverseWeightOrder());
        return matchingTerms;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        exceptionThrower(prefix);
        Term prefixTerm = new Term(prefix, 0);

        int prefixLength = prefix.length();

        Comparator<Term> comparatorPrefixOrder = Term.byPrefixOrder(prefixLength);

        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm,
                                                         comparatorPrefixOrder);
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm,
                                                       comparatorPrefixOrder);

        // if there are no terms with the prefixTerm, then the number of terms
        // is 0
        // it is sufficient to consider firstIndex only because the existence of
        // firstIndex signifies lastIndex also exists (hence the key is in
        // the array)
        if (firstIndex == -1) {
            return 0;
        }

        return (lastIndex - firstIndex + 1);
    }

    // throws IllegalArgumentExceptions if argument is invalid
    private static void exceptionThrower(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Invalid Argument");
        }
    }

    // unit testing
    public static void main(String[] args) {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }


    }
}
